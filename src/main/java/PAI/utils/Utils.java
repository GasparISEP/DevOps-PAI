package PAI.utils;

import PAI.VOs.Country;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static final Map<String, String> NIF_RULE_MAP = new HashMap<>();

    static {
        //AT U + 8 digits
        NIF_RULE_MAP.put(normalize("Austria"), "^U\\d{8}$");
        //BE 11 digits
        NIF_RULE_MAP.put(normalize("Belgium"), "^\\d{11}$");
        //BG 9 or 10 digits
        NIF_RULE_MAP.put(normalize("Bulgaria"), "^\\d{9,10}$");
        //HR 11 digits
        NIF_RULE_MAP.put(normalize("Croatia"), "^\\d{11}$");
        //CY 8 digits + 1 letter
        NIF_RULE_MAP.put(normalize("Cyprus"), "^\\d{8}[A-Z]$");
    }

    public static boolean NIFValidator (Country country, String NIF){

        String rule = NIF_RULE_MAP.get(normalize(country.getCountryName()));

        if(rule != null){
            return NIF.toUpperCase().matches(rule);
        }
        return false;
    }

    private static String normalize (String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")   // remove acentos
                .replaceAll("[^\\p{ASCII}]", "") // remove chars especiais
                .toLowerCase()
                .trim();
    }
}
