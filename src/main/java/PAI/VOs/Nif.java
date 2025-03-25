package PAI.VOs;

public class Nif {
    private String _nif;

    public Nif (String nif){
       if(nif == null || nif.isBlank()) throw new IllegalArgumentException("NIF cannot be empty.");

       this._nif = nif;
    }





}
