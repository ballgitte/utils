package dev.ballgitte.utils;

import java.util.List;

/**
 * Utility class for building strings from lists of strings.
 */
public final class ListStringBuilder {

    private ListStringBuilder() {}

    /**
     * Builds a string from a list of strings, separating them with commas and an "and" before the last item.
     * @param listOfStrings the list of strings to build from.
     * @return the built string.
     */
    @PublicApi
    public static String build(List<String> listOfStrings) {
        StringBuilder listStringBuilder = new StringBuilder();
        for (int i = 0; i < listOfStrings.size(); i++) {
            if (i < listOfStrings.size() - 2) {
                listStringBuilder.append(listOfStrings.get(i)).append(", ");
            } else if (i == listOfStrings.size() - 2) {
                listStringBuilder.append(listOfStrings.get(i)).append(", and ");
            } else if (i == listOfStrings.size() - 1) {
                listStringBuilder.append(listOfStrings.get(i)).append(" ");
            }
        }
        return listStringBuilder.toString();
    }
}
