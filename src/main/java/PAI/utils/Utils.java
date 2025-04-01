package PAI.utils;

import PAI.VOs.Country;

public class Utils {

    public static boolean NIFValidator (Country country, String NIF){
        //U + 8 digits
        Country austria = new Country("Austria");
        if(country.equals(austria)){
            return NIF.matches("^U\\d{8}$");
        }

        return false;
    }
}
