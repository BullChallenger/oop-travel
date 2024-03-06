package com.example.ooptravel.api.service.hotel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ooptravel.Fixtures;
import com.example.ooptravel.api.service.hotel.response.HotelRoom;
import com.example.ooptravel.domain.hotel.repository.HotelRepository;
import com.example.ooptravel.domain.hotel.repository.RoomRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class HotelServiceTest {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	HotelService hotelService;

	@Test
	void test() {
		roomRepository.save(Fixtures.roomBuilder());
		hotelRepository.save(Fixtures.hotelBuilder());

		HotelRoom hotelRoom = hotelService.readHotelRooms(1L);
		System.out.println(hotelRoom);
	}

}