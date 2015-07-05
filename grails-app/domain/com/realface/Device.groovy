package com.realface

import com.neurotec.devices.NDevice
import com.neurotec.devices.NDeviceType

class Device
{
    String name
    String systemId
    String manufacturer
    String model
    String serialNumber

    NDeviceType type

    boolean available
    boolean disconnectable
    boolean _private

    Date dateCreated
    Date lastUpdated

    static constraints =
    {
        serialNumber(unique: true)
    }

    static mapping =
    {
        version(false)
        type(enumType: "ordinal")
        _private(column: "private")
    }

    public static from(NDevice nDevice)
    {
        Map properties = [
            name: nDevice.getDisplayName(),
            systemId: nDevice.getId(),
            manufacturer: nDevice.getMake(),
            model: nDevice.getModel(),
            serialNumber: nDevice.getSerialNumber(),
            type: nDevice.getDeviceType(),
            available: nDevice.isAvailable(),
            disconnectable: nDevice.isDisconnectable(),
            _private: nDevice.isPrivate()
        ]

        Device device = Device.findWhere(serialNumber: properties.serialNumber)
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