package com.realface

class UserController
{
    private static final byte OK = 1;
    private static final byte NOK = 0;

    def index()
    {
        List users = User.findAll("from User", [max: 25]);
        return [ users: users ]
    }

    def edit()
    {
        User user = User.get(params.id);
        return [ user: user ]
    }

    def submit()
    {
        User user = User.get(params.id);
        user.properties = params;
        user.save(flush: true);

        boolean hasErrors = user.hasErrors();
        String message = hasErrors ? message(error: user.errors.allErrors.first()) : message(code: "default.updated.message2")
        return render(contentType: "text/json") {[
            status: hasErrors ? NOK : OK,
            message: message
        ]};
    }
}