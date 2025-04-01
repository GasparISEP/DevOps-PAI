package PAI.VOs;

import PAI.ddd.ValueObject;

public class NIF implements ValueObject {
    private final Country _country;
    private final String _NIF;

    public NIF (String NIF, Country country){

       if(NIF == null || NIF.isBlank()) throw new IllegalArgumentException("NIF cannot be empty.");
       if(!isNifValid(NIF)) throw new IllegalArgumentException("NIF is Invalid");

       this._NIF = NIF;
       this._country = country;
    }

    public boolean equals (Object object){

        if (this == object) return true;

        if (object instanceof NIF nif){
            return this._NIF.equals(nif._NIF);
        }

        return false;
    }

    private boolean isNifValid(String NIF){
        return NIF.matches("^[A-Z]{0,2}?\\d{2,14}[A-Z0-9]{0,2}?$") || NIF.matches("^\\d{9}B\\d{2}$");
    }

    public String getNIF(){
        return _NIF;
    }
}
