package com.realface

import grails.transaction.Transactional

@Transactional
class RoleService
{
    def updateUsers(Role role, List<String> userIds)
    {
        Set<UserRole> newUsers = userIds.collect() {
            User user = User.get(it.toLong());
            return new UserRole([user: user, role: role]);
        }

        Set<UserRole> oldUsers = role.userRoles;
        if (oldUsers) {
            Set removed = (oldUsers - newUsers);
            removed.each() {
                role.userRoles.remove(it);
                it.delete();
            }
        }

        Set added = (newUsers - oldUsers);
        added.each() { it.save(); }
    }

    Role tryFindByIdOrName(String idOrName)
    {
        Role role = tryFindById(idOrName)
        if (!role)
        {
            role = Role.findByNameIlike(idOrName)
        }
        return role
    }

    Role tryFindById(String id)
    {
        Role role
        try
        {
            Long roleId = Long.parseLong(id)
            role = Role.get(roleId)
        }
        catch (Exception e) { }

        return role
    }
}