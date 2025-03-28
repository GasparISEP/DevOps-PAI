package PAI.VOs;

import PAI.ddd.ValueObject;

public class Street implements ValueObject {

    private final String _street;

    public Street (String street){

        if(street == null || street.isBlank())
            throw new IllegalArgumentException("Street cannot be empty.");

        this._street = street;
    }
}