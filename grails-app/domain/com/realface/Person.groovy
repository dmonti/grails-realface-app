package com.realface

import com.realface.AccessPermission.Type

class Person
{
    String name

    String email;

    List<AccessPermission> permissions;

    // Photos/Auth info

    Date dateCreated;

    Date lastUpdated;

    static constraints =
    {
        email(blank: false, size: 8..64, unique: true);
    }

    static mapping =
    {
        tablePerHierarchy(false)
        roles(joinTable: [name: "person_roles", key: "person_id" ])
    }

    static hasMany = [logs: AccessPointLog, roles: Role]

    static mappedBy = [logs: "person"]

    static transients = ["permissions"];

    public AccessPermission getPermissions()
    {
        if (permissions == null)
        {
            permissions = AccessPermission.findWhere(type: Type.PERSON, objectId: id);
        }
        return permissions;
    }
}