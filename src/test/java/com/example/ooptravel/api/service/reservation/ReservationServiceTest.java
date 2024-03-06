package com.example.ooptravel.api.service.reservation;

import static org.junit.jupiter.api.Assertions.*;

import com.example.ooptravel.domain.reservation.Reservation;
import com.example.ooptravel.reservation.FixturesOfReservation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Test
    void reservationCreate() {
        Reservation reservation = FixturesOfReservation.reservationBuilder();
        System.out.println(reservation);
    }

}