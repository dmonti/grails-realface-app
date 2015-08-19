package com.realface

import grails.transaction.Transactional

@Transactional
class AccessPointService
{
    def access(AccessPoint accessPoint, User user)
    {
        if (!accessPoint.isAttached())
        {
            accessPoint.attach()
        }

        Set rules = accessPoint.rules
        if (!rules)
        {
            return grant(accessPoint, user)
        }

        deny(accessPoint, user)
    }

    def grant(AccessPoint accessPoint, User user)
    {
        return logEvent(accessPoint, user, AccessPointEvent.Status.GRANTED)
    }

    def deny(AccessPoint accessPoint, User user)
    {
        return logEvent(accessPoint, user, AccessPointEvent.Status.DENIED)
    }

    def logEvent(AccessPoint accessPoint, User user, AccessPointEvent.Status status)
    {
        return new AccessPointEvent(
            accessPoint: accessPoint,
            user: user,
            status: status
        ).save()
    }
}