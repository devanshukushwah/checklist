package com.checklist.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date addDay(Date date, int noOfDay) {
        // Use Calendar to add one day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, noOfDay);
        
        return calendar.getTime();
	}
}
