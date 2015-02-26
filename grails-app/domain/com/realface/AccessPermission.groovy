package com.realface

class AccessPermission
{
    enum Type { USER, ROLE }

    long objectId

    Type type

    Date dateCreated

    Date lastUpdated

    static constraints = { }

    public Object getObject()
    {
        Object object
        switch(type) {
            case USER:
                object = User.get(objectId)
            break
            case ROLE:
                object = Role.get(objectId)
            break
            default:
                object = null
            break
        }
        return object
    }
}