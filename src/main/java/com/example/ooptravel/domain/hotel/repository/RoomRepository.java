package com.example.ooptravel.domain.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ooptravel.domain.hotel.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
