package PAI.factory;

import PAI.domain.Address;

public interface IAddressFactory {

    Address createAddress(String street, String postalCode, String location, String country);

}