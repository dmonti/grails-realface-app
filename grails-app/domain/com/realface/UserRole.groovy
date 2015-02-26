package com.realface

import org.apache.commons.lang.builder.HashCodeBuilder

class UserRole implements Serializable
{
    User user;

    Role role;

    Date dateCreated;

    static mapping =
    {
        id(composite: ['user', 'role']);
    }

    boolean equals(other)
    {
        return (other instanceof UserRole) &&
               (other.user.id == user.id) &&
               (other.role.id == role.id)
    }

    int hashCode()
    {
        def builder = new HashCodeBuilder()
        builder.append(user.id);
        builder.append(role.id);
        return builder.toHashCode();
    }
}