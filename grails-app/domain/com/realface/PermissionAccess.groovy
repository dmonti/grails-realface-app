package com.realface

class AccessPermission
{
    enum Type { PERSON, ROLE; }

    Type type

    long objectId

    Date dateCreated

    static constraints = { }
}