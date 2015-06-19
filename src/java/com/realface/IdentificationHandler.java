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
    private static final Logger log = LoggerFactory.getLogger(IdentificationHandler.class);

    IdentificationService service;

    NSubject subject;

    PhotoTemplate source;

    PhotoTemplate target;

    public IdentificationHandler(IdentificationService service, NSubject subject, PhotoTemplate source, PhotoTemplate target)
    {
        this.service = service;
        this.subject = subject;
        this.source = source;
        this.target = target;
    }

    @Override
    public void completed(final NBiometricStatus status, final Object attachment)
    {
        log.debug("IdentificationHandler completed, status: status" + status);
        if (subject.getMatchingResults().isEmpty())
        {
            log.warn("No match results for source #" + source.getSId() + " with target #" + target.getSId());
            service.save(null, status, source, target);
        }
        else
        {
            for (NMatchingResult result : subject.getMatchingResults())
            {
                service.save(result, status, source, target);
            }
        }
    }

    @Override
    public void failed(final Throwable th, final Object attachment)
    {
        log.warn("Failed!", th);
    }
}