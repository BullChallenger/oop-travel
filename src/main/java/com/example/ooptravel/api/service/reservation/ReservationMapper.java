package com.example.ooptravel.api.service.reservation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.ooptravel.api.service.reservation.request.ReservationOrder;
import com.example.ooptravel.domain.generic.time.DateTimePeriod;
import com.example.ooptravel.domain.hotel.Hotel;
import com.example.ooptravel.domain.hotel.Room;
import com.example.ooptravel.domain.hotel.repository.HotelRepository;
import com.example.ooptravel.domain.hotel.repository.RoomRepository;
import com.example.ooptravel.domain.reservation.Reservation;
import com.example.ooptravel.domain.reservation.ReservationLineRoom;
import com.example.ooptravel.domain.reservation.ReservationOption;
import com.example.ooptravel.domain.reservation.ReservationOptionGroup;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ReservationMapper {

	private final HotelRepository hotelRepository;
	private final RoomRepository roomRepository;

	public Reservation mapFrom(ReservationOrder order) {
		Hotel hotel = hotelRepository.findById(order.getHotelId()).orElseThrow(EntityNotFoundException::new);

		return Reservation.builder()
			.userId(order.getUserId())
			.hotel(hotel)
			.reservationLineRooms(convertReservationLineRoomsBy(order))
			.build();
	}

	private ReservationLineRoom ofReservationOrderBy(ReservationOrder.ReservationRoomOrder roomOrder) {
		Room room = roomRepository.findById(roomOrder.getRoomId()).orElseThrow(EntityNotFoundException::new);

		return ReservationLineRoom.builder()
			.room(room)
			.period(DateTimePeriod.between(room.getCheckInDate(), room.getCheckOutDate()).period())
			.reservationOptionGroups(roomOrder.getOptionGroups().stream().map(this::ofReservationLineRoomBy).toList())
			.build();
	}

	private List<ReservationLineRoom> convertReservationLineRoomsBy(ReservationOrder request) {
		return request.getRoomOrders().stream().map(this::ofReservationOrderBy).toList();
	}

	private ReservationOptionGroup ofReservationLineRoomBy(ReservationOrder.ReservationOrderOptionGroup optionGroup) {
		return ReservationOptionGroup.builder()
			.name(optionGroup.getName())
			.reservationOptionSpecs(optionGroup.getOptionSpecs().stream().map(this::ofReservationOptionGroupBy).toList())
			.build();
	}

	private ReservationOption ofReservationOptionGroupBy(ReservationOrder.ReservationOrderOption optionSpec) {
		return ReservationOption.builder()
			.name(optionSpec.getName())
			.price(optionSpec.getPrice())
			.build();
	}

}
