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

    public static from(NDevice device)
    {
        Map properties = [
            name: device.getDisplayName(),
            systemId: device.getId(),
            manufacturer: device.getMake(),
            model: device.getModel(),
            serialNumber: device.getSerialNumber(),
            type: device.getDeviceType(),
            available: device.isAvailable(),
            disconnectable: device.isDisconnectable(),
            _private: device.isPrivate()
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