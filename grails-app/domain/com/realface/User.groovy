package com.realface

import com.realface.TextUtils

class User
{
    String name

    String email

    Credential credential

    List<AccessPermission> permissions

    Date dateCreated

    Date lastUpdated

    static constraints =
    {
        email(size: 8..64, unique: true, email: true)
    }

    static mapping =
    {
        table("AppUser")
        credential(cascade: "save-update")
    }

    static hasMany = [logs: AccessPointLog, userRoles: UserRole]

    static mappedBy = [logs: "user", userRoles: "user"]

    static transients = ["roles", "permissions"];

    public List<Role> getRoles()
    {
        return getUserRoles().collect() { it.role; };
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