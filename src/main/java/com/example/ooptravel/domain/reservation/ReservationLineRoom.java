package com.example.ooptravel.domain.reservation;

import com.example.ooptravel.domain.generic.money.Money;
import com.example.ooptravel.domain.generic.time.DateTimePeriod;
import com.example.ooptravel.domain.hotel.Room;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
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

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    private String roomName;

    @OneToMany
    @JoinColumn(name = "RESERVATION_LINE_ROOM_ID")
    private List<ReservationOptionGroup> reservationOptionGroups = new ArrayList<>();

    @Builder
    public ReservationLineRoom(Room room, String roomName, List<ReservationOptionGroup> reservationOptionGroups) {
        this.room = room;
        this.roomName = roomName;
        this.reservationOptionGroups = reservationOptionGroups;
    }

    public Money calculateTotalAmountOfRoomReservation() {
        return Money.sum(reservationOptionGroups, ReservationOptionGroup::calculateTotalPrice);
    }

    public void validate() {
        room.validate(roomName, reservationOptionGroups);
    }

}
