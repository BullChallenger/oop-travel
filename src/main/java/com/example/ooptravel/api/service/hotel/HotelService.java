package com.example.ooptravel.api.service.hotel;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ooptravel.api.service.hotel.response.HotelRoom;
import com.example.ooptravel.domain.hotel.Hotel;
import com.example.ooptravel.domain.hotel.repository.HotelRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

	private final HotelRepository hotelRepository;

	public HotelRoom readHotelRooms(Long hotelId) {
		Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(
			() -> new EntityNotFoundException("호텔 못찾음")
		);

		return new HotelRoom(hotel);
	}

}
