package com.semokin.app.adapter.config.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DatetimeUtil {

    private final String TIMEZONE;

    public DatetimeUtil(
            @Value("${app.semokincare.properties.date_format}") String timezone
    ) {
        TIMEZONE = timezone;
    }

    public String generateDatetime(Long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));

        return dateFormat.format(new Date(timestamp));
    }

    public String generateDatetime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));

        return dateFormat.format(new Date());
    }
}
