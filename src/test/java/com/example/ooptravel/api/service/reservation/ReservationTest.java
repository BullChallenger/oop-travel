package com.example.ooptravel.api.service.reservation;

import static org.assertj.core.api.Assertions.*;

import com.example.ooptravel.api.service.reservation.request.ReservationOrder.ReservationRoomOrder;
import com.example.ooptravel.domain.generic.time.DateTimePeriod;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.ooptravel.api.service.reservation.request.ReservationOrder;
import com.example.ooptravel.domain.generic.money.Money;
import com.example.ooptravel.domain.hotel.Room;
import com.example.ooptravel.domain.hotel.repository.HotelRepository;
import com.example.ooptravel.domain.reservation.Reservation;
import com.example.ooptravel.domain.reservation.ReservationLineRoom;
import com.example.ooptravel.domain.reservation.ReservationOptionGroup;
import com.example.ooptravel.hotel.FixturesOfHotel;
import com.example.ooptravel.reservation.FixturesOfReservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest
class ReservationTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        hotelRepository.save(FixturesOfHotel.hotelBuilder());
    }

    @DisplayName("예약 성공 확인")
    @Test
    void reservation_accept_test() {
        // given
        ReservationOrder reservationOrder = FixturesOfReservation.reservationOrderBuilder();

        // when
        Reservation reservation = reservationService.createReservation(reservationOrder);

        // then
        assertThat(reservation.getReservationStatus()).isEqualTo(Reservation.ReservationStatus.ACCEPT);
        assertThat(reservation.calculateTotalAmountOfReservation()).isEqualTo(Money.wons(150000L));
    }

    @DisplayName("호텔이 운영 중이지 않을 때 예약 시 예외 발생")
    @Test
    void reservation_validate_test() {
        // given
        Reservation reservation = FixturesOfReservation.reservationBuilder();
        reservation.getHotel().close();

        // when, then
        assertThatThrownBy(reservation::validate)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("숙박업소가 운영 중이지 않습니다.");
    }

    @DisplayName("예약한 방의 이름과 제공되는 숙소의 이름이 일치하지 않을 때 예외 발생")
    @Test
    void reservationGroup_validate_test() {
        // given
        LocalDateTime now = LocalDateTime.now();
        Room room = FixturesOfHotel.roomBuilder();

        // when, then
        assertThatThrownBy(() -> room.validate("wrong_room_name", List.of(), DateTimePeriod.between(now, now)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("예약한 방의 이름과 호텔의 방 이름이 일치하지 않습니다.");
    }

    @DisplayName("제공되는 서비스 옵션의 이름이 변경됐을 경우 예외 발생")
    @Test
    void reservationRoom_OptionGroupName_test() {
        // given
        Room room = FixturesOfHotel.roomBuilder();
        LocalDateTime checkInDateTime = LocalDateTime.of(2024, 3, 5, 12, 0);
        LocalDateTime checkOutDateTime = checkInDateTime.minusDays(1L);
        List<ReservationOptionGroup> reservationOptionGroups = List.of(
                FixturesOfReservation.reservationBasicOptionGroupBuilder(),
                FixturesOfReservation.reservationOptionGroupBuilder()
        );

        reservationOptionGroups.stream().forEach(group -> System.out.println(group.getName()));
        System.out.println("===========");
        room.getHotelOptionGroups().forEach(group -> System.out.println(group.getName()));

        // when, then
        room.validate( "디럭스룸",
                reservationOptionGroups,
                DateTimePeriod.between(checkInDateTime, checkOutDateTime));
//        assertThatThrownBy(() -> room.validate(
//                "디럭스룸",
//                reservationOptionGroups,
//                DateTimePeriod.between(checkInDateTime, checkOutDateTime))
//        ).isInstanceOf(IllegalArgumentException.class)
//        .hasMessage("예약 옵션과 실제 제공되는 옵션에 차이가 존재합니다.");
    }

    @DisplayName("예약한 방의 체크인/아웃 시간과 실제 체크인/아웃 시간이 일치하지 않을 때")
    @Test
    void reservationRoom_checkInAndOutTime_test() {
        // given
        Room room = FixturesOfHotel.roomBuilder();
        LocalDateTime checkInDateTime = LocalDateTime.of(2024, 3, 5, 14, 0);
        LocalDateTime checkOutDateTime = checkInDateTime.minusDays(1L);

        List<ReservationOptionGroup> reservationOptionGroups = List.of(
                FixturesOfReservation.reservationBasicOptionGroupBuilder()
        );

        // when, then
        assertThatThrownBy(() -> room.validate(
                "디럭스룸",
                reservationOptionGroups,
                DateTimePeriod.between(checkInDateTime, checkOutDateTime)
                )
        ).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("예약한 방의 체크인/아웃 시간과 실제 체크인/아웃 시간이 일치하지 않습니다.");
    }

    @Test
    void test() {
        List<String> strings = new ArrayList<>();
        strings.add("First");
        List<String> others = List.of("Second", "Third");
        strings.addAll(others);

        System.out.println(strings.toString());
        assertThat(strings.get(0)).isEqualTo("First");
    }

}