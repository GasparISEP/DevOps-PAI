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
        //DE 9 digits
        NIF_RULE_MAP.put("GERMANY", "^\\d{9,11}$");
        //EL 9 digits
        NIF_RULE_MAP.put("GREECE", "^\\d{9}$");
        //HU 10 digits
        NIF_RULE_MAP.put("HUNGARY", "^\\d{10}$");
        //IE 7 digits + 1 or 2 letters
        NIF_RULE_MAP.put("IRELAND", "\\d{7}[a-zA-Z]{1,2}");
        //IT 6 letters + 2 digits + 1 letter + 2 digits + 1 letter + 3 digits + 1 letter
        NIF_RULE_MAP.put("ITALY", "[a-zA-Z]{6}\\d{2}[a-zA-Z]\\d{2}[a-zA-Z]\\d{3}[a-zA-Z]");
        //LV 11 digits
        NIF_RULE_MAP.put("LATVIA", "^\\d{11}$");
        //LT 11 digits
        NIF_RULE_MAP.put("LITHUANIA", "^\\d{11}$");
        //LU 13 digits
        NIF_RULE_MAP.put("LUXEMBOURG", "^\\d{13}$");
        //MT 7 digits + 1 letter (M,G,A,P,L,H,B,Z)
        NIF_RULE_MAP.put("MALTA", "\\d{7}[MGAPLHBZ]");
        //NL 9 digits
        NIF_RULE_MAP.put("NETHERLANDS", "^\\d{9}$");
        //PL 10 or 11 digits
        NIF_RULE_MAP.put("POLAND", "^\\d{10,11}$");
        //PT 9 digits
        NIF_RULE_MAP.put("PORTUGAL", "^\\d{9}$");
        //RO 13 digits
        NIF_RULE_MAP.put("ROMANIA", "^\\d{13}$");
        //SK 9 or 10 digits
        NIF_RULE_MAP.put("SLOVAKIA", "^\\d{9,10}$");
        //SI 8 digits
        NIF_RULE_MAP.put("SLOVENIA", "^\\d{8}$");
        //ES 1 digit or letter + 7 digits  +  1 letter
        NIF_RULE_MAP.put("SPAIN", "[0-9LKMXYZ]\\d{7}[a-zA-Z]");
        //SE between 10, 12
        NIF_RULE_MAP.put("SWEDEN", "^\\d{10,12}$");
    }

    public static boolean NIFValidator (Country country, String NIF){
        try {
            String rule = NIF_RULE_MAP.get(normalizeCountryNIF(country.getCountryName()));

            if (rule != null) {
                return NIF.matches(rule);
            }
        } catch (Exception e) {
            return false;
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
