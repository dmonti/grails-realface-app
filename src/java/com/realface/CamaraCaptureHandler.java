package com.realface;

import com.neurotec.biometrics.NBiometricStatus;
import com.neurotec.biometrics.NSubject;
import com.neurotec.util.concurrent.CompletionHandler;

public class CamaraCaptureHandler implements CompletionHandler<NBiometricStatus, NSubject>
{
    @Override
    public void completed(final NBiometricStatus result, final NSubject subject)
    {
        System.out.println("CamaraCaptureHandler, result: " + result);
        if ((result == NBiometricStatus.OK) || (result == NBiometricStatus.CANCELED))
        {
        }
        else
        {
            // Template creation failed, so start capturing again.
            // getSubject().getFaces().get(0).setImage(null);
            // FaceTools.getInstance().getClient().capture(getSubject(), null, captureCompletionHandler);
        }
    }

    @Override
    public void failed(final Throwable th, final NSubject subject)
    {
    }
}