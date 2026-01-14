package dev.ballgitte.utils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public final class TimeUtils {

    private TimeUtils() {}

    @PublicApi
    public static String getTimeFromUnit(Duration duration, ChronoUnit unit) {
        long totalNanos = duration.toNanos();
        Object[][] units = {
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
        StringBuilder sb = new StringBuilder();
        for (Object[] unit2 : units) {
            ChronoUnit unit3 = (ChronoUnit) unit2[0];
            long uNanos = (Long) unit2[1];
            String suffix = (String) unit2[2];
            long value = totalNanos / uNanos;
            totalNanos %= uNanos;
            if (unit3 == unit || value > 0) {
                sb.append(value).append(suffix);
                if (unit3 == unit) {
                    break;
                }
            }
        }
        return sb.toString();
    }
}
