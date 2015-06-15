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

public class IdentificationHandler implements CompletionHandler<NBiometricStatus, Object>
{
    private static final Logger log = LoggerFactory.getLogger(ImageCreationHandler.class);

    NSubject subject;

    public IdentificationHandler(NSubject subject)
    {
        this.subject = subject;
    }

    @Override
    public void completed(final NBiometricStatus status, final Object attachment)
    {
        log.warn(">>>> Identification status: " + status);
        log.warn("IDENTIFY TEST id: " + subject.getId());
        log.warn("IDENTIFY results size: " + subject.getMatchingResults().size());

        if ((status == NBiometricStatus.OK) || (status == NBiometricStatus.MATCH_NOT_FOUND))
        {
            // Match subjects.
            for (NMatchingResult result : subject.getMatchingResults())
            {
                log.warn("IDENTIFY TARGET id: " + result.getId());
                log.warn("IDENTIFY TARGET score: " + result.getScore());
            }
        }
        else
        {
            log.warn("Identification failed: " + status);
            // JOptionPane.showMessageDialog(IdentifyFace.this, "Identification failed: " + status, "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void failed(final Throwable th, final Object attachment)
    {
        log.warn("Failed!", th);
    }
}