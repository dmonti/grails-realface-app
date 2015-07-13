package com.realface;

import com.neurotec.biometrics.NSubject;
import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.io.NFile;
import com.neurotec.samples.FaceTools;
import com.neurotec.util.concurrent.CompletionHandler;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateHandler implements CompletionHandler<NBiometricStatus, TemplateAttach>
{
    private static final Logger log = LoggerFactory.getLogger(TemplateHandler.class);

    private long timeMillis;

    public TemplateHandler()
    {
        this.timeMillis = System.currentTimeMillis();
        log.debug("Generating template...");
    }

    @Override
    public void completed(final NBiometricStatus result, final TemplateAttach attachment)
    {
        NSubject subject = attachment.subject;
        PhotoTemplate photo = attachment.photo;
        IdentificationService service = attachment.identificationService;

        log.debug("Template creation for photo #" + photo.getSId() + " completed in " + (System.currentTimeMillis() - timeMillis) + "ms, result: " + result);

        service.save(subject, photo, result);
    }

    @Override
    public void failed(final Throwable th, final TemplateAttach attachment)
    {
        log.warn("Template creation for photo #" + attachment.photo.getSId() + " failed in " + (System.currentTimeMillis() - timeMillis) + "ms.", th);
    }
}