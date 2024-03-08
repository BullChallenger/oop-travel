package com.example.ooptravel.domain.hotel;

import com.example.ooptravel.domain.generic.money.Money;
import com.example.ooptravel.domain.generic.time.DateTimePeriod;
import com.example.ooptravel.domain.reservation.ReservationOptionGroup;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROOM_ID")
    private List<HotelOptionGroup> hotelOptionGroups = new ArrayList<>();

    private String name;
    private int standardNumberOfPeople;
    private int maximumNumberOfPeople;

    @Embedded
    private DateTimePeriod period;

    @Embedded
    private Bed bed;

    @Builder
    public Room(HotelOptionGroup basicAccommodationFee, List<HotelOptionGroup> hotelOptionGroups, String name, int standardNumberOfPeople,
                int maximumNumberOfPeople, DateTimePeriod period, Bed bed
    ) {
        this.hotelOptionGroups.add(basicAccommodationFee);
        this.hotelOptionGroups.addAll(hotelOptionGroups);
        this.name = name;
        this.standardNumberOfPeople = standardNumberOfPeople;
        this.maximumNumberOfPeople = maximumNumberOfPeople;
        this.period = period;
        this.bed = bed;
    }

    public Money getBaseAccommodationFee() {
        return getBasicOptionHotelService().getOptionSpecs().get(0).getPrice();
    }

    private HotelOptionGroup getBasicOptionHotelService() {
        return this.hotelOptionGroups.stream()
                .filter(HotelOptionGroup::isBasic)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public void validate(String roomName, List<OptionGroup> optionGroups, DateTimePeriod reservationPeriod) {
        if (!name.equals(roomName)) {
            throw new IllegalArgumentException("예약한 방의 이름과 호텔의 방 이름이 일치하지 않습니다.");
        }

        if (!period.equals(reservationPeriod)) {
            throw new IllegalArgumentException("예약한 방의 체크인/아웃 시간과 실제 체크인/아웃 시간이 일치하지 않습니다.");
        }

        if (!isSatisfiedBy(optionGroups)) {
            throw new IllegalArgumentException("예약 옵션과 실제 제공되는 옵션에 차이가 존재합니다.");
        }
    }

    private boolean isSatisfiedBy(List<OptionGroup> optionGroups) {
        return optionGroups.stream().allMatch(this::isSatisfiedBy);
    }

    private boolean isSatisfiedBy(OptionGroup optionGroup) {
        return hotelOptionGroups.stream().anyMatch(hotelOptionGroup -> hotelOptionGroup.isSatisfiedBy(optionGroup));
    }

}