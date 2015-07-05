package com.realface

import com.neurotec.samples.FaceTools
import com.neurotec.devices.NDevice
import com.neurotec.devices.NDeviceType

import grails.transaction.Transactional

@Transactional
class DeviceService
{
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
}