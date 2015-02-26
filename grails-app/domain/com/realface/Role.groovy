package com.realface

import com.realface.AccessPermission.Type

class Role
{
    String name;

    List<AccessPermission> permissions;

    Date dateCreated;

    Date lastUpdated;

    static constraints =
    {
        name(unique: true);
    }

    static hasMany = [userRoles: UserRole]

    static mappedBy = [userRoles: "role"]

    static transients = ["users", "permissions"];

    public List<User> getUsers()
    {
        return getUserRoles().collect() { it.user; };
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