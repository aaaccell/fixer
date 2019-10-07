package com.aaaccell.fixer.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class when dealing with date periods
 */
public class LocalDateHelper {

    /**
     * Segmentation of start and end dates according to a given delta
     *
     * @param startDate startDate
     * @param endDate   endDate
     * @param delta     threshold between start and end dates
     * @return list of start and end date pairs
     */
    public static List<Tuple<LocalDate, LocalDate>> splitDateRange(LocalDate startDate, LocalDate endDate, Duration delta) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before or equal to end date.");
        }
        List<Tuple<LocalDate, LocalDate>> segments = new ArrayList<>();
        while (startDate.until(endDate, ChronoUnit.DAYS) + 1 > delta.toDays()) {
            LocalDate endDateTmp = startDate.plusDays(delta.toDays());
            segments.add(new Tuple<>(startDate, endDateTmp));
            startDate = endDateTmp.plusDays(1);
        }
        if (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
            segments.add(new Tuple<>(startDate, endDate));
        }
        return segments;
    }
}
