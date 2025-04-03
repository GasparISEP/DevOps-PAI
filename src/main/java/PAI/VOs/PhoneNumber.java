package PAI.VOs;

import PAI.ddd.ValueObject;

public class PhoneNumber implements ValueObject {

    private final String _countryCode;
    private final String _number;

    public PhoneNumber(String countryCode, String number) throws Exception{

        if(areParameterInvalid(countryCode)){
            throw new IllegalArgumentException("Country Code cannot be empty");}
        if(isCountryCodeInvalid(countryCode)){
            throw new IllegalArgumentException("Country Code is invalid");
        }
        if(areParameterInvalid(number)){
            throw new IllegalArgumentException("Number cannot be empty");}

        this._countryCode = countryCode;
        this._number = number;
    }

    public boolean areParameterInvalid(String parameter){
        return parameter == null || parameter.isBlank();
    }

    public boolean isCountryCodeInvalid(String countryCode) {
        return !countryCode.matches("^\\+([1-9]\\d{0,3})$");
    }

}
