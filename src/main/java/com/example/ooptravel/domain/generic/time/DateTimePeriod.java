package com.example.ooptravel.domain.generic.time;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import lombok.AccessLevel;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DateTimePeriod that = (DateTimePeriod) o;

        if (!Objects.equals(checkInDateTime, that.checkInDateTime)) {
            return false;
        }
        return Objects.equals(checkOutDateTime, that.checkOutDateTime);
    }

    @Override
    public int hashCode() {
        int result = checkInDateTime != null ? checkInDateTime.hashCode() : 0;
        result = 31 * result + (checkOutDateTime != null ? checkOutDateTime.hashCode() : 0);
        return result;
    }

}
