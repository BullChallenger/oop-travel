package com.example.ooptravel.api.service.hotel;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ooptravel.api.service.hotel.response.HotelRoom;
import com.example.ooptravel.domain.hotel.Hotel;
import com.example.ooptravel.domain.hotel.Room;
import com.example.ooptravel.domain.hotel.repository.HotelRepository;
import com.example.ooptravel.domain.hotel.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HotelService {

	private final HotelRepository hotelRepository;

	@Transactional(readOnly = true)
	public HotelRoom readHotelRooms(Long hotelId) {
		Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(
			() -> new EntityNotFoundException("호텔 못찾음")
		);

		return new HotelRoom(hotel);
	}

}
