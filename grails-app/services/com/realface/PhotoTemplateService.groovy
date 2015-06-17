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
import com.neurotec.samples.FaceTools
import com.neurotec.util.concurrent.CompletionHandler

import java.awt.Dimension
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class PhotoTemplateService
{
    private static final String DIR = "templates"
    private static final String DEFAULT_FORMAT = "PNG"
    private static final Dimension DEFAULT_PHOTO_DIMENSION = new Dimension(640, 480)

    File photoBaseDir

    File templateBaseDir

    def storageService

    public PhotoTemplate capture()
    {
        PhotoTemplate photo = new PhotoTemplate()
        photo.save(failOnError: true)

        Webcam webcam = Webcam.getDefault()
        webcam.setViewSize(DEFAULT_PHOTO_DIMENSION)
        try
        {
            webcam.open()

            File file = getPhotoFile(photo)
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
        File file = getPhotoFile(photo)
        face.setImage(NImage.fromFile(file.getAbsolutePath()))
        subject.getFaces().add(face)

        NBiometricClient client = FaceTools.getInstance().getClient()
        FaceTools.getInstance().obtainLicenses(["Biometrics.FaceExtraction", "Biometrics.FaceSegmentsDetection"])

        TemplateCreationHandler handler = new TemplateCreationHandler(this, photo, subject)
        client.createTemplate(subject, null, handler)

        return photo
    }

    public void save(NSubject subject, PhotoTemplate photo, NBiometricStatus status)
    {
        PhotoTemplate.withTransaction {
            photo.status = status
            photo.save(failOnError: true)
        }

        if (!NBiometricStatus.OK.equals(status))
        {
            log.debug("Photo template #${photo.id} NOT OK, status: ${photo.status}.")
            return
        }

        File file = getTemplateFile(photo)
        try
        {
            log.debug("Saving photo template #${photo.id}, file: ${file.getAbsolutePath()}")
            NFile.writeAllBytes(file.getAbsolutePath(), subject.getTemplateBuffer())
        }
        catch (IOException e)
        {
            log.warn("Exception writing photo template #${photo.id}, file: ${file.getAbsolutePath()}", e)
        }
    }

    void recognize(PhotoTemplate source, PhotoTemplate target)
    {
        File sourceFile = getTemplateFile(source)
        NSubject subjectTest = NSubject.fromFile(sourceFile.getAbsolutePath())
        subjectTest.setId(source.getSubjectId())

        File targetFile = getTemplateFile(target)
        NSubject subjectTarget = NSubject.fromFile(targetFile.getAbsolutePath())
        subjectTarget.setId(target.getSubjectId())

        List licenses = ["Biometrics.FaceExtraction", "Biometrics.FaceMatching"]
        FaceTools.getInstance().obtainLicenses(licenses)
        FaceTools.getInstance().getClient().clear()

        NBiometricTask enrollmentTask = new NBiometricTask(EnumSet.of(NBiometricOperation.ENROLL))
        enrollmentTask.getSubjects().add(subjectTarget)

        FaceTools.getInstance().getClient().performTask(enrollmentTask, null, new EnrollHandler(subjectTest))
    }

    public File getPhotoFile(PhotoTemplate photo)
    {
        if (photoBaseDir == null)
        {
            photoBaseDir = new File(storageService.getBaseDir(), "photos")
            if (!photoBaseDir.exists())
                photoBaseDir.mkdirs()
        }
        return new File(photoBaseDir, photo.getPhotoFileName())
    }

    public File getTemplateFile(PhotoTemplate photo)
    {
        if (templateBaseDir == null)
        {
            templateBaseDir = new File(storageService.getBaseDir(), "templates")
            if (!templateBaseDir.exists())
                templateBaseDir.mkdirs()
        }
        return new File(templateBaseDir, photo.getTemplateFileName())
    }
}