package PAI.VOs;

public class Country {
    private final String _country;

    public Country(String country) throws IllegalArgumentException {

            _country = country;
    }

    private boolean isCountryInvalid (String country) {

        if  (country ==  null )
            return true;
        else
            return false;
    }

    public boolean equals (Object object){

        if (this == object) return true;

        if (object instanceof Country country){
            return this._country.equals(country._country);
        }

        return false;
    }

    public String getCountryName (){
        return this._country;
    }

    @Override
    public String toString() {
        return _country;
    }


}
