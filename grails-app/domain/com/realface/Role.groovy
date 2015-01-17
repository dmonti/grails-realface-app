package com.realface

import com.realface.AccessPermission.Type

class Role
{
    String name

    List<AccessPermission> permissions;

    static constraints = { }

    static transients = ["permissions"];

    public AccessPermission getPermissions()
    {
        if (permissions == null)
        {
            permissions = AccessPermission.executeQuery(
                "FROM AccessPermission WHERE (type = ? AND objectId = ?)", [Type.ROLE, id]
            );
        }
        return permissions;
    }
}