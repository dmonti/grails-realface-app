package com.realface

import com.neurotec.io.NFile;
import com.neurotec.lang.NCore;
import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NBiometricTask;
import com.neurotec.biometrics.NFace
import com.neurotec.biometrics.NLAttributes
import com.neurotec.biometrics.NSubject
import com.neurotec.biometrics.NBiometricOperation;
import com.neurotec.util.concurrent.CompletionHandler;

import com.neurotec.samples.FaceTools

class TemplateService
{
    def saveFrom(String id, File file)
    {
        NFace face = new NFace()
        face.setFileName(file.getAbsolutePath())

        NSubject subject = new NSubject()
        subject.setId(id)
        subject.getFaces().add(face)

        NBiometricTask task = FaceTools.getInstance().getClient().createTask(EnumSet.of(NBiometricOperation.SEGMENT, NBiometricOperation.ASSESS_QUALITY), subject);

        List licenses = ["Biometrics.FaceDetection", "Biometrics.FaceSegmentation", "Biometrics.FaceQualityAssessment"]
        FaceTools.getInstance().obtainLicenses(licenses)

        File templateFile = new File(file.getParent(), "template-${id}.tlp")
        FaceTools.getInstance().getClient().performTask(task, null, new ImageCreationHandler(subject, templateFile))
    }

    private class ImageCreationHandler implements CompletionHandler<NBiometricTask, Void>
    {
        NSubject subject
        File templateFile

        public ImageCreationHandler(NSubject subject, File templateFile)
        {
            this.subject = subject
            this.templateFile = templateFile
        }

        @Override
        public void completed(final NBiometricTask task, final Void attachment)
        {
            if (task.getError() != null)
            {
                log.warn("Error!", task.getError())
            }

            NBiometricStatus status = task.getStatus();
            if (status == NBiometricStatus.OK)
            {
                NFace tokenFace = subject.getFaces().get(1);
                NFile.writeAllBytes(templateFile.getAbsolutePath(), subject.getTemplateBuffer());
            }
        }

        @Override
        public void failed(final Throwable th, final Void attachment)
        {
            log.warn("Failed!", th)
        }
    }
}