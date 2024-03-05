package com.example.ooptravel.api.service.reservation;

import com.example.ooptravel.api.service.reservation.request.ReservationOrder;
import com.example.ooptravel.domain.reservation.Reservation;
import org.springframework.stereotype.Service;

import com.example.ooptravel.domain.reservation.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final ReservationMapper reservationMapper;

	public Reservation createReservation(ReservationOrder request) {
		Reservation reservation = reservationMapper.mapFrom(request);
		reservation.accept();
		return reservationRepository.save(reservation);
	}

}
