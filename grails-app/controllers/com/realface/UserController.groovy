package com.realface

class UserController
{
    private static final byte OK = 1
    private static final byte NOK = 0

    def userService

    def index()
    {
        List users = User.list(max: 25)
        return [ users: users ]
    }

    def edit()
    {
        User user = User.get(params.id)
        return [ user: user ]
    }

    def search()
    {
        List users = User.list(max: 10)
        return render(contentType: "text/json") {
            users.collect { [ id: it.id, value: it.name, label: it.email ] }
        }
    }

    def create()
    {
        render(view: "edit")
    }

    def submit()
    {
        User user
        boolean containsId = params.containsKey("id")
        if (containsId)
        {
            user = User.get(params.id)
        }
        else
        {
            user = new User()
            user.credential = new Credential()
        }

        if (params.containsKey("credential.password"))
        {
            user.credential.setAndEncodePassword(params.credential.password)
            params.remove("credential.password")
        }

        user.properties = params
        user.save(flush: true)

        String msg
        boolean hasErrors = user.hasErrors()
        if (hasErrors)
            msg = message(error: user.errors.allErrors.first())
        else if (containsId)
            msg = message(code: "default.updated.message2")
        else
            msg = message(code: "default.created.message2")

        return render(contentType: "text/json") {
            [ status: (hasErrors ? NOK : OK), message: msg ]
        }
    }

    def add()
    {
        String codeOrId = params.codeOrId
        User user = userService.tryFindByIdOrCode(codeOrId)
        return render(contentType: "text/json") {[
            status: (user ? OK : NOK),
            user: (user ? g.render(template: "/role/users_row", bean: user) : null)
        ]}
    }
}