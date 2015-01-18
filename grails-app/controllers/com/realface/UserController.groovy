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

    def create()
    {
        render(view: "edit")
    }

    def submit()
    {
        boolean containsId = params.containsKey("id");

        User user = (containsId ? User.get(params.id) : new User());
        if (params.containsKey("password"))
        {
            params.password = user.encodePassword(params.password)
            params.remove("password")
        }

        user.properties = params;
        user.save(flush: true);

        String msg;
        boolean hasErrors = user.hasErrors();
        if (hasErrors)
            msg = message(error: user.errors.allErrors.first());
        else if (containsId)
            msg = message(code: "default.updated.message2")
        else
            msg = message(code: "default.created.message2")

        return render(contentType: "text/json") {
            [ status: (hasErrors ? NOK : OK), message: msg]
        };
    }
}