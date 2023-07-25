package util;

import java.util.regex.Pattern;

public class Utils {
    public static final String NUMBER_REGEX = "^\\d+$";
    public static final String REGEX_DOUBLE = "^\\d*\\.\\d+$|^[-+]?\\d+\\.\\d*$";
    public static final String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]{5}$";
    public static final String LETTERS_REGEX = "^[a-zA-Z]+$";
    public static final String DECIMAL_DOUBLE_REGEX = "^[0-9]*(\\.[0-9]{0,2})?$";
    public static final String BOOLEAN_REGEX = "^(?i)(true|false)$";
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
}
