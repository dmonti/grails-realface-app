package com.realface

class AccessRuleController
{
    private static final byte OK = 1;
    private static final byte NOK = 0;

    def index()
    {
        List rules = AccessRule.list(max: 25)
        return [ rules: rules ]
    }

    def create()
    {
        render(view: "edit")
    }

    def edit()
    {
        AccessRule rule = AccessRule.get(params.id)

        int afterHour = rule ? rule.afterHour : 0
        int afterMinute = rule ? rule.afterMinute : 0
        String afterTime = formatNumber(number: afterHour, format: "00") + ":" + formatNumber(number: afterMinute, format: "00")

        int beforeHour = rule ? rule.beforeHour : 0
        int beforeMinute = rule ? rule.beforeMinute : 0
        String beforeTime = formatNumber(number: beforeHour, format: "00") + ":" + formatNumber(number: beforeMinute, format: "00")

        return [
            rule: rule,
            afterTime: afterTime,
            beforeTime: beforeTime
        ]
    }

    def submit()
    {
        AccessRule accessRule
        boolean containsId = params.containsKey("id")
        if (containsId)
        {
            accessRule = AccessRule.get(params.long("id"))
        }
        else
        {
            accessRule = new AccessRule()
        }

        if (params.containsKey("type"))
        {
            params.type = RuleType.values()[params.int("type")]
        }
        if (params.containsKey("afterTime"))
        {
            params.afterHour = params.afterTime.split(":")[0]
            params.afterMinute = params.afterTime.split(":")[1]
        }
        if (params.containsKey("beforeTime"))
        {
            params.beforeHour = params.beforeTime.split(":")[0]
            params.beforeMinute = params.beforeTime.split(":")[1]
        }

        accessRule.properties = params
        accessRule.save(flush: true)

        String msg;
        boolean hasErrors = accessRule.hasErrors();
        if (hasErrors)
            msg = message(error: accessRule.errors.allErrors.first());
        else if (containsId)
            msg = message(code: "default.updated.message2")
        else
            msg = message(code: "default.created.message2")

        return render(contentType: "text/json") {[
            returnPage: g.createLink(action: "edit", id: accessRule.id),
            status: (hasErrors ? NOK : OK),
            message: msg
        ]}
    }
}