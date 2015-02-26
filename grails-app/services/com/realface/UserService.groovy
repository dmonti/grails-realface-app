package com.realface

import grails.transaction.Transactional

@Transactional
class UserService
{
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

    def bootStrap()
    {
        if (User.count() > 0)
            return;

        User user;

        user = new User(
            name: "Daniel",
            email: "dms.monti@gmail.com",
            credential: new Credential(
                code: "dmonti",
                enabled: true,
                level: AccessLevel.SYSTEM
            )
        );
        user.credential.setAndEncodePassword("1234qwer");
        user.save(failOnError: true);

        user = new User(
            name: "Jean",
            email: "jeancarlosantonio@gmail.com",
            credential: new Credential(
                code: "jeanonline",
                enabled: true,
                level: AccessLevel.SYSTEM
            )
        );
        user.credential.setAndEncodePassword("1234qwer");
        user.save(failOnError: true);
    }
}