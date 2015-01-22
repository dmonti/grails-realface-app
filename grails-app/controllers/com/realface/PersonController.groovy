package com.realface

class PersonController
{
    private static final byte OK = 1;
    private static final byte NOK = 0;

    def index()
    {
        List persons = Person.findAll("from Person", [max: 25]);
        return [ persons: persons ]
    }

    def edit()
    {
        Person person = Person.get(params.id);
        return [ person: person ]
    }

    def search()
    {
        List persons = Person.list(max: 10);
        return render(contentType: "text/json") {
            persons.collect { [ id: it.id, value: it.name, label: it.email ] };
        };
    }

    def create()
    {
        render(view: "edit")
    }

    def submit()
    {
        boolean containsId = params.containsKey("id");

        Person person = (containsId ? Person.get(params.id) : new Person());
        person.properties = params;
        person.save(flush: true);

        String msg;
        boolean hasErrors = person.hasErrors();
        if (hasErrors)
            msg = message(error: person.errors.allErrors.first());
        else if (containsId)
            msg = message(code: "default.updated.message2")
        else
            msg = message(code: "default.created.message2")

        return render(contentType: "text/json") {
            [ status: (hasErrors ? NOK : OK), message: msg]
        };
    }
}