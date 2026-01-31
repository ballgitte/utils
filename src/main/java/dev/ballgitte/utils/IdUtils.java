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
     * Gets the label of an ID in IDs like `example_label:1294949`, where `example_label` is the label.
     * The delimiter is `:`.
     * @param id the ID to get the label of.
     * @return the label of the ID.
     */
    @PublicApi
    public static String getLabel(String id) {
        int delimiter = id.indexOf(':');
        if (delimiter == -1) {
            throw new IllegalArgumentException("Invalid component ID (missing `:`): " + id);
        }
        return id.substring(0, delimiter);
    }

    /**
     * Gets the numeric value of an ID in IDs like `example_label:1215129489668055070`, where `1215129489668055070` is the ID.
     * The delimiter is `:`.
     * @param id the ID to get the numeric value of.
     * @param parser a function to parse the numeric part of the ID into a number.
     * @param <T> number type (Integer, Long, BigInteger, etc.).
     * @return the numeric value of the ID.
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
     * @param ids the collection of IDs.
     * @param startingNumber the starting ID number. Most IDs start at 0 or 1.
     * @param comparator function to compare two values.
     * @param incrementer function to increment the next available ID.
     * @param <T> number type (Integer, Long, BigInteger, etc.).
     * @return the first available ID, starting from {@code startingNumber}.
     */
    @PublicApi
    public static <T> T getFirstAvailableId(
        Collection<T> ids,
        T startingNumber,
        Comparator<? super T> comparator,
        Function<T, T> incrementer
    ) {
        if (ids == null || ids.isEmpty()) {
            return startingNumber;
        }
        PriorityQueue<T> queue = new PriorityQueue<>(comparator);
        for (T id : ids) {
            if (id != null) {
                queue.offer(id);
            }
        }
        T expected = startingNumber;
        while (!queue.isEmpty()) {
            T current = queue.poll();
            int comparison = comparator.compare(current, expected);
            if (comparison > 0) {
                return expected;
            } else if (comparison == 0) {
                expected = incrementer.apply(expected);
            }
        }
        return expected;
    }
}
