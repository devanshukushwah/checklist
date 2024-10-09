package com.checklist.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Utility class for date operations.
 * Provides methods to manipulate dates.
 */
public class DateUtil {
    
    /**
     * Adds a specified number of days to a given date.
     *
     * @param date the original date to which days will be added
     * @param noOfDay the number of days to add (can be negative to subtract)
     * @return a new Date object with the specified number of days added
     */
    public static Date addDay(Date date, int noOfDay) {
        // Use Calendar to add one day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, noOfDay);
        
        return calendar.getTime();
    }
}
