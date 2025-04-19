package PAI.VOs;

import PAI.ddd.ValueObject;

public class Address implements ValueObject {
    private Street _street;
    private PostalCode _postalCode;
    private Location _location;
    private Country _country;

    public Address(Street street, PostalCode postalCode, Location location, Country country){
        if (!isAttributeValid(street)) {
            throw new IllegalArgumentException("Street cannot be null.");
        }
        this._street = street;

        if (!isAttributeValid(postalCode)){
            throw new IllegalArgumentException("Postal Code cannot be null.");
        }
        this._postalCode = postalCode;

        if (!isAttributeValid(location)){
            throw new IllegalArgumentException("Location cannot be null.");
        }
        this._location = location;

        if(!isAttributeValid(country)) {
            throw new IllegalArgumentException("Country cannot be null.");
        }
        this._country = country;
    }

    private boolean isAttributeValid(Object attribute){
        if (attribute == null){
            return false;
        }
        return true;
    }

    public Street getStreet () {
        return _street;
    }

    public PostalCode getPostalCode () {
        return _postalCode;
    }

    public Location getLocation () {
        return _location;
    }

    public Country getCountry () {
        return _country;
    }
}
