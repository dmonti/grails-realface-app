package com.realface;

import com.neurotec.biometrics.NBiometricTask;
import com.neurotec.util.concurrent.CompletionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnrollSubjectsCacheHandler implements CompletionHandler<NBiometricTask, IdentificationService>
{
    private static final Logger log = LoggerFactory.getLogger(EnrollSubjectsCacheHandler.class);

    private long timeMillis;

    public EnrollSubjectsCacheHandler()
    {
        this.timeMillis = System.currentTimeMillis();
    }

    @Override
    public void completed(NBiometricTask task, IdentificationService service)
    {
        log.info("Verified subjects cache loaded in " + (System.currentTimeMillis() - timeMillis) + "ms, status: " + task.getStatus());
    }

    @Override
    public void failed(final Throwable th, final IdentificationService service)
    {
        log.warn("EnrollSubjectsCacheHandler failed!", th);
    }
}