package com.realface

import com.neurotec.biometrics.NFace
import com.neurotec.biometrics.NSubject
import com.neurotec.devices.NDevice

public class CaptureAttach
{
    public DeviceService deviceService

    public IdentificationService identificationService

    public NSubject subject

    public NDevice nDevice

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