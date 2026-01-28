package dev.ballgitte.utils;

/**
 * Interface for marking the range of valid values.
 */
@PublicApi
public @interface Range {
    /**
     * Minimum value of the range.
     * @return the minimum value of the range
     */
    int min();

    /**
     * Maximum value of the range.
     * @return the maximum value of the range
     */
    int max();
}
