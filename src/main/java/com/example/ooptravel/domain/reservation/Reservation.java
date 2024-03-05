package com.example.ooptravel.domain.reservation;

import static com.example.ooptravel.domain.reservation.Reservation.ReservationStatus.ACCEPT;
import static com.example.ooptravel.domain.reservation.Reservation.ReservationStatus.CHECKIN;
import static com.example.ooptravel.domain.reservation.Reservation.ReservationStatus.CHECKOUT;

import com.example.ooptravel.domain.hotel.Hotel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.springframework.data.annotation.CreatedDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Reservation {

    public enum ReservationStatus {
        ACCEPT, CHECKIN, CHECKOUT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;

    @OneToMany
    @JoinColumn(name = "RESERVATION_ID")
    private List<ReservationLineRoom> reservationLineRooms = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Reservation(Long userId, Hotel hotel, List<ReservationLineRoom> reservationLineRooms,
                       ReservationStatus reservationStatus
    ) {
        this.userId = userId;
        this.hotel = hotel;
        this.reservationLineRooms = reservationLineRooms;
        this.reservationStatus = reservationStatus;
    }

    public void accept() {
        this.reservationStatus = ACCEPT;
    }

    public void checkIn() {
        this.reservationStatus = CHECKIN;
    }

    public void checkOut() {
        this.reservationStatus = CHECKOUT;
    }

}
