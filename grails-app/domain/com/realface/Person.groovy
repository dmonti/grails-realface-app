package com.realface

import com.realface.AccessPermission.Type

class Person
{
    String name

    String email;

    List<AccessPermission> permissions;

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

    static hasMany = [logs: AccessPointLog, personRoles: PersonRole]

    static mappedBy = [logs: "person", personRoles: "person"]

    static transients = ["roles", "permissions"];

    public List<Role> getRoles()
    {
        return getPersonRoles().collect() { it.role; };
    }

    public AccessPermission getPermissions()
    {
        if (permissions == null)
        {
            permissions = AccessPermission.findWhere(type: Type.PERSON, objectId: id);
        }
        return permissions;
    }
}