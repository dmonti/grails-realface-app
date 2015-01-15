package com.realface

class AccessPoint
{
    String name

    Date dateCreated

    Date lastUpdated

    static hasMany = [accessLogs: AccessLog]

    static mappedBy = [accessLogs: "accessPoint"]

    static constraints = { }
}