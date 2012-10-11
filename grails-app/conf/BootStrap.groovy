import org.keaplogik.Person
import org.keaplogik.Address
import org.keaplogik.SecRole
import org.keaplogik.SecUser
import org.keaplogik.SecUserSecRole

class BootStrap {

    def init = { servletContext ->

        //Configure Security Roles
        def userRole = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true)

        //add an admin and default user
        def adminUser = SecUser.findByUsername('admin') ?: new SecUser(
                username: 'admin',
                password: 'admin',
                enabled: true).save(failOnError: true)

        def basicUser = SecUser.findByUsername('guest') ?: new SecUser(
                username: 'guest',
                password: 'guest',                          //pw encoded by security plugin
                enabled: true).save(failOnError: true)

        if (!adminUser.authorities.contains(adminRole)) {
            SecUserSecRole.create adminUser, adminRole
        }
        if (!basicUser.authorities.contains(userRole)) {
            SecUserSecRole.create basicUser, userRole
        }


        //Add mock data if none exists
        if (!Person.count()) {
            def johnDoe = new Person( firstName: "John", lastName: "Doe" ).save(failOnError: true)
            def joeReed = new Person( firstName: "Joe", lastName: "Reed" ).save(failOnError: true)
            def jimSmith = new Person( firstName: "Jim", lastName: "Smith" ).save(failOnError: true)
            def patrickHartwin = new Person( firstName: "Patrick", lastName: "Hartwin" ).save(failOnError: true)
            def steveGunther = new Person( firstName: "Steve", lastName: "Gunther" ).save(failOnError: true)
            def samWhiting = new Person( firstName: "Sam", lastName: "Whiting" ).save(failOnError: true)
            def sarahMathews = new Person( firstName: "Sarah", lastName: "Mathews" ).save(failOnError: true)
            def lisaPudock = new Person( firstName: "Lisa", lastName: "Pudock" ).save(failOnError: true)
            def karaWhiting = new Person( firstName: "Kara", lastName: "Whiting" ).save(failOnError: true)

            johnDoe.addToAddresses(
                    new Address(state: "NY", city: "Windsor", streetAddress: "117 W 2nd St", zipCode: "13865")
            ).addToAddresses(
                    new Address(state: "TX", city: "Alberta", streetAddress: "117 W 2nd St", zipCode: "55555")
            ).addToAddresses(
                    new Address(state: "NY", city: "Longely", streetAddress: "2 Sandy Creek", zipCode: "34009")
            ).addToAddresses(
                    new Address(state: "ME", city: "Ladly", streetAddress: "117 W 2nd St", zipCode: "55533")
            ).addToAddresses(
                    new Address(state: "KY", city: "Korba", streetAddress: "3 Apple St", zipCode: "40351")
            ).save(failOnError: true)

            joeReed.addToAddresses(
                    new Address(state: "KY", city: "Frankfort", streetAddress: "33 Main St", zipCode: "77625")
            ).addToAddresses(
                    new Address(state: "PA", city: "Scranton", streetAddress: "71 Kind Ave Apt 3", zipCode: "44567")
            ).addToAddresses(
                    new Address(state: "PA", city: "Scranton", streetAddress: "8559 Hard Rock", zipCode: "44567")
            ).addToAddresses(
                    new Address(state: "WV", city: "Charleston", streetAddress: "8233 Juniper Rd", zipCode: "33982")
            ).save(failOnError: true)

            jimSmith.addToAddresses(
                    new Address(state: "PA", city: "Blue Ridge", streetAddress: "780 Country Rd", zipCode: "44564")
            ).addToAddresses(
                    new Address(state: "TX", city: "Ft. Worth", streetAddress: "55 Holdem Dr." , zipCode: "77298")
            ).save(failOnError: true)

            patrickHartwin.addToAddresses(
                    new Address(state: "CA", city: "Sacramento", streetAddress: "1 Beach Rd", zipCode: "98765")
            ).addToAddresses(
                    new Address(state: "CA", city: "Sacramento", streetAddress: "53 Sinking Dr." , zipCode: "98765")
            ).save(failOnError: true)

            steveGunther.addToAddresses(
                    new Address(state: "CA", city: "Sacramento", streetAddress: "1 Beach Rd", zipCode: "98765")
            ).addToAddresses(
                    new Address(state: "CA", city: "Sacramento", streetAddress: "53 Sinking Dr." , zipCode: "98765")
            ).addToAddresses(
                    new Address(state: "CA", city: "Sacramento", streetAddress: "759 Sinking Dr." , zipCode: "98765")
            ).save(failOnError: true)

            samWhiting.addToAddresses(
                    new Address(state: "CA", city: "Sacramento", streetAddress: "1 Beach Rd", zipCode: "98765")
            ).save(failOnError: true)

            sarahMathews.addToAddresses(
                    new Address(state: "VT", city: "Burlington", streetAddress: "81 Lake Dr.", zipCode: "22183")
            ).addToAddresses(
                    new Address(state: "VT", city: "Burlington", streetAddress: "40 Shorten Ave Apt 33" , zipCode: "22183")
            ).addToAddresses(
                    new Address(state: "NY", city: "Plattsburgh", streetAddress: "1772 Lovely Lane" , zipCode: "22795")
            ).save(failOnError: true)

            lisaPudock.addToAddresses(
                    new Address(state: "VT", city: "Burlington", streetAddress: "81 Lake Dr.", zipCode: "22183")
            ).addToAddresses(
                    new Address(state: "VT", city: "Burlington", streetAddress: "40 Shorten Ave Apt 33" , zipCode: "22183")
            ).addToAddresses(
                    new Address(state: "NY", city: "Plattsburgh", streetAddress: "1772 Lovely Lane" , zipCode: "22795")
            ).save(failOnError: true)

            karaWhiting.addToAddresses(
                    new Address(state: "CA", city: "Sandiego", streetAddress: "9901 Shore Dr.", zipCode: "98741")
            ).save(failOnError: true)
        }
    }
    def destroy = {
    }
}
