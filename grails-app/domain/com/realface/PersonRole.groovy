package com.realface

import org.apache.commons.lang.builder.HashCodeBuilder

class PersonRole implements Serializable
{
    Person person;

    Role role;

    Date dateCreated;

    static mapping =
    {
        id(composite: ['person', 'role']);
    }

    boolean equals(other)
    {
        return (other instanceof PersonRole) &&
               (other.person.id == person.id) &&
               (other.role.id == role.id)
    }

    int hashCode()
    {
        def builder = new HashCodeBuilder()
        builder.append(person.id);
        builder.append(role.id);
        return builder.toHashCode();
    }
}