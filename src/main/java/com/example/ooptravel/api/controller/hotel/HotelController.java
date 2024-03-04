package com.example.ooptravel.api.controller.hotel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ooptravel.api.service.hotel.HotelService;
import com.example.ooptravel.api.service.hotel.response.HotelRoom;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/hotels")
@RestController
public class HotelController {

	private final HotelService hotelService;

	@GetMapping(value = "/{hotelId}")
	public ResponseEntity<HotelRoom> getHotelRooms(@PathVariable(value = "hotelId") Long hotelId) {
		return ResponseEntity.ok(hotelService.readHotelRooms(hotelId));
	}

}
