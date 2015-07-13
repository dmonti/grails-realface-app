package com.realface

import com.neurotec.biometrics.NFace
import com.neurotec.biometrics.NSubject

public class CaptureAttach
{
    DeviceService deviceService

    IdentificationService identificationService

    NSubject subject

    public NSubject getSubject()
    {
        return subject
    }

    public void saveImages()
    {
        PhotoTemplate.withTransaction {
            saveImages(subject)
        }
    }

    public void saveImages(NSubject subject)
    {
        for (NFace face : subject.getFaces())
        {
            identificationService.savePhoto(face)
        }
    }

    public void resetImages()
    {
        for (NFace face : getSubject().getFaces())
        {
            face.setImage(null)
        }
    }

    public void restartCam()
    {
        deviceService.test()
    }
}