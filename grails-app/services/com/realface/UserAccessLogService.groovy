package com.realface

import com.realface.UserAccessLog.Status;
import grails.transaction.Transactional

@Transactional
class UserAccessLogService
{
    def create(String userCode, status)
    {
        UserAccessLog log = new UserAccessLog(userCode: userCode, status: status);
        log.save();

        return log;
    }
}