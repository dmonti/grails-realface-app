package com.realface;

import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NSubject;
import com.neurotec.util.concurrent.CompletionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CamaraCaptureHandler implements CompletionHandler<NBiometricStatus, CamaraCaptureHandlerAttachment>
{
    private static final Logger log = LoggerFactory.getLogger(CamaraCaptureHandler.class);

    @Override
    public void completed(final NBiometricStatus result, final CamaraCaptureHandlerAttachment attachment)
    {
        log.debug("CamaraCaptureHandler, result: " + result);
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
            attachment.restartCam();
        }
        catch (InterruptedException e)
        {
            log.warn("Exception waiting 10secs.", e);
        }
    }

    @Override
    public void failed(final Throwable th, final CamaraCaptureHandlerAttachment attachment)
    {
        log.warn("CamaraCaptureHandler failed!", th);
    }
}