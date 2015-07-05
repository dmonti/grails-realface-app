package com.realface

class DevicesMonitorJob
{
    private static final int INTERVAL = (1000*60*5)

    def deviceService

    static triggers = {
        simple name: "devicesMonitorTrigger", startDelay: INTERVAL, repeatInterval: INTERVAL
    }

    def execute()
    {
        deviceService.update()
    }
}