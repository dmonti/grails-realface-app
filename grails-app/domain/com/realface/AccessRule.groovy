package com.realface

class AccessRule
{
    enum TargetType { PERSON, ROLE; }

    RuleType type;
    AccessPoint accessPoint;

    long targetId;
    TargetType targetType;

    int afterHour;
    int afterMinute;

    int beforeHour;
    int beforeMinute;

    Date dateCreated;
    Date lastUpdated;

    static constraints = { }

    static mapping =
    {
        targetType(enumType: "ordinal");
        type(enumType: "ordinal");
    }

    public Object getTarget()
    {
        Object targetType;
        switch(type) {
            case PERSON:
                targetType = User.get(targetId);
            break
            case ROLE:
                targetType = Role.get(targetId);
            break
            default:
                targetType = null;
            break
        }
        return targetType;
    }
}