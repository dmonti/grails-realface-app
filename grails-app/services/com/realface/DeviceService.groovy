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

    private static final Map<Integer, NBiometricClient> clients = new HashMap<Integer, NBiometricClient>()

    public void init()
    {
        FaceTools.getInstance().getClient().setUseDeviceManager(true)
        def deviceManager = FaceTools.getInstance().getClient().getDeviceManager()
        deviceManager.setDeviceTypes(EnumSet.of(NDeviceType.CAMERA))
        deviceManager.initialize()
    }

    public List<NDevice> list()
    {
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

    public NBiometricClient capture(NDevice nDevice)
    {
        NBiometricClient client = new NBiometricClient()
        client.setUseDeviceManager(true)
        client.setFaceCaptureDevice(nDevice)

        NSubject subject = createCaptureSubject()

        def attachment = new CaptureAttach()
        attachment.subject = subject
        attachment.deviceService = this
        attachment.identificationService = identificationService
        attachment.nDevice = nDevice

        client.capture(subject, attachment, new CaptureHandler())
        return client
    }

    public NSubject createCaptureSubject()
    {
        NFace face = new NFace()
        EnumSet<NBiometricCaptureOption> options = EnumSet.of(NBiometricCaptureOption.STREAM)

        face.setCaptureOptions(options)
        NSubject subject = new NSubject()
        subject.getFaces().add(face)

        return subject
    }

    public void start(int i)
    {
        def camera = list()[i]
        def client = capture(camera)
        clients.put(i, client)
    }

    public void stop(int i)
    {
        clients.get(i).cancel()
    }
}