package com.realface

import com.github.sarxos.webcam.Webcam
import com.neurotec.io.NFile
import com.neurotec.images.NImage
import com.neurotec.lang.NCore
import com.neurotec.biometrics.NBiometricStatus
import com.neurotec.biometrics.NBiometricTask
import com.neurotec.biometrics.NFace
import com.neurotec.biometrics.NMatchingResult
import com.neurotec.biometrics.NLAttributes
import com.neurotec.biometrics.NSubject
import com.neurotec.biometrics.NBiometricOperation
import com.neurotec.biometrics.client.NBiometricClient
import com.neurotec.images.NImageFormat
import com.neurotec.samples.FaceTools
import com.neurotec.util.concurrent.CompletionHandler

import java.awt.Dimension
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class IdentificationService
{
    private static final String DIR = "templates"
    private static final String DEFAULT_FORMAT = "PNG"
    private static final Dimension DEFAULT_PHOTO_DIMENSION = new Dimension(640, 480)

    def enrollService
    def storageService

    public PhotoTemplate capture(User user = null)
    {
        PhotoTemplate photo = new PhotoTemplate(user: user)
        photo.save(flush: true)

        Webcam webcam = Webcam.getDefault()
        webcam.setViewSize(DEFAULT_PHOTO_DIMENSION)
        try
        {
            webcam.open()

            File file = storageService.getPhotoFile(photo)
            ImageIO.write(webcam.getImage(), DEFAULT_FORMAT, file)
        }
        catch(IOException e)
        {
            log.warn("Exception capturing photo #${photo.id}.", e)
        }
        finally
        {
            webcam.close()
        }

        return generate(photo)
    }

    public PhotoTemplate generate(PhotoTemplate photo)
    {
        NSubject subject = new NSubject()

        NFace face = new NFace()
        File file = storageService.getPhotoFile(photo)
        face.setImage(NImage.fromFile(file.getAbsolutePath()))
        subject.getFaces().add(face)

        TemplateAttach attach = new TemplateAttach()
        attach.identificationService = this
        attach.photo = photo
        attach.subject = subject

        NBiometricClient client = FaceTools.getInstance().getClient()
        client.createTemplate(subject, attach, new TemplateHandler())

        return photo
    }

    public PhotoTemplate savePhoto(byte[] data)
    {
        PhotoTemplate photo = new PhotoTemplate()
        photo.save(flush: true)

        try
        {
            File file = storageService.getPhotoFile(photo)
            file.withOutputStream { OutputStream stream ->
                stream.write(data)
            }
        }
        catch(Exception e)
        {
            log.warn("Exception saving photo #${photo.id}.", e)
        }

        try
        {
            generate(photo)
        }
        catch(Exception e)
        {
            log.warn("Exception generating template for photo #${photo.id}.", e)
        }

        return photo
    }

    public PhotoTemplate savePhoto(NFace face)
    {
        PhotoTemplate photo = new PhotoTemplate()
        photo.save(flush: true)

        try
        {
            File file = storageService.getPhotoFile(photo)
            face.getImage().save(file.getAbsolutePath(), NImageFormat.getJPEG())
        }
        catch(IOException e)
        {
            log.warn("Exception capturing photo #${photo.id}.", e)
        }

        return generate(photo)
    }

    public void save(NSubject subject, PhotoTemplate photo, Throwable th)
    {
        if ("Operation is not activated".equals(th.getCause().getMessage()))
        {
            save(subject, photo, NBiometricStatus.OPERATION_NOT_ACTIVATED)
        }
    }

    public void save(NSubject subject, PhotoTemplate photo, NBiometricStatus status)
    {
        PhotoTemplate.withTransaction {
            if (photo.user)
            {
                photo.authenticity = AuthenticityStatus.VERIFIED
            }
            photo.status = status
            photo.save(flush: true)
        }

        if (!NBiometricStatus.OK.equals(status))
        {
            log.debug("Photo template #${photo.id} NOT OK, status: ${photo.status}.")
            return
        }

        subject.setId(photo.getSubjectId())

        File file = storageService.getTemplateFile(photo)
        try
        {
            log.debug("Saving photo template #${photo.id}, file: ${file.getAbsolutePath()}")
            NFile.writeAllBytes(file.getAbsolutePath(), subject.getTemplateBuffer())
        }
        catch (IOException e)
        {
            log.warn("Exception writing photo template #${photo.id}, file: ${file.getAbsolutePath()}", e)
        }

        if (photo.user)
        {
            enrollService.perform(subject)
        }
        else
        {
            identifyUser(photo, subject)
        }
    }

    public void identifyUser(PhotoTemplate photo, NSubject subject)
    {
        IdentificationAttach attachment = new IdentificationAttach()
        attachment.photo = photo
        attachment.subject = subject
        attachment.service = this

        FaceTools.getInstance().getClient().identify(subject, attachment, new IdentificationHandler())
    }

    public void save(NMatchingResult result, IdentificationAttach attachment, NBiometricStatus status)
    {
        long targetId = Long.parseLong(result.getId())

        PhotoTemplate target
        PhotoTemplate source

        PhotoTemplate.withTransaction {
            target = PhotoTemplate.get(targetId)
            source = attachment.photo

            source.user = target.user
            source.authenticity = AuthenticityStatus.IDENTIFIED
            source.save(failOnError: true)
        }

        PhotoIdentificationLog.withTransaction {
            new PhotoIdentificationLog(
                source: source,
                target: target,
                status: status,
                score: (result ? result.getScore() : -1)
            ).save(failOnError: true)
        }
    }
}