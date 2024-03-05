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

    private LocalTime from;
    private LocalTime to;

    public DateTimePeriod(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    public static DateTimePeriod between(LocalTime from, LocalTime to) {
        return new DateTimePeriod(from, to);
    }

    public boolean contains(LocalTime time) {
        return (time.isAfter(from) || time.equals(from)) &&
                (time.isBefore(to) || time.equals(to));
    }

    public boolean isRightScheduleTIme(LocalTime checkInTime, LocalTime checkOutTime) {
        return checkInTime.equals(from) && checkOutTime.equals(to);
    }

    public long period() {
        return ChronoUnit.DAYS.between(from, to);
    }

}
