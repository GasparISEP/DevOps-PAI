package PAI.persistence.datamodel;

import jakarta.persistence.Embeddable;

@Embeddable
public class AddressDataModel {

    private String street;
    private String postalCode;
    private String location;
    private String country;

    public AddressDataModel () {}

    public AddressDataModel (String street, String postalCode, String location, String country) {

        this.street = street;
        this.postalCode = postalCode;
        this.location = location;
        this.country = country;
    }

    public String getStreet () {
        return street;
    }

    public String getPostalCode () {
        return postalCode;
    }

    public String getLocation () {
        return location;
    }

    public String getCountry () {
        return country;
    }
}
