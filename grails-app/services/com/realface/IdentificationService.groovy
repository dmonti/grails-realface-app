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

class IdentificationService
{
    private static final String DIR = "templates"
    private static final String DEFAULT_FORMAT = "PNG"
    private static final Dimension DEFAULT_PHOTO_DIMENSION = new Dimension(640, 480)

    public static List<NSubject> subjects = new ArrayList<NSubject>()

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
        List licenses = ["Biometrics.FaceExtraction", "Biometrics.FaceMatching"]
        FaceTools.getInstance().obtainLicenses(licenses)
        FaceTools.getInstance().getClient().clear()

        NBiometricTask enrollmentTask = new NBiometricTask(EnumSet.of(NBiometricOperation.ENROLL))
        NSubject targetSubject = loadSubject(target)
        enrollmentTask.getSubjects().add(targetSubject)

        EnrollHandler handler = new EnrollHandler(this, source, target)
        FaceTools.getInstance().getClient().performTask(enrollmentTask, null, handler)
    }

    public void save(NMatchingResult result, NBiometricStatus status, PhotoTemplate source, PhotoTemplate target)
    {
        PhotoIdentificationLog.withTransaction {
            new PhotoIdentificationLog(
                source: source,
                target: target,
                status: status,
                score: (result ? result.getScore() : -1)
            ).save(failOnError: true)
        }
    }

    public NSubject loadSubject(PhotoTemplate photo)
    {
        File file = getTemplateFile(photo)
        NSubject subject = NSubject.fromFile(file.getAbsolutePath())
        subject.setId(photo.getSubjectId())
        return subject
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

    public void loadCache()
    {
        PhotoTemplateService.subjects.clear()
        NBiometricTask enrollmentTask = new NBiometricTask(EnumSet.of(NBiometricOperation.ENROLL))

        for (PhotoTemplate photo : loadVerifiedTemplates())
        {
            NSubject subject = loadSubject(photo)
            enrollmentTask.getSubjects().add(subject)
            PhotoTemplateService.subjects.add(subject)
        }

        FaceTools.getInstance().getClient().performTask(enrollmentTask, this, new EnrollSubjectsCacheHandler())
    }

    public List<PhotoTemplate> loadVerifiedTemplates()
    {
        return PhotoTemplate.findAll(
            "FROM PhotoTemplate WHERE user IS NOT NULL AND status = ? AND authenticity = ?",
            [NBiometricStatus.OK, AuthenticityStatus.VERIFIED]
        )
    }
}