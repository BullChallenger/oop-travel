package com.example.ooptravel.domain.generic.time;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class DateTimePeriod {

    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;

    public DateTimePeriod(LocalDateTime from, LocalDateTime to) {
        this.checkInDateTime = from;
        this.checkOutDateTime = to;
    }

    public static DateTimePeriod between(LocalDateTime from, LocalDateTime to) {
        return new DateTimePeriod(from, to);
    }

    public boolean contains(LocalDateTime time) {
        return (time.isAfter(checkInDateTime) || time.equals(checkInDateTime)) &&
                (time.isBefore(checkOutDateTime) || time.equals(checkOutDateTime));
    }

    public boolean isRightScheduleTIme(LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        return checkInTime.equals(checkInDateTime) && checkOutTime.equals(checkOutDateTime);
    }

    public long period() {
        return ChronoUnit.DAYS.between(checkInDateTime, checkOutDateTime);
    }

}
