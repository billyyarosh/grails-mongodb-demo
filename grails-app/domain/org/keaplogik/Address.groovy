package org.keaplogik

class Address {

    String streetAddress;
    String city;
    String state;
    String zipCode;

    //Date moveInDate;
    //Date moveOutDate;

    static belongsTo = [person: Person]

    static constraints = {
        streetAddress(blank: false)
        city(blank: false)
        state(blank: false, size: 2..2)
        zipCode(blank: false, size: 5..5, validator: {val, obj -> val?.isNumber()})
        /*moveInDate(nullable: false, max:  new Date())
        moveOutDate(nullable: true, validator: { val, obj ->
            val?.after(obj.moveInDate)
        })*/
    }
}