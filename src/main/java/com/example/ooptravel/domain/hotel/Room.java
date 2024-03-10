package com.example.ooptravel.domain.hotel;

import com.example.ooptravel.domain.generic.money.Money;
import com.example.ooptravel.domain.generic.time.DateTimePeriod;

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
    private Long hotelId;

    @Embedded
    private DateTimePeriod period;

    @Embedded
    private Bed bed;

    @Builder
    public Room(HotelOptionGroup basicAccommodationFee, List<HotelOptionGroup> hotelOptionGroups, String name, int standardNumberOfPeople,
                int maximumNumberOfPeople, Long hotelId, DateTimePeriod period, Bed bed
    ) {
        this.hotelOptionGroups.add(basicAccommodationFee);
        this.hotelOptionGroups.addAll(hotelOptionGroups);
        this.name = name;
        this.standardNumberOfPeople = standardNumberOfPeople;
        this.maximumNumberOfPeople = maximumNumberOfPeople;
        this.hotelId = hotelId;
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

}