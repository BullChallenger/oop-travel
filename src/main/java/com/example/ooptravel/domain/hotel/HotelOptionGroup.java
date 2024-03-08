package com.example.ooptravel.domain.hotel;

import com.example.ooptravel.domain.reservation.ReservationOption;
import com.example.ooptravel.domain.reservation.ReservationOptionGroup;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HotelOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOTEL_SERVICE_GROUP_ID")
    private Long id;

    private String name;
    private boolean basic;
    private boolean exclusive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOTEL_SERVICE_GROUP_ID")
    private List<HotelOptionSpecification> optionSpecs = new ArrayList<>();

    @Builder
    public HotelOptionGroup(String name, boolean basic, boolean exclusive, List<HotelOptionSpecification> optionSpecs) {
        this.name = name;
        this.basic = basic;
        this.exclusive = exclusive;
        this.optionSpecs = optionSpecs;
    }

    public static HotelOptionGroup basic(String name, boolean exclusive, HotelOptionSpecification... options) {
        return new HotelOptionGroup(name, exclusive, true, options);
    }

    public static HotelOptionGroup exclusive(String name, boolean exclusive, HotelOptionSpecification... options) {
        return new HotelOptionGroup(name, exclusive, false, options);
    }

    public HotelOptionGroup(String name, boolean exclusive, boolean basic, HotelOptionSpecification... options) {
        this(name, exclusive, basic, Arrays.asList(options));
    }

    public boolean isSatisfiedBy(ReservationOptionGroup optionGroup) {
        return isSatisfied(optionGroup.getName(), satisfied(optionGroup.getReservationOptionSpecs()));
    }

    private boolean isSatisfied(String optionGroupName, List<ReservationOption> optionSpecs) {
        if (!name.equals(optionGroupName)) {
            System.out.println("Difference");
            System.out.println("호텔 옵션 그룹의 이름 : " + name);
            System.out.println("예약 옵션 그룹의 이름 : " + optionGroupName);
            return false;
        }

        System.out.println("SAME");
        System.out.println("호텔 옵션 그룹의 이름 : " + name);
        System.out.println("예약 옵션 그룹의 이름 : " + optionGroupName);

        if (optionSpecs.isEmpty()) {
            return false;
        }

        if (exclusive && optionSpecs.size() > 1) {
            return false;
        }

        return true;
    }

    private List<ReservationOption> satisfied(List<ReservationOption> reservationOptionSpecs) {
        return optionSpecs.stream()
            .flatMap(optionSpec -> reservationOptionSpecs.stream().filter(optionSpec::isSatisfiedBy))
            .toList();
    }

}
