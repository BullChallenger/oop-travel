package com.example.ooptravel.api.service.reservation;

import com.example.ooptravel.api.service.reservation.request.ReservationOrder;
import com.example.ooptravel.domain.reservation.Reservation;
import com.example.ooptravel.domain.reservation.ReservationValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ooptravel.domain.reservation.repository.ReservationRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final ReservationMapper reservationMapper;
	private final ReservationValidator reservationValidator;

	@Transactional
	public Reservation createReservation(ReservationOrder request) {
		Reservation reservation = reservationMapper.mapFrom(request);
		reservationValidator.validate(reservation);
		reservation.accept();
		return reservationRepository.save(reservation);
	}

	@Transactional
	public void hotelCheckIn(Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(EntityNotFoundException::new);
		reservation.checkIn();
	}

	@Transactional
	public void hotelCheckOut(Long reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(EntityNotFoundException::new);
		reservation.checkOut();
	}

}
