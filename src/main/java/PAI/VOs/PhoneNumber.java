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

        if(isPhoneNumberInvalid(number))
            throw new IllegalArgumentException("Phone Number is invalid");

        this._countryCode = countryCode;
        this._number = number;
    }

    public boolean areParameterInvalid(String parameter){
        return parameter == null || parameter.isBlank();
    }

    public boolean isCountryCodeInvalid(String countryCode) {
        return !countryCode.matches("^\\+([1-9]\\d{0,3})$");
    }

    public boolean isPhoneNumberInvalid(String phoneNumber) {
        return !phoneNumber.matches("^\\d{6,15}$");
    }

    @Override
    public boolean equals(Object objectToCompare) {

        if (this == objectToCompare)
            return true;

        if (objectToCompare instanceof PhoneNumber) {

            PhoneNumber phoneNumberTest = (PhoneNumber) objectToCompare;

            if (_countryCode.equals(phoneNumberTest._countryCode) &&
                _number.equals(phoneNumberTest._number)){
                return true;}
        }
        return false;
    }

    @Override
    public String toString() {
        return "Country Code = " + this._countryCode + " " + "PhoneNumber = " + this._number;
    }
}
