package com.example.ooptravel.api.service.hotel.response;

import java.util.List;

import com.example.ooptravel.domain.generic.money.Money;
import com.example.ooptravel.domain.hotel.Address;
import com.example.ooptravel.domain.hotel.Bed;
import com.example.ooptravel.domain.hotel.Hotel;
import com.example.ooptravel.domain.hotel.Room;

import lombok.Getter;

@Getter
public class HotelRoom {

	private final Long hotelId;
	private final String hotelName;
	private final String description;
	private final Address address;
	private final List<RoomItem> roomItems;

	public HotelRoom(Hotel hotel) {
		this.hotelId = hotel.getId();
		this.hotelName = hotel.getName();
		this.description = hotel.getDescription();
		this.address = hotel.getAddress();
		this.roomItems = convertToRoomItem(hotel.getRooms());
	}

	private List<RoomItem> convertToRoomItem(List<Room> rooms) {
		return rooms.stream().map(RoomItem::new).toList();
	}

	@Getter
	public static class RoomItem {
		private final Long roomId;
		private final String name;
		private final int standardNumberOfPeople;
		private final int maximumNumberOfPeople;
		private final Bed bed;
		private final Money accommodationFee;

		public RoomItem(Room room) {
			this.roomId = room.getId();
			this.name = room.getName();
			this.standardNumberOfPeople = room.getStandardNumberOfPeople();
			this.maximumNumberOfPeople = room.getMaximumNumberOfPeople();
			this.bed = room.getBed();
			this.accommodationFee = room.getBaseAccommodationFee();
		}
	}

}
