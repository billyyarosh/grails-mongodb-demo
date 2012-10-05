package org.keaplogik

class Address {

    String state;
    String streetAddress;
    String zipCode;

    Date moveInDate;
    Date moveOutDate;

    static belongsTo = [person: Person]

    static constraints = {
        state(blank: false)
        streetAddress(blank: false)
        zipCode(blank: false)
        moveInDate(nullable: false)
        moveOutDate(nullable: true, validator: { val, obj ->
            val?.after(obj.moveInDate)
        })
    }
}
