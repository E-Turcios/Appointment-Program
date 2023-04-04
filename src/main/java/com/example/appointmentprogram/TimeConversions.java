package com.example.appointmentprogram;
import java.time.*;

/**
 * @author Abdoulaye Boundy Djikine
 * The TimeConversions class provides methods for converting a given local date and time
 * to Eastern Time Zone.
 */
public class TimeConversions {
    /**
     * Converts a given local date and time to Eastern Time Zone.
     * @param date the local date to be converted
     * @param time the local time to be converted
     * @return the Eastern Time Zone representation of the given date and time
     */
    public static LocalTime convertToEasternTime(LocalDate date, LocalTime time){
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime convertedZone = zonedDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalTime easternTime = convertedZone.toLocalTime();
        return easternTime;
    }
    /**
     * Converts a given local date and time to Eastern Time Zone.
     * @param date the local date to be converted
     * @param time the local time to be converted
     * @return the Eastern Time Zone representation of the given date and time
     */
    public static LocalDate convertToEasternDate(LocalDate date, LocalTime time){
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime convertedZone = zonedDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDate easternDate = convertedZone.toLocalDate();
        return easternDate;
    }
}