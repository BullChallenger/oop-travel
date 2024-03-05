package com.example.ooptravel.domain.reservation;

import com.example.ooptravel.api.service.reservation.request.ReservationOrder.ReservationOrderOptionGroup;
import com.example.ooptravel.api.service.reservation.request.ReservationOrder.ReservationRoomOrder;
import com.example.ooptravel.domain.generic.time.DateTimePeriod;
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

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @Embedded
    private DateTimePeriod period;

    @OneToMany
    @JoinColumn(name = "RESERVATION_LINE_ROOM_ID")
    private List<ReservationOptionGroup> reservationOptionGroups = new ArrayList<>();

    @Builder
    public ReservationLineRoom(Room room, DateTimePeriod period, List<ReservationOptionGroup> reservationOptionGroups) {
        this.room = room;
        this.period = period;
        this.reservationOptionGroups = reservationOptionGroups;
    }

}
