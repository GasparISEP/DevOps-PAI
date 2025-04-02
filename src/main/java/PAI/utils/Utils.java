package PAI.utils;

import PAI.VOs.Country;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    private static final Map<String, String> NIF_RULE_MAP = new HashMap<>();

    static {
        //AT U + 8 digits
        NIF_RULE_MAP.put("AUSTRIA", "^U\\d{8}$");
        //BE 11 digits
        NIF_RULE_MAP.put("BELGIUM", "^\\d{11}$");
        //BG 9 or 10 digits
        NIF_RULE_MAP.put("BULGARIA", "^\\d{9,10}$");
        //HR 11 digits
        NIF_RULE_MAP.put("CROATIA", "^\\d{11}$");
        //CY 8 digits + 1 letter
        NIF_RULE_MAP.put("CYPRUS", "^\\d{8}[A-Z]$");
        //CZ between 8, 10 digits
        NIF_RULE_MAP.put("CZECH REPUBLIC", "^\\d{8,10}$");
        //DK 10 digits
        NIF_RULE_MAP.put("DENMARK", "^\\d{10}$");
        //EE between 9, 11 digits
        NIF_RULE_MAP.put("ESTONIA", "^\\d{9,11}$");
        //FI 6 digits + "+, -, A" + 3 digits + 1 digit or letter
        NIF_RULE_MAP.put("FINLAND", "\\d{6}[+\\-A]\\d{3}[a-zA-Z0-9]");
        //FR 13 digits first digit always between 0-3
        NIF_RULE_MAP.put("FRANCE", "[0-3]\\d{12}");
    }

    public static boolean NIFValidator (Country country, String NIF){

        String rule = NIF_RULE_MAP.get(normalizeCountryNIF(country.getCountryName()));

        if(rule != null){
            return NIF.matches(rule);
        }
        return false;
    }

    private static String normalizeCountryNIF (String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")   // remove acentos
                .replaceAll("[^\\p{ASCII}]", "") // remove chars especiais
                .toUpperCase()
                .trim();
    }
}
