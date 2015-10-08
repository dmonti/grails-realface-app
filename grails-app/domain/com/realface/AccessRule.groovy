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
        description(nullable: true, type: "text")
    }

    static mapping =
    {
        type(enumType: "ordinal")
        afterHour(defaultValue: 0)
        afterMinute(defaultValue: 0)
        beforeHour(defaultValue: 24)
        beforeMinute(defaultValue: 0)
        users(joinTable: [name: "access_rule_users", key: 'rule_id'])
        roles(joinTable: [name: "access_rule_roles", key: 'rule_id'])
    }

    boolean access(User user)
    {
        if (RuleType.ALLOW.equals(type) && inTime() && hasUser(user))
        {
            return true
        }
        if (RuleType.DENY.equals(type) && inTime() && hasUser(user))
        {
            return false
        }
        return false
    }

    boolean hasUser(User user)
    {
        def result = User.executeQuery("""
            SELECT 1 FROM AccessRule rule
            LEFT JOIN rule.users users
            LEFT JOIN rule.roles roles
            LEFT JOIN roles.userRoles userRoles
            WHERE rule.id = :id
            AND (users.id = :userId OR userRoles.user.id = :userId)""",
            [id: id, userId: user.id]
        )
        return !result.isEmpty()
    }

    boolean inTime()
    {
        Calendar currentTime = Calendar.getInstance();
        int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
        int currentMinutes = currentTime.get(Calendar.MINUTE);

        return (currentHour == afterHour && currentMinutes >= afterMinute || currentHour > afterHour) &&
               (currentHour == beforeHour && currentMinutes <= beforeMinute || currentHour < beforeHour)
    }
}