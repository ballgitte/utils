package dev.ballgitte.utils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

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

    @PublicApi
    public static String getTimeFromUnit(Duration duration, ChronoUnit unit) {
        long remainingNanos = duration.toNanos();
        StringBuilder sb = new StringBuilder();
        for (Object[] unitObject : units) {
            ChronoUnit unit2 = (ChronoUnit) unitObject[0];
            long unitNanos = (Long) unitObject[1];
            String unitSuffix = (String) unitObject[2];
            long value = remainingNanos / unitNanos;
            remainingNanos %= unitNanos;
            if (unit2 == unit || value > 0) {
                sb.append(value).append(unitSuffix);
                if (unit2 == unit) {
                    break;
                }
            }
        }
        return sb.toString();
    }
}
