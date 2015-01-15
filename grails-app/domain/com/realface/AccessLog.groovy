package com.realface

class AccessLog
{
    enum Status { DENIED, GRANTED; }

    Person user

    AccessPoint accessPoint

    Status status

    Date dateCreated

    static constraints = { }

    static mapping =
    {
        version(false)
    }
}