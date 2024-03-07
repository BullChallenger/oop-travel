package com.example.ooptravel.api.service.reservation;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.ooptravel.api.service.reservation.request.ReservationOrder;
import com.example.ooptravel.domain.generic.money.Money;
import com.example.ooptravel.domain.hotel.repository.HotelRepository;
import com.example.ooptravel.domain.reservation.Reservation;
import com.example.ooptravel.hotel.FixturesOfHotel;
import com.example.ooptravel.reservation.FixturesOfReservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        hotelRepository.save(FixturesOfHotel.hotelBuilder());
    }

    @DisplayName("예약 성공 확인")
    @Test
    void reservation_accept_test() {
        // given
        ReservationOrder reservationOrder = FixturesOfReservation.reservationOrderBuilder();

        // when
        Reservation reservation = reservationService.createReservation(reservationOrder);

        // then
        assertThat(reservation.getReservationStatus()).isEqualTo(Reservation.ReservationStatus.ACCEPT);
        assertThat(reservation.calculateTotalAmountOfReservation()).isEqualTo(Money.wons(150000L));
    }

    @DisplayName("")
    @Test
    void reservation_validate_test() {
        // given
        Reservation reservation = FixturesOfReservation.reservationBuilder();
        reservation.getHotel().close();

        // when, then
        assertThatThrownBy(reservation::validate)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("숙박업소가 운영 중이지 않습니다.");
    }

}