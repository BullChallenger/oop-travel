package com.example.ooptravel.domain.reservation;

import com.example.ooptravel.domain.generic.money.Money;
import com.example.ooptravel.domain.generic.time.DateTimePeriod;
import com.example.ooptravel.domain.hotel.OptionGroup;
import com.example.ooptravel.domain.hotel.Room;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReservationLineRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_LINE_ROOM_ID")
    private Long id;

    private Long roomId;
    private String roomName;

    @Embedded
    private DateTimePeriod period;

    @OneToMany
    @JoinColumn(name = "RESERVATION_LINE_ROOM_ID")
    private List<ReservationOptionGroup> reservationOptionGroups = new ArrayList<>();

    @Builder
    public ReservationLineRoom(Long roomId, String roomName, DateTimePeriod period, List<ReservationOptionGroup> reservationOptionGroups) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.period = period;
        this.reservationOptionGroups = reservationOptionGroups;
    }

    public Money calculateTotalAmountOfRoomReservation() {
        return Money.sum(reservationOptionGroups, ReservationOptionGroup::calculateTotalPrice);
    }

}
