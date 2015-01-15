package com.realface

class UserAccessLog
{
    enum Status { LOGGED_IN, INVALID_PASSWORD, INVALID_USERNAME, INACTIVE, BLOCKED, JS_EXCEPTION; }

    String  userCode;

    Status status;

    Date dateCreated;

    static constraints = { }

    static mapping =
    {
        version(false)
        status(enumType: "ordinal")
    }
}