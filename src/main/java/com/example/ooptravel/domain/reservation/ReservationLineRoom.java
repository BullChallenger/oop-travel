package com.example.ooptravel.domain.reservation;

import jakarta.persistence.Column;
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
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation reservation;

    @Column(name = "ROOM_NAME")
    private String name;

    private int period;

    @OneToMany
    @JoinColumn(name = "RESERVATION_LINE_ROOM_ID")
    private List<ReservationOptionGroup> reservationOptionGroups = new ArrayList<>();

    @Builder
    public ReservationLineRoom(Reservation reservation, String name, int period,
                               List<ReservationOptionGroup> reservationOptionGroups
    ) {
        this.reservation = reservation;
        this.name = name;
        this.period = period;
        this.reservationOptionGroups = reservationOptionGroups;
    }

}
