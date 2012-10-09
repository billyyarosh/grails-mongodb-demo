package org.keaplogik

class Person {

    String firstName;
    String lastName;

    static hasMany = [addresses: Address]

    static constraints = {
        firstName(blank: false)
        lastName(blank: false)
    }

    String toString(){ return "${firstName} ${lastName}"}
}
