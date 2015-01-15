package com.realface

class AccessPointLog
{
    enum Status { DENIED, GRANTED; }

    Person person;

    AccessPoint accessPoint;

    Status status;

    Date dateCreated;

    static constraints = { }

    static mapping =
    {
        version(false)
    }
}