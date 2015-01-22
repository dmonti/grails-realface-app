package com.realface

import grails.transaction.Transactional

@Transactional
class RoleService
{
    def updatePersons(Role role, List<String> personIds)
    {
        Set<PersonRole> newPersons = personIds.collect() {
            Person person = Person.get(it.toLong());
            return new PersonRole([person: person, role: role]);
        }

        Set<PersonRole> oldPersons = role.personRoles;
        if (oldPersons) {
            Set removed = (oldPersons - newPersons);
            removed.each() {
                role.personRoles.remove(it);
                it.delete();
            }
        }

        Set added = (newPersons - oldPersons);
        added.each() { it.save(); }
    }
}