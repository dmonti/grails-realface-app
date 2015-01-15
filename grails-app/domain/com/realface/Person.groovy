package com.realface

class Person
{
    String name

    String email;

    // Photos/Auth info

    static hasMany = [logs: AccessPointLog, roles: Role]

    static mappedBy = [logs: "person"]

    static constraints =
    {
        name(nullable: true)
        email(blank: false, size: 8..64, unique: true);
    }

    static mapping =
    {
        tablePerHierarchy(false)
    }
}