package com.realface

class RoleController
{
    private static final byte OK = 1;
    private static final byte NOK = 0;

    def index()
    {
        List roles = Role.findAll("from Role", [max: 25]);
        return [ roles: roles ]
    }

    def editModal()
    {
        Role role = Role.get(params.id);
        return render(template: "/role/edit_modal", model: [role: role]);
    }

    def create()
    {
        render(view: "edit")
    }

    def submit()
    {
        boolean containsId = params.containsKey("id");

        Role role = (containsId ? Role.get(params.id) : new Role());
        role.properties = params;
        role.save(flush: true);

        String msg;
        boolean hasErrors = role.hasErrors();
        if (hasErrors)
            msg = message(error: role.errors.allErrors.first());
        else if (containsId)
            msg = message(code: "default.updated.message2")
        else
            msg = message(code: "default.created.message2")

        return render(contentType: "text/json") {
            [ status: (hasErrors ? NOK : OK), message: msg ]
        };
    }
}