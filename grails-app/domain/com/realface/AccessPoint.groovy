package com.realface

class AccessPoint
{
    String name
    String description

    Date dateCreated
    Date lastUpdated

    static hasMany = [rules: AccessRule, logs: AccessPointLog]

    static mappedBy = [logs: "accessPoint"]

    static constraints = { }

    static mapping =
    {
        description(type:'text')
        rules(joinTable: [name: "access_point_rules"])
    }
}