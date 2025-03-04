package PAI.domain;

public class AddressFactory {

    public Address createAddress (String street, String postalCode, String location, String country) {

        return new Address(street, postalCode, location, country);
    }
}
