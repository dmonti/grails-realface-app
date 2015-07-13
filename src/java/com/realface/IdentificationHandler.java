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

public class IdentificationHandler implements CompletionHandler<NBiometricStatus, IdentificationAttach>
{
    private static final Logger log = LoggerFactory.getLogger(IdentificationHandler.class);

    @Override
    public void completed(final NBiometricStatus status, final IdentificationAttach attachment)
    {
        log.debug("IdentificationHandler completed, status: " + status);

        NSubject subject = attachment.subject;
        if (subject.getMatchingResults().isEmpty())
        {
            log.debug("No match results for subject #" + subject.getId());
        }
        else
        {
            IdentificationService service = attachment.service;
            for (NMatchingResult result : subject.getMatchingResults())
            {
                service.save(result, attachment, status);
            }
        }
    }

    @Override
    public void failed(final Throwable th, final IdentificationAttach attachment)
    {
        log.warn("IdentificationHandler Failed!", th);
    }
}