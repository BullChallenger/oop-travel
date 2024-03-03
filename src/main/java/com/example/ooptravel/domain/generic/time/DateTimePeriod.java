package com.example.ooptravel.domain.generic.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateTimePeriod {

    private LocalDateTime from;
    private LocalDateTime to;

    public DateTimePeriod(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public static DateTimePeriod between(LocalDateTime from, LocalDateTime to) {
        return new DateTimePeriod(from, to);
    }

    public boolean contains(LocalDateTime datetime) {
        return (datetime.isAfter(from) || datetime.equals(from)) &&
                (datetime.isBefore(to) || datetime.equals(to));
    }

    public long period() {
        return ChronoUnit.DAYS.between(from, to);
    }

}
