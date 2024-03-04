package com.example.ooptravel.domain.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ooptravel.domain.hotel.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
