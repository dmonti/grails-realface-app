package com.realface

import com.realface.AccessPointEvent.Status

class HomeController
{
    def index()
    {
        List accessGranted = AccessPointEvent.findAllByStatus(Status.GRANTED, [max: 25, sort: "id", order: "desc"])
        List accessDenied = AccessPointEvent.findAllByStatus(Status.DENIED, [max: 25, sort: "id", order: "desc"])

        return [
            accessGranted: accessGranted,
            accessDenied: accessDenied
        ]
    }
}