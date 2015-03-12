package com.realface

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
        name(size: 3..64)
        email(size: 9..64, unique: true, email: true)
    }

    static mapping =
    {
        table("app_user")
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