package dev.ballgitte.utils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for time-related operations.
 */
public final class TimeUtils {
    private static final Object[][] units = {
            {ChronoUnit.YEARS, 365L * 24 * 3600 * 1_000_000_000L, "y"},
            {ChronoUnit.MONTHS, (365L * 24 * 3600 / 12) * 1_000_000_000L, "mo"},
            {ChronoUnit.DAYS, 24L * 3600 * 1_000_000_000L, "d"},
            {ChronoUnit.HOURS, 3600L * 1_000_000_000L, "h"},
            {ChronoUnit.MINUTES, 60L * 1_000_000_000L, "min"},
            {ChronoUnit.SECONDS, 1_000_000_000L, "s"},
            {ChronoUnit.MILLIS, 1_000_000L, "ms"},
            {ChronoUnit.MICROS, 1_000L, "μs"},
            {ChronoUnit.NANOS, 1L, "ns"}
        };

    private TimeUtils() {}

    /**
     * Converts a duration to a string representation from the specified unit.
     * @param duration The duration to convert
     * @param unit The last unit to display
     * @return The string representation of the duration
     * <p>Examples:
     * <pre>
     *     {@code getTimeFromUnit(Duration.ofHours(24 * 14), ChronoUnit.DAYS)} :
     *         returns "14d"
     *     {@code getTimeFromUnit(Duration.ofHours(24 * 14), ChronoUnit.HOURS)} :
     *         returns "14d0hr"
     *     &gt; Because ChronoUnit.HOURS is used, returns the hour even if it's 0
     * </pre>
     */
    @PublicApi
    public static String getTimeFromUnit(Duration duration, ChronoUnit unit) {
        long remainingNanos = duration.toNanos();
        StringBuilder sb = new StringBuilder();
        for (Object[] unitObject : units) {
            ChronoUnit unit1 = (ChronoUnit) unitObject[0];
            long unitNanos = (Long) unitObject[1];
            String unitSuffix = (String) unitObject[2];
            long value = remainingNanos / unitNanos;
            remainingNanos %= unitNanos;
            if (unit1 == unit || value > 0) {
                sb.append(value).append(unitSuffix);
                if (unit1 == unit) {
                    break;
                }
            }
        }
        return sb.toString();
    }
}
