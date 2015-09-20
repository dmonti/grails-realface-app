package com.realface

import grails.transaction.Transactional

@Transactional
class UserService
{
    private static String FIND_CODE_OR_EMAIL_QUERY = "FROM User WHERE email = :codeOrEmail OR credential.code = :codeOrEmail"

    public User findUser(String codeOrEmail)
    {
        List result = User.executeQuery(
            FIND_CODE_OR_EMAIL_QUERY,
            [codeOrEmail: codeOrEmail], [max: 1]
        )
        return (result.isEmpty() ? null : result.first())
    }

    public boolean existsUsernameOrEmail(String codeOrEmail)
    {
        final String query = "SELECT 1 ${FIND_CODE_OR_EMAIL_QUERY}"
        List result = User.executeQuery(query, [codeOrEmail: codeOrEmail], [max: 1])

        return !result.isEmpty()
    }

    User tryFindByIdOrCode(String idOrCode)
    {
        User user = tryFindById(idOrCode)
        if (!user)
        {
            user = tryFindByCode(idOrCode)
        }
        return user
    }

    User tryFindById(String id)
    {
        User user
        try
        {
            Long userId = Long.parseLong(id)
            user = User.get(userId)
        }
        catch (Exception e) { }

        return user
    }

    User tryFindByCode(String code)
    {
        User user
        try
        {
            user = User.findWhere("FROM User WHERE credential.code = ?", code)
        }
        catch (Exception e) { }

        return user
    }

    def bootStrap()
    {
        if (User.count() > 0)
            return

        User user1 = new User(
            name: "Daniel",
            email: "dms.monti@gmail.com",
            credential: new Credential(
                code: "dmonti",
                enabled: true,
                level: AccessLevel.SYSTEM
            )
        )
        user1.credential.setAndEncodePassword("1234qwer")
        user1.save(failOnError: true)

        User user2 = new User(
            name: "Anderson",
            email: "souza.ander@gmail.com",
            credential: new Credential(
                code: "ander",
                enabled: true,
                level: AccessLevel.SYSTEM
            )
        )
        user2.credential.setAndEncodePassword("1234qwer")
        user2.save(failOnError: true)
    }
}