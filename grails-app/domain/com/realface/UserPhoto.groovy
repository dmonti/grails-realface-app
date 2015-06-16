package com.realface

class UserPhoto
{
    static final String DIR = "photo"

    static final String FORMAT = "png"

    User user

    Date dateCreated

    static belongsTo = [user: User]

    static hasOne = [template: Template]

    static constraints =
    {
        user(nullable: true)
        template(nullable: true, unique: true)
    }

    static mapping =
    {
        version(false)
        cache(usage: "read-only")
    }

    public String getFileName()
    {
        String sId = String.valueOf(id)
        return "${sId}.${UserPhoto.FORMAT}"
    }
}