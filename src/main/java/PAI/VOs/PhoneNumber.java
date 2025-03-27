package PAI.VOs;

import PAI.ddd.ValueObject;

public class PhoneNumber implements ValueObject {

    private final String _countryCode;
    private final String _number;

    public PhoneNumber(String countryCode, String number){

        this._countryCode = countryCode;
        this._number = number;
    }
}
