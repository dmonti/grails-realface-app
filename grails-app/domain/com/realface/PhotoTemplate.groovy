package com.realface

import com.neurotec.biometrics.NBiometricStatus

class PhotoTemplate
{
    User user

    AccessPoint accessPoint

    AuthenticityStatus authenticity

    NBiometricStatus status

    Date dateCreated

    Date lastUpdated

    static constraints =
    {
        user(nullable: true)
        accessPoint(nullable: true)
        authenticity(nullable: true)
        status(nullable: true)
    }

    static mapping =
    {
        version(false)
        status(enumType: "ordinal")
        authenticity(enumType: "ordinal")
    }

    public String getSId()
    {
        return String.valueOf(id)
    }

    public String getSubjectId()
    {
        return getSId()
    }

    public String getTemplateFileName()
    {
        return "${getSId()}.template"
    }

    public String getPhotoFileName()
    {
        return "${getSId()}.png"
    }
}