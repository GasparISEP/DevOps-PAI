package PAI.utils;

import PAI.VOs.Country;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static final Map<String, String> NIF_RULE_MAP = new HashMap<>();

    static {
        NIF_RULE_MAP.put(normalize("Austria"), "^U\\d{8}$");
        NIF_RULE_MAP.put(normalize("Belgium"), "^\\d{11}$");
    }

    public static boolean NIFValidator (Country country, String NIF){

        String rule = NIF_RULE_MAP.get(normalize(country.getCountryName()));

        if(rule != null){
            return NIF.matches(rule);
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
