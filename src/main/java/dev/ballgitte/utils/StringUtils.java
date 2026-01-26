package dev.ballgitte.utils;

import java.text.BreakIterator;

public final class StringUtils {

    private StringUtils() {}

    /**
     * Used for counting display characters (including emojis) properly in a string, considering Unicode grapheme clusters.
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
