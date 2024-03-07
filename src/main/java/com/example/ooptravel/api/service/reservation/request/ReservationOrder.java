package com.example.ooptravel.api.service.reservation.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.ooptravel.domain.generic.money.Money;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationOrder {

	private final Long userId;
	private final Long hotelId;
	private final List<ReservationRoomOrder> roomOrders;

	@Builder
	public ReservationOrder(Long userId, Long hotelId, List<ReservationRoomOrder> orders) {
		this.userId = userId;
		this.hotelId = hotelId;
		this.roomOrders = orders;
	}

	@Getter
	public static class ReservationRoomOrder {
		private final Long roomId;
		private final LocalDateTime checkInDateTime;
		private final LocalDateTime checkOutDateTime;
		private final List<ReservationOrderOptionGroup> optionGroups;

		@Builder
		public ReservationRoomOrder(Long roomId, List<ReservationOrderOptionGroup> optionGroups,
									LocalDateTime checkInDateTime, LocalDateTime checkOutDateTime
		) {
			this.roomId = roomId;
			this.optionGroups = optionGroups;
			this.checkInDateTime = checkInDateTime;
			this.checkOutDateTime = checkOutDateTime;
		}
	}

	@Getter
	public static class ReservationOrderOptionGroup {
		private final String name;
		private final List<ReservationOrderOption> optionSpecs = new ArrayList<>();

		public ReservationOrderOptionGroup(String name, ReservationOrderOption... orderOptions) {
			this.name = name;
			this.optionSpecs.addAll(Arrays.asList(orderOptions));
		}
	}

	@Getter
	public static class ReservationOrderOption {
		private final String name;
		private final Money price;

		public ReservationOrderOption(String name, Money price) {
			this.name = name;
			this.price = price;
		}
	}

}
