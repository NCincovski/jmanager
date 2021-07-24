package com.dev.nc.jmanager.utilities;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility helper for handling time related operations
 */
public class TimeUtil {
    public static String formatZonedDateTime(ZonedDateTime zonedDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
        return zonedDateTime.format(formatter);
    }
}
