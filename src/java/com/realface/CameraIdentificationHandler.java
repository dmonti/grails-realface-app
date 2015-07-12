package com.realface;

import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NMatchingResult;
import com.neurotec.biometrics.NSubject;
import com.neurotec.util.concurrent.CompletionHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CameraIdentificationHandler implements CompletionHandler<NBiometricStatus, CameraIdentificationAttachment>
{
    private static final Logger log = LoggerFactory.getLogger(CameraIdentificationHandler.class);

    @Override
    public void completed(final NBiometricStatus status, final CameraIdentificationAttachment attachment)
    {
        log.debug("CameraIdentificationHandler completed, status: " + status);
        NSubject subject = attachment.subject;
        for (NMatchingResult result : subject.getMatchingResults())
        {
            System.out.println("Owner: " + result.getOwner().getId() + " > Subject: " + result.getSubject().getId() + ", Score: " + result.getScore());
        }
    }

    @Override
    public void failed(final Throwable th, final CameraIdentificationAttachment attachment)
    {
        log.warn("CameraIdentificationHandler Failed!", th);
    }
}