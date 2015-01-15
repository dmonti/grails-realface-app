package com.realface

class Person
{
    String name

    // Photos/Auth info

    static hasMany = [logs: AccessPointLog, roles: Role]

    static mappedBy = [logs: "person"]

    static constraints =
    {
        name(nullable: true)
    }
}