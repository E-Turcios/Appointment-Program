package com.example.appointmentprogram;
import java.time.*;

public class TimeConversions {

    public static LocalTime convertToEasternTime(LocalDate date, LocalTime time){
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime convertedZone = zonedDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalTime easternTime = convertedZone.toLocalTime();
        return easternTime;
    }
    public static LocalDate convertToEasternDate(LocalDate date, LocalTime time){
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime convertedZone = zonedDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalDate easternDate = convertedZone.toLocalDate();
        return easternDate;
    }
}