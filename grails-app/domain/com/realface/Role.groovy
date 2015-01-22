package com.realface

import com.realface.AccessPermission.Type

class Role
{
    String name;

    List<AccessPermission> permissions;

    Date dateCreated;

    Date lastUpdated;

    static constraints = { }

    static hasMany = [personRoles: PersonRole]

    static mappedBy = [personRoles: "role"]

    static transients = ["persons", "permissions"];

    public List<Person> getPersons()
    {
        return getPersonRoles().collect() { it.person; };
    }

    public AccessPermission getPermissions()
    {
        if (permissions == null)
        {
            permissions = AccessPermission.findWhere(type: Type.ROLE, objectId: id);
        }
        return permissions;
    }
}