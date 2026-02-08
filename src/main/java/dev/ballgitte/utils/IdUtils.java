package dev.ballgitte.utils;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

/**
 * Utility class for ID-related operations.
 */
@SuppressWarnings("unused")
public final class IdUtils {
    public static final int INVALID_ID = -1;
    public static final long INVALID_ID_LONG = INVALID_ID;

    public static final BigInteger INVALID_ID_BIG_INT = BigInteger.valueOf(INVALID_ID);

    public static final int UNASSIGNED_ID = -1;
    public static final long UNASSIGNED_ID_LONG = UNASSIGNED_ID;
    public static final BigInteger UNASSIGNED_ID_BIG_INT = BigInteger.valueOf(UNASSIGNED_ID);

    private IdUtils() {}

    /**
     * Gets the key of an ID in IDs like `1215129489668055070:example_value`, where `1215129489668055070` is the key.
     * The delimiter is `:`.
     * @param id the ID to get the key of.
     * @param parser a function to parse the numeric part of the ID into a number.
     * @param <T> number type (Integer, Long, BigInteger, etc.).
     * @return the key of the ID.
     */
    public static <T> T getKey(String id, Function<String, T> parser) {
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
     * Gets the value of an ID in IDs like `1294949:example_value`, where `example_value` is the value.
     * The delimiter is `:`.
     * @param id the ID to get the value of.
     * @return the value of the ID.
     */
    public static String getValue(String id) {
        int delimiter = id.indexOf(':');
        if (delimiter == -1) {
            throw new IllegalArgumentException("Invalid component ID (missing `:`): " + id);
        }
        return id.substring(0, delimiter);
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
