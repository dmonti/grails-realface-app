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

    def submit()
    {
        Person person = Person.get(params.id);
        person.properties = params;
        person.save(flush: true);

        boolean hasErrors = person.hasErrors();
        String message = hasErrors ? message(error: person.errors.allErrors.first()) : message(code: "default.updated.message2")
        return render(contentType: "text/json") {[
            status: hasErrors ? NOK : OK,
            message: message
        ]};
    }
}