package PAI.domain;

public class Address {

    private String _street;
    private String _postalCode;
    private String _location;
    private String _country;

    //constructor with validations
    public Address (String street, String postalCode, String location, String country) throws IllegalArgumentException {

        //validation Street
        if (areParametersInvalid(street))
            throw new IllegalArgumentException("Street cannot be empty!");

        _street = street;

        //validation postcode
        if (areParametersInvalid(postalCode))
            throw new IllegalArgumentException("Postal Code cannot be empty!");

        _postalCode = postalCode;

        //validation location
        if (areParametersInvalid(location))
            throw new IllegalArgumentException("Location cannot be empty!");

        _location = location;

        //validation country
        if (areParametersInvalid(country))
            throw new IllegalArgumentException("Country cannot be empty!");

        _country = country;
    }

    private boolean areParametersInvalid(String parameter) {

        return parameter == null || parameter.isBlank();
    }
}
