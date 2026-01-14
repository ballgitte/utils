package dev.ballgitte.utils;

import java.util.Set;

public final class IdUtils {

    private IdUtils() {}

    /**
     * Gets the type of an ID in IDs like "example_type:1294949", where "example_type" is the type
     * The delimiter is `:`
     */
    @PublicApi
    public static String getType(String id) {
        int delimiter = id.indexOf(':');
        if (delimiter == -1) {
            throw new IllegalArgumentException("Invalid component ID (missing `:`): " + id);
        }
        return id.substring(0, delimiter);
    }

    /**
     * Gets the numeric value of an ID as an integer in IDs like "example_type:1294949", where "1294949" is the ID
     * The delimiter is `:`
     */
    @PublicApi
    public static int getIdInt(String id) {
        int delimiter = id.indexOf(':');
        if (delimiter == -1 || delimiter == id.length() - 1) {
            throw new IllegalArgumentException("Invalid component ID: " + id);
        }
        try {
            return Integer.parseInt(id.substring(delimiter + 1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric ID in component ID: " + id, e);
        }
    }

    /**
     * Gets the numeric value of an ID as a long in IDs like "example_type:1215129489668055070", where "1215129489668055070" is the ID
     * The delimiter is `:`
     */
    @PublicApi
    public static long getIdLong(String id) {
        int delimiter = id.indexOf(':');
        if (delimiter == -1 || delimiter == id.length() - 1) {
            throw new IllegalArgumentException("Invalid component ID: " + id);
        }
        try {
            return Long.parseLong(id.substring(delimiter + 1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric ID in component ID: " + id, e);
        }
    }

    @PublicApi
    public static int getFirstAvailableId(Set<Integer> ids) {
        for (int i = 1; ; i++) {
            if (!ids.contains(i)) {
                return i;
            }
        }
    }
}
