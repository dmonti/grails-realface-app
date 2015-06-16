package com.realface

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
import com.neurotec.util.concurrent.CompletionHandler

import com.neurotec.samples.FaceTools

class TemplateService
{
    def photoService

    def storageService

    File baseDir

    void generate(UserPhoto photo)
    {
        NSubject subject = new NSubject()
        User user = photo.user
        if (user)
        {
            subject.setId(user.getSubjectId())
        }

        NFace face = new NFace()
        File file = photoService.getFile(photo)
        face.setImage(NImage.fromFile(file.getAbsolutePath()))
        subject.getFaces().add(face)

        NBiometricClient client = FaceTools.getInstance().getClient()
        FaceTools.getInstance().obtainLicenses(["Biometrics.FaceExtraction", "Biometrics.FaceSegmentsDetection"])

        TemplateCreationHandler handler = new TemplateCreationHandler(this, photo, subject)
        client.createTemplate(subject, null, handler)
    }

    public void save(NSubject subject, UserPhoto photo, NBiometricStatus status)
    {
        Template template = new Template(photo: photo, status: status)
        Template.withTransaction { template.save(failOnError: true) }
        if (NBiometricStatus.OK.equals(status))
        {
            wirteTemplate(subject, template)
        }
    }

    private void wirteTemplate(NSubject subject, Template template)
    {
        File file = getFile(template)
        try
        {
            log.debug("Saving template file: ${file.getAbsolutePath()}")
            NFile.writeAllBytes(file.getAbsolutePath(), subject.getTemplateBuffer())
        }
        catch (IOException e)
        {
            log.warn("Exception writing template file: ${file.getAbsolutePath()}", e)
        }
    }

    void recognize(File templateTarget, File templateTest)
    {
        NSubject subjectTarget = NSubject.fromFile(templateTarget.getAbsolutePath())
        subjectTarget.setId(templateTarget.getName())

        NSubject subjectTest = NSubject.fromFile(templateTest.getAbsolutePath())
        subjectTest.setId(templateTest.getName())

        List licenses = ["Biometrics.FaceExtraction", "Biometrics.FaceMatching"]
        FaceTools.getInstance().obtainLicenses(licenses)

        FaceTools.getInstance().getClient().clear()

        NBiometricTask enrollmentTask = new NBiometricTask(EnumSet.of(NBiometricOperation.ENROLL))
        enrollmentTask.getSubjects().add(subjectTarget)

        FaceTools.getInstance().getClient().performTask(enrollmentTask, null, new EnrollHandler(subjectTest))
    }

    public File getBaseDir()
    {
        if (baseDir == null)
        {
            baseDir = new File(storageService.getBaseDir(), Template.DIR)
            if (!baseDir.exists())
                baseDir.mkdirs()
        }
        return baseDir
    }

    public File getFile(Template template)
    {
        return new File(getBaseDir(), template.getFileName())
    }
}