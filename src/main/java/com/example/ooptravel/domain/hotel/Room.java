package com.example.ooptravel.domain.hotel;

import com.example.ooptravel.domain.generic.money.Money;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<HotelServiceGroup> hotelServiceGroups = new ArrayList<>();

    private String name;
    private int standardNumberOfPeople;
    private int maximumNumberOfPeople;

    @Embedded
    private Bed bed;

    public Money getBaseAccommodationFee() {
        return getBasicOptionHotelService().getOptionSpecs().get(0).getPrice();
    }

    private HotelServiceGroup getBasicOptionHotelService() {
        return this.hotelServiceGroups.stream()
                .filter(HotelServiceGroup::isBasic)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

}