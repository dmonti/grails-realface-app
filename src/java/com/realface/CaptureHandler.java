package com.realface;

import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NSubject;
import com.neurotec.util.concurrent.CompletionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaptureHandler implements CompletionHandler<NBiometricStatus, CaptureAttach>
{
    private static final Logger log = LoggerFactory.getLogger(CaptureHandler.class);

    @Override
    public void completed(final NBiometricStatus result, final CaptureAttach attachment)
    {
        log.debug("CaptureHandler, result: " + result);
        if (result == NBiometricStatus.CANCELED)
        {
            return;
        }

        if (result == NBiometricStatus.OK)
        {
            attachment.saveImages();
        }

        try
        {
            Thread.sleep(10000);
            attachment.deviceService.capture(attachment.nDevice);
        }
        catch (InterruptedException e)
        {
            log.warn("Exception waiting 10secs.", e);
        }
    }

    @Override
    public void failed(final Throwable th, final CaptureAttach attachment)
    {
        log.warn("CaptureHandler failed!", th);
    }
}