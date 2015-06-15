package com.realface;

import com.neurotec.io.NFile;
import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NBiometricTask;
import com.neurotec.biometrics.NFace;
import com.neurotec.biometrics.NSubject;
import com.neurotec.util.concurrent.CompletionHandler;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageCreationHandler implements CompletionHandler<NBiometricTask, Void>
{
    private static final Logger log = LoggerFactory.getLogger(ImageCreationHandler.class);

    NSubject subject;
    File templateFile;

    public ImageCreationHandler(NSubject subject, File templateFile)
    {
        this.subject = subject;
        this.templateFile = templateFile;
    }

    @Override
    public void completed(final NBiometricTask task, final Void attachment)
    {
        if (task.getError() != null)
        {
            log.warn("Error!", task.getError());
        }

        NBiometricStatus status = task.getStatus();
        if (status == NBiometricStatus.OK)
        {
            NFace tokenFace = subject.getFaces().get(1);
            wirteTemplate(templateFile, subject);
        }
    }

    @Override
    public void failed(final Throwable th, final Void attachment)
    {
        log.warn("Failed!", th);
    }

    public void wirteTemplate(File file, NSubject subject)
    {
        try
        {
            log.warn(">>> saving subject id::: " + subject.getId());
            NFile.writeAllBytes(templateFile.getAbsolutePath(), subject.getTemplateBuffer());
        }
        catch (IOException e)
        {
            log.warn("Exception writing template file: " + templateFile.getAbsolutePath());
        }
    }
}