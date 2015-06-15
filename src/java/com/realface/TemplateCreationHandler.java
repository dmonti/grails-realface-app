package com.realface;

import com.neurotec.biometrics.NSubject;
import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.io.NFile;
import com.neurotec.util.concurrent.CompletionHandler;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateCreationHandler implements CompletionHandler<NBiometricStatus, Object>
{
    private static final Logger log = LoggerFactory.getLogger(TemplateCreationHandler.class);

    private NSubject subject;
    private File templateFile;

    public TemplateCreationHandler(NSubject subject, File templateFile)
    {
        this.subject = subject;
        this.templateFile = templateFile;
    }

    @Override
    public void completed(final NBiometricStatus result, final Object attachment)
    {
        log.warn("TemplateCreationHandler result: " + result);
        if (result == NBiometricStatus.OK)
        {
            wirteTemplate(subject);
        }
    }

    @Override
    public void failed(final Throwable th, final Object attachment)
    {
        log.warn("TemplateCreationHandler Failed!", th);
    }

    public void wirteTemplate(NSubject subject)
    {
        try
        {
            log.warn(">>> Saving template subject id: " + subject.getId());
            NFile.writeAllBytes(templateFile.getAbsolutePath(), subject.getTemplateBuffer());
        }
        catch (IOException e)
        {
            log.warn("Exception writing template file: " + templateFile.getAbsolutePath());
        }
    }
}