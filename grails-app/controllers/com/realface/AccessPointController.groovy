package com.realface

class AccessPointController
{
    def index()
    {
        List points = AccessPoint.list(max: 25);
        return [ points: points ]
    }

    def create()
    {
        render(view: "edit")
    }

    def edit()
    {
        AccessPoint point = AccessPoint.get(params.id);
        return [ point: point ]
    }
}