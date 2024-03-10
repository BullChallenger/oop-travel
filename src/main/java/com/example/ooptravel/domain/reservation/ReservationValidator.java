package com.example.ooptravel.domain.reservation;

import static java.util.function.Function.identity;

import com.example.ooptravel.api.service.reservation.request.ReservationOrder.ReservationOrderOptionGroup;
import com.example.ooptravel.domain.hotel.Hotel;
import com.example.ooptravel.domain.hotel.HotelOptionGroup;
import com.example.ooptravel.domain.hotel.Room;
import com.example.ooptravel.domain.hotel.repository.HotelRepository;
import com.example.ooptravel.domain.hotel.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReservationValidator {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public void validate(Reservation reservation) {
        validate(reservation, getHotel(reservation), getRooms(reservation));
    }

    private void validate(Reservation reservation, Hotel hotel, Map<Long, Room> rooms) {
            if (reservation.getReservationLineRooms().isEmpty()) {
                throw new IllegalArgumentException("방 예약 항목이 비었습니다.");
            }

            if (!hotel.isOpen()) {
                throw new IllegalArgumentException("숙박업소가 운영 중이지 않습니다.");
            }

            for (ReservationLineRoom reservationLineRoom : reservation.getReservationLineRooms()) {
                validateReservationLineRooms(reservationLineRoom, rooms.get(reservationLineRoom.getRoomId()));
            }
    }

    public void validateReservationLineRooms(ReservationLineRoom reservationLineRoom, Room room) {
        if (!room.getName().equals(reservationLineRoom.getRoomName())) {
            throw new IllegalArgumentException("예약한 방의 이름과 호텔의 방 이름이 일치하지 않습니다.");
        }

        if (!room.getPeriod().equals(reservationLineRoom.getPeriod())) {
            throw new IllegalArgumentException("예약한 방의 체크인/아웃 시간과 실제 체크인/아웃 시간이 일치하지 않습니다.");
        }

        for (ReservationOptionGroup reservationOrderOptionGroup : reservationLineRoom.getReservationOptionGroups()) {
            validateReservationOptionGroup(reservationOrderOptionGroup, room);
        }
    }

    private void validateReservationOptionGroup(ReservationOptionGroup reservationOrderOptionGroup, Room room) {
        for (HotelOptionGroup hotelOptionGroup : room.getHotelOptionGroups()) {
            if (hotelOptionGroup.isSatisfiedBy(reservationOrderOptionGroup.convertToOptionGroup())) {
                return;
            }
        }

        throw new IllegalArgumentException("예약 옵션과 실제 제공되는 옵션에 차이가 존재합니다.");
    }

    private Hotel getHotel(Reservation reservation) {
        return hotelRepository.findById(reservation.getHotelId()).orElseThrow(EntityNotFoundException::new);
    }

    private Map<Long, Room> getRooms(Reservation reservation) {
        return roomRepository.findAllById(reservation.getRoomIds()).stream()
                .collect(Collectors.toMap(Room::getId, identity()));
    }

}
