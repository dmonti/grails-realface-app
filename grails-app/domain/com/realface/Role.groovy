package com.realface

import com.realface.AccessPermission.Type

class Role
{
    String name;

    List<AccessPermission> permissions;

    Date dateCreated;

    Date lastUpdated;

    static constraints = { }

    static belongsTo = Person

    static hasMany = [persons: Person]

    static mapping =
    {
        persons(joinTable: [name: "person_roles", key: "role_id" ])
    }

    static transients = ["permissions"];

    public AccessPermission getPermissions()
    {
        if (permissions == null)
        {
            permissions = AccessPermission.findWhere(type: Type.ROLE, objectId: id);
        }
        return permissions;
    }
}