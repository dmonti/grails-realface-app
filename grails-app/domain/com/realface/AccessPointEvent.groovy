package com.realface

class AccessPointEvent
{
    enum Status { DENIED, GRANTED; }

    User user;

    AccessPoint accessPoint;

    Status status;

    Date dateCreated;

    static constraints = { }

    static mapping =
    {
        version(false)
        status(enumType: "ordinal")
    }
}