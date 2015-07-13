package com.realface;

import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NBiometricTask;
import com.neurotec.biometrics.NSubject;
import com.neurotec.samples.FaceTools;
import com.neurotec.util.concurrent.CompletionHandler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnrollHandler implements CompletionHandler<NBiometricTask, EnrollAttach>
{
    private static final Logger log = LoggerFactory.getLogger(EnrollHandler.class);

    @Override
    public void completed(NBiometricTask task, EnrollAttach attachment)
    {
        log.debug("EnrollHandler status: " + task.getStatus());
        if (NBiometricStatus.OK.equals(task.getStatus()))
        {
            log.info(task.getSubjects().size() + " subjects loaded!");
        }
    }

    @Override
    public void failed(final Throwable th, final EnrollAttach attachment)
    {
        log.warn("EnrollHandler failed!", th);
    }
}