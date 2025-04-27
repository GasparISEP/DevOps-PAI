package PAI.persistence.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AddressDataModel {

    @Column(name = "Street")
    private String street;
    @Column(name = "PostalCode")
    private String postalCode;
    @Column(name = "Location")
    private String location;
    @Column(name = "Country")
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
