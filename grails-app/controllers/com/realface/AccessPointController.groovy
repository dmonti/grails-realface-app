package com.realface

class AccessPointController
{
    def deviceService

    def index()
    {
        List points = AccessPoint.list(max: 25)

        return [ points: points ]
    }

    def create()
    {
        List devices = deviceService.list()

        return render(view: "edit", model: [devices: devices])
    }

    def edit()
    {
        List devices = deviceService.list()
        AccessPoint point = AccessPoint.get(params.id)

        return [ point: point, devices: devices ]
    }
}