package com.realface

import com.neurotec.biometrics.NBiometricStatus

class PhotoIdentificationLog
{
    PhotoTemplate source

    PhotoTemplate target

    NBiometricStatus status

    int score

    Date dateCreated

    static constraints =
    {
        target(nullable: true)
    }

    static mapping =
    {
        version(false)
        status(enumType: "ordinal")
    }
}