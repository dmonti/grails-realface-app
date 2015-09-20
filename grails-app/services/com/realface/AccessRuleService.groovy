package com.realface

import grails.transaction.Transactional

@Transactional
class AccessRuleService
{
    AccessRule tryFindByIdOrCode(String idOrCode)
    {
        AccessRule rule = tryFindById(idOrCode)
        if (!rule)
        {
            rule = AccessRule.findByCodeIlike(idOrCode)
        }
        return rule
    }

    AccessRule tryFindById(String id)
    {
        AccessRule rule
        try
        {
            Long ruleId = Long.parseLong(id)
            rule = AccessRule.get(ruleId)
        }
        catch (Exception e) { }

        return rule
    }
}