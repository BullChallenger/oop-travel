package com.example.ooptravel.domain.hotel;

import com.example.ooptravel.domain.generic.money.Money;
import com.example.ooptravel.domain.reservation.ReservationOption;
import com.example.ooptravel.infra.generic.money.MoneyConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HotelOptionSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOTEL_SERVICE_ID")
    private Long id;

    private String name;
    private boolean isAvailable;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    @Builder
    public HotelOptionSpecification(String name, boolean isAvailable, Money price) {
        this.name = name;
        this.isAvailable = isAvailable;
        this.price = price;
    }

    public boolean isSatisfiedBy(ReservationOption reservationOptionSpec) {
        return isAvailable &&
            reservationOptionSpec.getName().equals(name) &&
            reservationOptionSpec.getPrice().isEqualTo(price);
    }

}
