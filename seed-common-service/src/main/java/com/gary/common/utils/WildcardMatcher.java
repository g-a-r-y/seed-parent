package com.gary.common.utils;

import java.util.regex.Pattern;

public class WildcardMatcher {
    private static String toRegex(final String expression) {
        final StringBuilder regex = new StringBuilder(expression.length() * 2);
        for (final char c : expression.toCharArray()) {
            switch (c) {
                case '?':
                    regex.append(".?");
                    break;
                case '*':
                    regex.append(".*");
                    break;
                default:
                    regex.append(Pattern.quote(String.valueOf(c)));
                    break;
            }
        }
        return regex.toString();
    }

    public static boolean match(String pattern, String input) {
        Pattern regExPattern = Pattern.compile(toRegex(pattern));
        return regExPattern.matcher(input).matches();
    }
}
