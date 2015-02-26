package com.realface

import grails.transaction.Transactional

@Transactional
class UserService
{
    def bootStrap()
    {
        if (User.count() > 0)
            return;

        User user = new User(
            name: "Daniel",
            email: "dms.monti@gmail.com",
            credential: new Credential(code: "dmonti", level: AccessLevel.DEFAULT)
        );

        user.credential.setAndEncodePassword("1234qwer");
        user.save(failOnError: true);
    }

    public User findUser(String codeOrEmail)
    {
        List result = User.executeQuery(
            "FROM User WHERE email = :codeOrEmail OR credential.code = :codeOrEmail",
            [codeOrEmail: codeOrEmail], [max: 1]
        );

        User user;
        if (result.isEmpty())
            user = null;
        else
            user = result.first();

        return user;
    }

    public boolean existsUsernameOrEmail(String codeOrEmail)
    {
        final String query = "SELECT 1 FROM User WHERE email = :codeOrEmail OR credential.code = :codeOrEmail";
        return !User.executeQuery(query, [codeOrEmail: codeOrEmail], [max: 1]).isEmpty()
    }
}