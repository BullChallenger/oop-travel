package com.example.ooptravel.api.service.reservation;

import com.example.ooptravel.api.service.reservation.request.ReservationOrder;
import com.example.ooptravel.domain.hotel.Hotel;
import com.example.ooptravel.domain.hotel.repository.HotelRepository;
import com.example.ooptravel.domain.reservation.Reservation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ooptravel.domain.reservation.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final HotelRepository hotelRepository;

	public void createReservation(ReservationOrder request) {

	}

}
