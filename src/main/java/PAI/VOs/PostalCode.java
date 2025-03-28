package PAI.VOs;

import PAI.ddd.ValueObject;

public class PostalCode implements ValueObject {

    private final String _postalCode;

    public PostalCode (String postalCode) {

        if(postalCode == null || postalCode.isBlank()){
            throw new IllegalArgumentException("Postal code cannot be empty!");
        }

        this._postalCode = postalCode;
    }
}
