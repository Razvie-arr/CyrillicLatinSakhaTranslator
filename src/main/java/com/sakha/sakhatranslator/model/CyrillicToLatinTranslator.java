package com.sakha.sakhatranslator.model;

import java.util.HashMap;
import java.util.Map;

public class CyrillicToLatinTranslator {
    private String cyrillicText;
    private Map<String, String> diftongs;

    public CyrillicToLatinTranslator(String cyrillicText) {
        this.cyrillicText = cyrillicText;
        initializeDiftongsHashMap();
    }

    public String getCyrillicText() {
        return cyrillicText;
    }

    public String translate() {
        StringBuilder ret = new StringBuilder(cyrillicText.length());
        cyrillicText = cyrillicText.toLowerCase();

        for (int i = 0; i < cyrillicText.length(); i++) {
            boolean nextCharExists = i < cyrillicText.length() - 1;

            if (nextCharExists && cyrillicText.charAt(i) == cyrillicText.charAt(i + 1) && isBytejDorhoon(cyrillicText.charAt(i))) {
                ret.append(getLongSound(cyrillicText.charAt(i)));
                i++;
            } else if (nextCharExists && isDiftong(cyrillicText.charAt(i), cyrillicText.charAt(i + 1))) {
                String diftongInLatin = getDiftong(cyrillicText.charAt(i) + "" + cyrillicText.charAt(i + 1));
                ret.append(diftongInLatin);
                i++;
            } else {
                ret.append(getChar(cyrillicText.charAt(i)));
            }
        }

        return ret.toString();
    }

    private String getChar(char charInCyrillic) {
        return switch (charInCyrillic) {
            case 'а' -> "a";
            case 'б' -> "b";
            case 'г' -> "g";
            case 'ҕ' -> "ʃ";
            case 'д' -> "d";
            case 'и' -> "i";
            case 'й' -> "j";
            case 'к' -> "k";
            case 'л' -> "l";
            case 'м' -> "m";
            case 'н' -> "n";
            case 'ҥ' -> "ŋ";
            case 'о' -> "ɔ";
            case 'п' -> "p";
            case 'р' -> "r";
            case 'с' -> "s";
            case 'һ' -> "h";
            case 'т' -> "t";
            case 'у' -> "u";
            case 'ү' -> "y";
            case 'х' -> "q";
            case 'ч' -> "c";
            case 'ы' -> "ɯ";
            case 'э' -> "e";
            case 'я' -> "ja";
            case 'ө' -> "œ";
            default -> String.valueOf(charInCyrillic);
        };
    }

    private void initializeDiftongsHashMap() {
        diftongs = new HashMap<>();
        diftongs.put("дь", "з");
        diftongs.put("нь", "ɲ");
        diftongs.put("ыа", "ɯa");
        diftongs.put("иэ", "ie");
        diftongs.put("уо", "uɔ");
        diftongs.put("үө", "yө");
    }

    private String getDiftong(String diftongCyrillic) {
        return diftongs.get(diftongCyrillic);
    }

    private boolean isDiftong(char first, char second) {
        String str = first + "" + second;
        return diftongs.containsKey(str);
    }

    private String getLongSound(char ch) {
        return getChar(ch) + ":";
    }

    private boolean isBytejDorhoon(char ch) {
        return switch (ch) {
            case 'а', 'и', 'о', 'у', 'ү', 'ы', 'э' -> true;
            default -> false;
        };
    }
}
