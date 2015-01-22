package com.realface

import com.realface.AccessPermission.Type

class PersonRole implements Serializable
{
    Person person;

    Role role;

    Date dateCreated;

    static mapping =
    {
        id(composite: ['person', 'role']);
    }
}