package com.example.ooptravel.domain.reservation;

import com.example.ooptravel.domain.generic.money.Money;
import com.example.ooptravel.domain.hotel.OptionGroup;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ReservationOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_OPTION_GROUP_ID")
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "RESERVATION_OPTION", joinColumns = @JoinColumn(name = "RESERVATION_OPTION_GROUP_ID"))
    private List<ReservationOption> reservationOptionSpecs = new ArrayList<>();

    @Builder
    public ReservationOptionGroup(String name, List<ReservationOption> reservationOptionSpecs) {
        this.name = name;
        this.reservationOptionSpecs = reservationOptionSpecs;
    }

    public Money calculateTotalPrice() {
        return Money.sum(reservationOptionSpecs, ReservationOption::getPrice);
    }

    public OptionGroup convertToOptionGroup() {
        return OptionGroup.builder()
            .groupName(name)
            .optionSpecs(reservationOptionSpecs.stream().map(ReservationOption::convertToOptionSpecification).toList())
            .build();
    }

}
