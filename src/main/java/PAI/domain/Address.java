package PAI.domain;

public class Address {

    private String _street;
    private String _postalCode;
    private String _location;
    private String _country;

    //constructor with validations
    public Address (String street, String postalCode, String location, String country) throws IllegalArgumentException {

        validStreet(street);
        validPostalCode(postalCode);
        validLocation(location);
        validCountry(country);
    }

    private void validStreet (String street) {
        if(street == null || street.isBlank()) {
            throw new IllegalArgumentException("Street cannot be empty!");
        }
        _street = street;
    }

    private void validPostalCode (String postalCode) {
        if(postalCode == null || postalCode.isBlank()){
            throw new IllegalArgumentException("Postal code cannot be empty!");
        }
        _postalCode = postalCode;
    }

    private void validLocation (String location) {
        if(location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be empty!");
        }
        if(!location.matches("^[A-Za-zÀ-ÖØ-öø-ÿ' -]+$")){
            throw new IllegalArgumentException("Write a valid location!");
        }
        _location = location;
    }

    private void validCountry (String country) {
        if(country == null || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be empty!");
        }
        if(!country.matches("^[A-Za-zÀ-ÖØ-öø-ÿ' -]+$")){
            throw new IllegalArgumentException("Write a valid country!");

        }
        _country = country;
    }

}
