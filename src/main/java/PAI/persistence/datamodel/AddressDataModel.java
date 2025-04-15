package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class AddressDataModel {

    private String _street;
    private String _postalCode;
    private String _location;
    private String _country;

    public AddressDataModel () {}

    public AddressDataModel (String street, String postalCode, String location, String country) {

        _street = street;
        _postalCode = postalCode;
        _location = location;
        _country = country;
    }

    public String getStreet () {
        return _street;
    }

    public String getPostalCode () {
        return _postalCode;
    }

    public String getLocation () {
        return _location;
    }

    public String getCountry () {
        return _country;
    }
}
