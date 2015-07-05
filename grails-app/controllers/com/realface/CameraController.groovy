package com.realface

class CameraController
{
    def deviceService

    def test() { }

    def test2()
    {
        def camera1 = deviceService.list().first()
        deviceService.capture(camera1)
    }
}