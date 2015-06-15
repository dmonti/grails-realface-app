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
    void saveFrom(String subjectId, File photoFile, File templateFile)
    {
        NImage image = NImage.fromFile(photoFile.getAbsolutePath())

        NSubject subject = new NSubject()
        subject.setId(subjectId)

        NFace face = new NFace()
        face.setImage(image)

        subject.getFaces().add(face)

        NBiometricClient client = FaceTools.getInstance().getClient()
        FaceTools.getInstance().obtainLicenses(["Biometrics.FaceExtraction", "Biometrics.FaceSegmentsDetection"])
        client.createTemplate(subject, null, new TemplateCreationHandler(subject, templateFile))
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
}