package com.example.ooptravel.reservation;

import com.example.ooptravel.api.service.reservation.request.ReservationOrder;
import com.example.ooptravel.domain.generic.money.Money;
import com.example.ooptravel.domain.reservation.Reservation;
import com.example.ooptravel.domain.reservation.Reservation.ReservationStatus;
import com.example.ooptravel.domain.reservation.ReservationLineRoom;
import com.example.ooptravel.domain.reservation.ReservationOption;
import com.example.ooptravel.domain.reservation.ReservationOptionGroup;
import com.example.ooptravel.hotel.FixturesOfHotel;

import java.time.LocalDateTime;
import java.util.List;

public class FixturesOfReservation {

    public static Reservation reservationBuilder() {
        return Reservation.builder()
            .userId(1L)
            .hotel(FixturesOfHotel.hotelBuilder())
            .reservationLineRooms(List.of(reservationLineRoomBuilder()))
            .reservationStatus(ReservationStatus.ACCEPT)
            .build();
    }

    private static ReservationLineRoom reservationLineRoomBuilder() {
        return ReservationLineRoom.builder()
            .room(FixturesOfHotel.roomBuilder())
            .roomName("디럭스룸")
            .reservationOptionGroups(List.of(reservationBasicOptionGroupBuilder(), reservationOptionGroupBuilder()))
            .build();
    }

    private static ReservationOptionGroup reservationBasicOptionGroupBuilder() {
        return ReservationOptionGroup.builder()
            .name("숙박 요금")
            .reservationOptionSpecs(List.of(
                ReservationOption.builder()
                    .name("기본 숙박 요금")
                    .price(Money.wons(100000))
                    .build()
            ))
            .build();
    }

    private static ReservationOptionGroup reservationOptionGroupBuilder() {
        return ReservationOptionGroup.builder()
            .name("이용 가능 시설 목록")
            .reservationOptionSpecs(List.of(reservationOptionBuilder01(), reservationOptionBuilder02()))
            .build();
    }

    private static ReservationOption reservationOptionBuilder01() {
        return ReservationOption.builder()
            .name("수영장")
            .price(Money.wons(50000L))
            .build();
    }

    private static ReservationOption reservationOptionBuilder02() {
        return ReservationOption.builder()
            .name("바베큐장")
            .price(Money.wons(50000L))
            .build();
    }

//

    public static ReservationOrder reservationOrderBuilder() {
        return ReservationOrder.builder()
            .userId(1L)
            .hotelId(1L)
            .orders(List.of(reservationRoomOrderBuilder()))
            .build();
    }

    public static ReservationOrder.ReservationRoomOrder reservationRoomOrderBuilder() {
        LocalDateTime checkInDateTime = LocalDateTime.of(2024, 3, 5, 12, 0);
        LocalDateTime checkOutDateTime = checkInDateTime.minusDays(1L);

        return ReservationOrder.ReservationRoomOrder.builder()
            .roomId(1L)
            .optionGroups(List.of(reservationBasicAccommodationOGBuilder(), reservationOGBuilder()))
            .checkInDateTime(checkInDateTime)
            .checkOutDateTime(checkOutDateTime)
            .build();
    }

    private static ReservationOrder.ReservationOrderOptionGroup reservationBasicAccommodationOGBuilder() {
        return new ReservationOrder.ReservationOrderOptionGroup("숙박 요금", reservationAccommodationBuilder());
    }

    private static ReservationOrder.ReservationOrderOption reservationAccommodationBuilder() {
        return new ReservationOrder.ReservationOrderOption("기본 숙박 요금", Money.wons(100000));
    }

    private static ReservationOrder.ReservationOrderOptionGroup reservationOGBuilder() {
        return new ReservationOrder.ReservationOrderOptionGroup("이용 가능 시설 목록", reservationOrderOptionBuilder());
    }

    private static ReservationOrder.ReservationOrderOption reservationOrderOptionBuilder() {
        return new ReservationOrder.ReservationOrderOption("수영장", Money.wons(50000L));
    }

}
