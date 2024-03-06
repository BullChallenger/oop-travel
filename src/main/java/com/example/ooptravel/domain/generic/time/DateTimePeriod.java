package com.example.ooptravel.domain.generic.time;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
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

    public boolean contains(LocalDateTime time) {
        return (time.isAfter(from) || time.equals(from)) &&
                (time.isBefore(to) || time.equals(to));
    }

    public boolean isRightScheduleTIme(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        return checkInTime.equals(from) && checkOutTime.equals(to);
    }

    public long period() {
        return ChronoUnit.DAYS.between(from, to);
    }

}
