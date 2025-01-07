package PAI.domain;

public class Address {

    private String _street;
    private String _postalCode;
    private String _city;
    private String _country;

    //constructor with validations
    public Address (String street, String postalCode, String location, String country) throws Exception {

        //validation Street
        if (areParametersInvalid(street))
            throw new Exception("Street cannot be empty!");

        _street = street;

        //validation postcode
        if (areParametersInvalid(postalCode))
            throw new Exception("Postcode cannot be empty!");

        _postalCode = postalCode;

        //validation location
        if (areParametersInvalid(location))
            throw new Exception("Location cannot be empty!");

        _city = location;

        //validation country
        if (areParametersInvalid(country))
            throw new Exception("Country cannot be empty!");

        _country = country;
    }

    private boolean areParametersInvalid(String parameter) {

        return parameter == null || parameter.isBlank();
    }
}
