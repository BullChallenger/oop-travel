package com.example.ooptravel.domain.hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ooptravel.domain.hotel.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

	// Optional<List<Room>> findByHotelId(Long hotelId);

}
