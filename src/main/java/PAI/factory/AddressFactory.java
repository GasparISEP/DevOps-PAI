package PAI.factory;

import PAI.domain.Address;

public class AddressFactory implements AddressFactoryInterface {

    public Address createAddress (String street, String postalCode, String location, String country) {

        return new Address(street, postalCode, location, country);
    }
}