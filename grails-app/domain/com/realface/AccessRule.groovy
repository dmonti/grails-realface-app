package com.realface

class AccessRule
{
    String code
    String description

    RuleType type

    int afterHour
    int afterMinute

    int beforeHour
    int beforeMinute

    Date dateCreated
    Date lastUpdated

    static hasMany = [users: User, roles: Role]

    static constraints =
    {
        code(size: 3..64, unique: true)
        description(type: "text")

        users(joinTable: [name: "access_rule_users"])
        roles(joinTable: [name: "access_rule_roles"])
    }

    static mapping =
    {
        type(enumType: "ordinal")
        afterHour(defaultValue: 0)
        afterMinute(defaultValue: 0)
        beforeHour(defaultValue: 0)
        beforeMinute(defaultValue: 0)
    }
}