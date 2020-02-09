package org.solarsystem.web.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JDday {
    public static double getTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(time, formatter);
        int day = localDate.getDayOfMonth();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();
        return day - 32075
                + 1461 * (year + 4800 + (month - 14) / 12) / 4
                + 367 * (month - 2 - (month - 14) / 12 * 12) / 12
                - 3 * ((year + 4900 + (month - 14) / 12) / 100) / 4;


    }
}
