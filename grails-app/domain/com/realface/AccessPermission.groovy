package com.realface

class AccessPermission
{
    enum Type { PERSON, ROLE; }

    long objectId;

    Date dateCreated;

    Date lastUpdated;

    static constraints = { }
}