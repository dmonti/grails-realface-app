package com.realface

class AccessPoint
{
    String name
    String description

    int cameraIdx

    Date dateCreated
    Date lastUpdated

    static hasMany = [rules: AccessRule, logs: AccessPointLog]

    static mappedBy = [logs: "accessPoint"]

    static constraints =
    {
        description(nullable: true)
    }

    static mapping =
    {
        description(type:'text')
        rules(joinTable: [name: "access_point_rules"])
    }
}