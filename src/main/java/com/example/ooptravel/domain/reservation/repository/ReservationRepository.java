package com.example.ooptravel.domain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ooptravel.domain.reservation.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
