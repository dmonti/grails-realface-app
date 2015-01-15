package com.realface

class AccessPoint
{
    String name

    Date dateCreated

    Date lastUpdated

    static hasMany = [logs: AccessPointLog]

    static mappedBy = [logs: "accessPoint"]

    static constraints = { }
}