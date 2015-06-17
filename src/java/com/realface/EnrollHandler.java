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

    private NSubject subject;

    public EnrollHandler(NSubject subject)
    {
        this.subject = subject;
    }

    @Override
    public void completed(NBiometricTask task, Object attachment)
    {
        log.warn("EnrollHandler status: " + task.getStatus());
        if (NBiometricStatus.OK.equals(task.getStatus()))
        {
            FaceTools.getInstance().getClient().identify(subject, null, new IdentificationHandler(subject));
        }
    }

    @Override
    public void failed(final Throwable th, final Object attachment)
    {
        log.warn("Failed!", th);
    }
}