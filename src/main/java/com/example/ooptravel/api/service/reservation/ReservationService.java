package com.example.ooptravel.api.service.reservation;

import com.example.ooptravel.api.service.reservation.request.ReservationOrder;
import com.example.ooptravel.domain.reservation.Reservation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ooptravel.domain.reservation.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final ReservationMapper reservationMapper;

	@Transactional
	public Reservation createReservation(ReservationOrder request) {
		Reservation reservation = reservationMapper.mapFrom(request);
		reservation.validate();
		reservation.accept();
		return reservationRepository.save(reservation);
	}

}
