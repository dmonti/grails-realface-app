package com.realface

import com.neurotec.devices.NDevice
import com.neurotec.devices.NDeviceType

class Device
{
    String name
    String systemId

    NDeviceType type

    boolean available
    boolean disconnectable
    boolean _private

    Date dateCreated
    Date lastUpdated

    static constraints = { }

    static mapping =
    {
        version(false)
        type(enumType: "ordinal")
        _private(column: "private")
    }

    public static Device from(NDevice nDevice)
    {
        Map properties = [
            name: nDevice.getDisplayName(),
            systemId: nDevice.getId(),
            type: nDevice.getDeviceType(),
            available: nDevice.isAvailable(),
            disconnectable: nDevice.isDisconnectable(),
            _private: nDevice.isPrivate()
        ]

        Device device = Device.findWhere(systemId: properties.systemId)
        if (device)
            device.properties = properties
        else
            device = new Device(properties)

        return device
    }

    public boolean isPrivate()
    {
        return _private
    }
}