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
    }

    static hasMany = [logs: AccessPointLog, roles: Role]

    static mappedBy = [logs: "person"]

    static transients = ["permissions"];

    public AccessPermission getPermissions()
    {
        if (permissions == null)
        {
            permissions = AccessPermission.executeQuery(
                "FROM AccessPermission WHERE (type = ? AND objectId = ?)", [Type.PERSON, id]
            );
        }
        return permissions;
    }
}