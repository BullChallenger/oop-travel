package com.example.ooptravel;

import java.time.LocalDateTime;
import java.util.List;

import com.example.ooptravel.domain.generic.time.DateTimePeriod;
import com.example.ooptravel.domain.hotel.Address;
import com.example.ooptravel.domain.hotel.Hotel;
import com.example.ooptravel.domain.hotel.HotelOptionGroup;
import com.example.ooptravel.domain.hotel.HotelOptionSpecification;
import com.example.ooptravel.domain.hotel.Room;

public class Fixtures {

	public static Hotel hotelBuilder() {
		return Hotel.builder()
			.address(addressBuilder())
			.rooms()
			.name()
			.description()
			.open()
			.build();
	}

	public static Room roomBuilder() {
		LocalDateTime checkInDateTime = LocalDateTime.of(2024, 3, 5, 12, 0);
		LocalDateTime checkOutDateTime = checkInDateTime.minusDays(1L);

		return Room.builder()
			.hotelOptionGroups(List.of(hotelOptionGroup01Builder(), hotelOptionGroup02Builder()))
			.name("디럭스룸")
			.standardNumberOfPeople(2)
			.maximumNumberOfPeople(3)
			.period(DateTimePeriod.between(checkInDateTime,  checkOutDateTime))
			.build();
	}

	private static Address addressBuilder() {
		return Address.builder()
			.address("서울특별시 상암동 올림픽대로")
			.postalCode("333333")
			.build();
	}

	private static HotelOptionGroup hotelOptionGroup01Builder() {
		return HotelOptionGroup.builder()
			.name("이용 가능 시설 목록")
			.basic(true)
			.exclusive(true)
			.optionSpecs(List.of(
					hotelOptionSpecification01Builder(),
					hotelOptionSpecification02Builder(),
					hotelOptionSpecification03Builder())
			)
			.build();
	}

	private static HotelOptionGroup hotelOptionGroup02Builder() {
		return HotelOptionGroup.builder()
			.name("룸 서비스 목록")
			.basic(false)
			.exclusive(false)
			.optionSpecs(List.of(
				hotelOptionSpecification04Builder(),
				hotelOptionSpecification05Builder(),
				hotelOptionSpecification06Builder())
			)
			.build();
	}

	private static HotelOptionSpecification hotelOptionSpecification01Builder() {
		return HotelOptionSpecification.builder()
			.name("수영장")
			.isAvailable(true)
			.build();
	}

	private static HotelOptionSpecification hotelOptionSpecification02Builder() {
		return HotelOptionSpecification.builder()
			.name("바베큐장")
			.isAvailable(true)
			.build();
	}

	private static HotelOptionSpecification hotelOptionSpecification03Builder() {
		return HotelOptionSpecification.builder()
			.name("샐러드바")
			.isAvailable(true)
			.build();
	}

	private static HotelOptionSpecification hotelOptionSpecification04Builder() {
		return HotelOptionSpecification.builder()
			.name("와인")
			.isAvailable(true)
			.build();
	}

	private static HotelOptionSpecification hotelOptionSpecification05Builder() {
		return HotelOptionSpecification.builder()
			.name("양주")
			.isAvailable(true)
			.build();
	}

	private static HotelOptionSpecification hotelOptionSpecification06Builder() {
		return HotelOptionSpecification.builder()
			.name("샐러드바")
			.isAvailable(true)
			.build();
	}

}
