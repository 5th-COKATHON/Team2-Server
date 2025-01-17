package com.cotato.team2.team2.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtil {

    public static final String ZONE_ID = "Asia/Seoul";

    public static LocalDate getToday() {
        ZoneId zoneId = ZoneId.of(ZONE_ID);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        return zonedDateTime.toLocalDate();
    }
}
