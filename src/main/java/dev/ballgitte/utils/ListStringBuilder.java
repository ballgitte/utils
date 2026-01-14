package dev.ballgitte.utils;

import java.util.List;

public final class ListStringBuilder {

    private ListStringBuilder() {}

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
