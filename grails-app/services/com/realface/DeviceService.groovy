package com.realface

import com.neurotec.biometrics.NBiometricCaptureOption
import com.neurotec.biometrics.NFace
import com.neurotec.biometrics.NSubject
import com.neurotec.biometrics.client.NBiometricClient
import com.neurotec.devices.NDevice
import com.neurotec.devices.NDeviceType
import com.neurotec.samples.FaceTools

import grails.transaction.Transactional
import java.util.EnumSet

@Transactional
class DeviceService
{
    def identificationService

    public void init()
    {
        FaceTools.getInstance().getClient().setUseDeviceManager(true)
        def deviceManager = FaceTools.getInstance().getClient().getDeviceManager()
        deviceManager.setDeviceTypes(EnumSet.of(NDeviceType.CAMERA))
        deviceManager.initialize()
    }

    public List<NDevice> list()
    {
        FaceTools.getInstance().obtainLicenses(["Devices.Cameras"])
        def deviceManager = FaceTools.getInstance().getClient().getDeviceManager()
        return deviceManager.getDevices()
    }

    public void update()
    {
        long t = System.currentTimeMillis()
        log.debug("Updating devices entries...")

        List devices = list()
        log.debug(devices.size() + " devices found.")

        for (NDevice nDevice : devices)
        {
            Device device = Device.from(nDevice)
            device.save(failOnError: true)
        }

        log.debug("Devices updated in ${System.currentTimeMillis() - t}ms.")
    }

    public void capture(NDevice nDevice)
    {
        NBiometricClient client = new NBiometricClient()
        client.setUseDeviceManager(true)
        client.setFaceCaptureDevice(nDevice)

        NSubject subject = createCaptureSubject()

        def attachment = new CamaraCaptureHandlerAttachment()
        attachment.subject = subject
        attachment.deviceService = deviceService
        attachment.identificationService = identificationService

        client.capture(subject, attachment, new CamaraCaptureHandler())
    }

    public NSubject createCaptureSubject()
    {
        NFace face = new NFace()
        EnumSet<NBiometricCaptureOption> options = EnumSet.of(NBiometricCaptureOption.STREAM, NBiometricCaptureOption.MANUAL)

        face.setCaptureOptions(options)
        NSubject subject = new NSubject()
        subject.getFaces().add(face)

        return subject
    }

    public void test()
    {
        def camera = list().first()
        capture(camera)
    }
}