package dev.ballgitte.utils;

import java.text.BreakIterator;

/**
 * Utility class for string-related operations.
 */
public final class StringUtils {

    private StringUtils() {}

    /**
     * Used for counting display characters (including emojis) properly in a string, considering Unicode grapheme clusters.
     * @param s the string to count characters in
     * @return the number of display characters in the string
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
}
