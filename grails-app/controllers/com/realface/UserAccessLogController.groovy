package com.realface

class UserAccessLogController
{
    def index()
    {
        List logs = UserAccessLog.findAll("from UserAccessLog order by id desc", [max: 25]);
        return [ logs: logs ]
    }
}