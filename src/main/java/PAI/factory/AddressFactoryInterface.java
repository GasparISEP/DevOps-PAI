package PAI.factory;

import PAI.domain.Address;

public interface AddressFactoryInterface {

    Address createAddress(String street, String postalCode, String location, String country);

}