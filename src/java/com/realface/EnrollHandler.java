package com.realface;

import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NBiometricTask;
import com.neurotec.biometrics.NSubject;
import com.neurotec.samples.FaceTools;
import com.neurotec.util.concurrent.CompletionHandler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnrollHandler implements CompletionHandler<NBiometricTask, Object>
{
    private static final Logger log = LoggerFactory.getLogger(EnrollHandler.class);

    IdentificationService service;

    PhotoTemplate source;

    PhotoTemplate target;

    public EnrollHandler(IdentificationService service, PhotoTemplate source, PhotoTemplate target)
    {
        this.service = service;
        this.source = source;
        this.target = target;
    }

    @Override
    public void completed(NBiometricTask task, Object attachment)
    {
        log.debug("EnrollHandler status: " + task.getStatus());
        if (NBiometricStatus.OK.equals(task.getStatus()))
        {
            NSubject sourceSubject = service.loadSubject(source);
            IdentificationHandler identificationHandler = new IdentificationHandler(service, sourceSubject, source, target);
            FaceTools.getInstance().getClient().identify(sourceSubject, null, identificationHandler);
        }
    }

    @Override
    public void failed(final Throwable th, final Object attachment)
    {
        log.warn("EnrollHandler failed!", th);
    }
}