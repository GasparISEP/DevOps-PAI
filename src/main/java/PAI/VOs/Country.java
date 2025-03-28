package PAI.VOs;

public class Country {
    private final String _country;

    public Country(String country) throws IllegalArgumentException {
        if (isCountryInvalid(country))
            throw new IllegalArgumentException("This country is not valid.");
        else
            _country = country;
    }

    private boolean isCountryInvalid (String country) {

        if  (country ==  null || country.isBlank() || !country.matches("^[A-Za-zÀ-ÖØ-öø-ÿ' -]+$"))
            return true;
        else
            return false;
    }
}
