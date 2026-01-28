package dev.ballgitte.utils;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Utility class for ID-related operations.
 */
public final class IdUtils {

    private IdUtils() {}

    /**
     * Gets the type of an ID in IDs like `example_type:1294949`, where `example_type` is the type.
     * The delimiter is `:`.
     * @param id the ID to get the type of
     * @return the type of the ID
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
     * Gets the numeric value of an ID in IDs like `example_type:1215129489668055070`, where `1215129489668055070` is the ID.
     * The delimiter is `:`.
     * @param id the ID to get the numeric value of
     * @param parser a function to parse the numeric part of the ID into a number
     * @param <T> number type (Integer, Long, BigInteger, etc.)
     * @return the numeric value of the ID
     */
    @PublicApi
    public static <T> T getId(String id, Function<String, T> parser) {
        int delimiter = id.indexOf(':');
        if (delimiter == -1 || delimiter == id.length() - 1) {
            throw new IllegalArgumentException("Invalid component ID: " + id);
        }
        String numericPart = id.substring(delimiter + 1);
        try {
            return parser.apply(numericPart);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid numeric ID in component ID: " + id, e);
        }
    }

    /**
     * Gets the first available ID from a collection of numbers.
     *
     * @param ids the collection of IDs
     * @param startingNumber the starting number
     * @param increment function to increment the next available ID
     * @param compare function to compare two values
     * @param <T> number type (Integer, Long, BigInteger, etc.)
     * @return the first available ID, starting from {@code startingNumber}
     */
    public static <T> T getFirstAvailableId(
            Collection<T> ids,
            T startingNumber,
            Function<T, T> increment,
            BiFunction<T, T, Integer> compare
    ) {
        if (ids.isEmpty()) {
            return startingNumber;
        }
        NavigableSet<T> sortedIds = new TreeSet<>(compare::apply);
        sortedIds.addAll(ids);
        T expected = startingNumber;
        for (T id : sortedIds) {
            int comparingNumber = compare.apply(id, expected);
            if (comparingNumber < 0) {
                continue;
            }
            if (comparingNumber > 0) {
                return expected;
            }
            expected = increment.apply(expected);
        }
        return expected;
    }
}
