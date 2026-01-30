package dev.ballgitte.utils;

import java.text.BreakIterator;

/**
 * Utility class for string-related operations.
 */
public final class StringUtils {

    private StringUtils() {}

    /**
     * Used for counting display characters (including emojis) properly in a string, considering Unicode grapheme clusters.
     * @param s the string to count characters in.
     * @return the number of display characters in the string.
     */
    @PublicApi
    public static int countDisplayChars(String s) {
        BreakIterator it = BreakIterator.getCharacterInstance();
        it.setText(s);
        int count = 0;
        while (it.next() != BreakIterator.DONE) {
            count++;
        }
        return count;
    }

    /**
     * Replaces the first occurrence of a target string with a replacement string.
     * @param s the string to replace in.
     * @param target the string to find and replace.
     * @param replacement the string to replace with.
     * @return the modified string with the first occurrence of the target replaced.
     */
    @PublicApi
    public static String replaceFirst(String s, String target, String replacement) {
        int i = s.indexOf(target);
        if (i != -1) {
            s = s.substring(0, i) + replacement + s.substring(i + target.length());
        }
        return s;
    }
}
