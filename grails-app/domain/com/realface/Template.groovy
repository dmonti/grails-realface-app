package com.realface

import com.neurotec.biometrics.NBiometricStatus

class Template
{
    static final String DIR = "template"

    static final String FORMAT = "template"

    UserPhoto photo

    NBiometricStatus status

    Date dateCreated

    static belongsTo = [photo: UserPhoto]

    static constraints = { }

    static mapping =
    {
        version(false)
        cache(usage: "read-only")
        status(enumType: "ordinal")
    }

    public String getFileName()
    {
        String sId = String.valueOf(id)
        return "${sId}.${Template.FORMAT}"
    }
}