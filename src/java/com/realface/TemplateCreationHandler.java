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

public class TemplateCreationHandler implements CompletionHandler<NBiometricStatus, Object>
{
    private static final Logger log = LoggerFactory.getLogger(TemplateCreationHandler.class);

    private long timeMillis;

    private NSubject subject;
    private PhotoTemplate photo;
    private IdentificationService service;

    public TemplateCreationHandler(IdentificationService service, PhotoTemplate photo, NSubject subject)
    {
        this.timeMillis = System.currentTimeMillis();
        log.debug("Generating template...");

        this.service = service;
        this.photo = photo;
        this.subject = subject;
    }

    @Override
    public void completed(final NBiometricStatus result, final Object attachment)
    {
        log.debug("Template creation for photo #" + photo.getSId() + " completed in " + (System.currentTimeMillis() - timeMillis) + "ms, result: " + result);
        service.save(subject, photo, result);

        CameraIdentificationAttachment attachment2 = new CameraIdentificationAttachment();
        attachment2.photo = photo;
        attachment2.subject = subject;
        attachment2.service = service;

        FaceTools.getInstance().getClient().identify(subject, attachment2, new CameraIdentificationHandler());
    }

    @Override
    public void failed(final Throwable th, final Object attachment)
    {
        log.warn("Template creation for photo #" + photo.getSId() + " failed in " + (System.currentTimeMillis() - timeMillis) + "ms.", th);
    }
}