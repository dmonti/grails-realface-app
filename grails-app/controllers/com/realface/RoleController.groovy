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

    def edit()
    {
        return [ role: Role.get(params.id) ]
    }

    def addPersonModal()
    {
        return render(template: "/role/add_person_modal");
    }

    def editModal()
    {
        boolean containsId = params.containsKey("id");

        Role role = containsId ? Role.get(params.id) : null;
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
        if (params.persons && !hasErrors)
        {
            PersonRole.executeUpdate("DELETE FROM PersonRole WHERE role = ?", [role])
            params.list("persons.id").each() {
                Person person = Person.get(it.toLong());
                PersonRole personRole = new PersonRole([person: person, role: role]).save();
                personRole.save(flush: true);
            }
        }


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