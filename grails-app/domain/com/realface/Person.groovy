package com.realface

class Person
{
    String name

    // Photos/Auth info

    static hasMany = [accessLogs: AccessLog, roles: Role]

    static mappedBy = [accessLogs: "user"]

    static constraints =
    {
        name(nullable: true)
    }
}