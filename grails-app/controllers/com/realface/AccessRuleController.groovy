package com.realface

class AccessRuleController
{
    def index()
    {
        List rules = AccessRule.list(max: 25);
        return [ rules: rules ]
    }

    def create()
    {
        render(view: "edit")
    }

    def edit()
    {
        AccessRule rule = AccessRule.get(params.id);

        int afterHour = rule ? rule.afterHour : 0;
        int afterMinute = rule ? rule.afterMinute : 0;
        String afterTime = formatNumber(number: afterHour, format: "00") + ":" + formatNumber(number: afterMinute, format: "00")

        int beforeHour = rule ? rule.beforeHour : 0;
        int beforeMinute = rule ? rule.beforeMinute : 0;
        String beforeTime = formatNumber(number: beforeHour, format: "00") + ":" + formatNumber(number: beforeMinute, format: "00")

        return [
            rule: rule,
            afterTime: afterTime,
            beforeTime: beforeTime
        ]
    }
}