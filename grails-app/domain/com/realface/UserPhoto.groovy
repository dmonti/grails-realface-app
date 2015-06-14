package com.realface

class UserPhoto
{
    User user

    Date dateCreated

    Date lastUpdated

    static belongsTo = [user: User]

    static constraints = { }
}