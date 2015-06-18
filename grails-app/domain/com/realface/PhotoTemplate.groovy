package com.realface

import com.neurotec.biometrics.NBiometricStatus

class PhotoTemplate
{
    User user

    NBiometricStatus status

    Date dateCreated

    Date lastUpdated

    static constraints =
    {
        user(nullable: true)
        status(nullable: true)
    }

    static mapping =
    {
        version(false)
        status(enumType: "ordinal")
    }

    public String getSId()
    {
        return String.valueOf(id)
    }

    public String getSubjectId()
    {
        return (user ? "user-${String.valueOf(user.id)}" : "photo-${String.valueOf(id)}")
    }

    public String getTemplateFileName()
    {
        String sId = String.valueOf(id)
        return "${sId}.template"
    }

    public String getPhotoFileName()
    {
        String sId = String.valueOf(id)
        return "${sId}.png"
    }
}