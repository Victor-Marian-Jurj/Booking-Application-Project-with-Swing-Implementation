package util;

import java.util.regex.Pattern;

public class Utils {

    public static final String NUMBERS_CHARACTERS_REGEX = "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$";
    public static final String NUMBER_REGEX = "^\\d+$";
    public static final String REGEX_DOUBLE = "^\\d*\\.\\d+$|^[-+]?\\d+\\.\\d*$";
    public static final String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]{5}$";
    public static final String LETTERS_REGEX = "^[a-zA-Z]+$";
    public static final String DECIMAL_DOUBLE_REGEX = "^[0-9]*(\\.[0-9]{0,2})?$";
    public static final String BOOLEAN_REGEX = "^(?i)(true|false)$";
    public static final String DATE_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";
    public static final String EMAIL_REGEX = "^(.+)@(.+)$";
    public static final String PASSWORD_REGEX = "\\S{6,}";

    public static boolean containsOnlyNumbersAndLetters(String input, String numbersCharatersRegex){
        return Pattern.matches(NUMBERS_CHARACTERS_REGEX, input);
    }


    public static boolean containsOnlyNumbers(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    public static boolean containsOnlyLetters(String input, String lettersRegex) {
        return Pattern.matches(LETTERS_REGEX, input);
    }


    public static boolean containsOnlyBoolean(String input, String booleanRegex) {
        return Pattern.matches(BOOLEAN_REGEX, input);
    }

    public static boolean containsDate(String input, String dateRegex) {
        return Pattern.matches(DATE_REGEX, input);
    }
}
