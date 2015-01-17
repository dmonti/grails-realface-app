package com.realface

class AccessPermission
{
    enum Type { PERSON, ROLE; }

    long objectId;

    Type type;

    Date dateCreated;

    Date lastUpdated;

    static constraints = { }

    public Object getObject()
    {
        Object object;
        switch(type) {
            case PERSON:
                object = Person.get(objectId);
            break
            case ROLE:
                object = Role.get(objectId);
            break
            default:
                object = null;
            break
        }
        return object;
    }
}