package com.realface

class CameraController
{
    def deviceService

    def index()
    {
        List devices = deviceService.list()
        return [devices: devices]
    }

    def start()
    {
        if (!params.containsKey("index"))
        {
            return render(0)
        }

        int i = params.int("index")
        deviceService.start(i)

        render(1)
    }
}