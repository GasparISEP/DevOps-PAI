package PAI.VOs;

public class NIF {
    private String _NIF;

    public NIF (String NIF){
       if(NIF == null || NIF.isBlank()) throw new IllegalArgumentException("NIF cannot be empty.");
       if(!isNifValid(NIF)) throw new IllegalArgumentException("NIF is Invalid");

       this._NIF = NIF;
    }


    private boolean isNifValid(String NIF){
        return NIF.matches("^[A-Z]{0,2}?\\d{2,14}[A-Z0-9]{0,2}?$") || NIF.matches("^\\d{9}B\\d{2}$");
    }
}
