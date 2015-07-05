package com.realface

import com.neurotec.samples.FaceTools
import com.neurotec.devices.NDeviceType

class DeviceService
{
    def init()
    {
        FaceTools.getInstance().getClient().setUseDeviceManager(true)
        def deviceManager = FaceTools.getInstance().getClient().getDeviceManager()
        deviceManager.setDeviceTypes(EnumSet.of(NDeviceType.CAMERA))
        deviceManager.initialize()
    }

    def list()
    {
        FaceTools.getInstance().obtainLicenses(["Devices.Cameras"])
        def deviceManager = FaceTools.getInstance().getClient().getDeviceManager()
        return deviceManager.getDevices()
    }
}