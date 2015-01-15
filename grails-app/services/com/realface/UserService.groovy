package com.realface

import grails.transaction.Transactional

@Transactional
class UserService
{
    def bootStrap()
    {
        if (User.count() > 0)
            return;

        User user = new User(name: "Daniel Monti", code: "dmonti", email: "dms.monti@gmail.com");
        user.encodePassword("1234qwer");
        user.save(failOnError: true);
    }
}