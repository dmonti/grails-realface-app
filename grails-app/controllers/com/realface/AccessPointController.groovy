package com.realface

class AccessPointController
{
    private static final byte OK = 1;
    private static final byte NOK = 0;

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

    def report()
    {
        List events = AccessPointEvent.findAll("from AccessPointEvent order by id desc", [max: 25]);
        return [ events: events ]
    }

    def submit()
    {
        def accessPoint
        boolean containsId = params.containsKey("id")
        if (containsId)
        {
            accessPoint = AccessPoint.get(params.long("id"))
        }
        else
        {
            accessPoint = new AccessPoint()
        }

        accessPoint.getRules()?.clear()
        List rules = params.list("rules.id")
        rules?.each {
            Long id = Long.parseLong(it)
            accessPoint.addToRules(AccessRule.get(id))
        }

        accessPoint.properties = params
        accessPoint.save(flush: true)

        String msg;
        boolean hasErrors = accessPoint.hasErrors();
        if (hasErrors)
            msg = message(error: accessPoint.errors.allErrors.first());
        else if (containsId)
            msg = message(code: "default.updated.message2")
        else
            msg = message(code: "default.created.message2")

        return render(contentType: "text/json") {[
            returnPage: g.createLink(action: "edit", id: accessPoint.id),
            status: (hasErrors ? NOK : OK),
            message: msg
        ]}
    }
}