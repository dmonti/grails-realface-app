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

    def download()
    {
        String output = "status,user.id,user.email,accessPoint.id,accessPoint.name"

        List accesses = AccessPointEvent.list([max: 100, sort: "id", order: "desc"])
        accesses.each({ access ->
            def user = access.user
            def accessPoint = access.accessPoint
            output += "\n{{access.status}},{{user.id}},{{user.email}},{{accessPoint.id}},{{accessPoint.name}}"
        })

        byte[] bytes = output.bytes
        response.setContentType("text/csv")
        response.setHeader "Content-disposition", "attachment; filename=accesses.txt"
        response.setContentLength(bytes.size())
        response.outputStream << bytes
    }
}