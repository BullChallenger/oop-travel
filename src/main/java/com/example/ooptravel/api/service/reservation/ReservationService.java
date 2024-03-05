package com.example.ooptravel.api.service.reservation;

import com.example.ooptravel.domain.hotel.repository.HotelRepository;
import org.springframework.stereotype.Service;

import com.example.ooptravel.domain.reservation.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final HotelRepository hotelRepository;

}
