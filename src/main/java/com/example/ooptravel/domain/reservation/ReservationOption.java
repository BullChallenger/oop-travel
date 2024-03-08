package com.example.ooptravel.domain.reservation;

import com.example.ooptravel.domain.generic.money.Money;
import com.example.ooptravel.domain.hotel.OptionSpecification;
import com.example.ooptravel.infra.generic.money.MoneyConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ReservationOption {

    private String name;

    @Convert(converter = MoneyConverter.class)
    private Money price;

    @Builder
    public ReservationOption(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public OptionSpecification convertToOptionSpecification() {
        return OptionSpecification.builder()
            .name(name)
            .price(price)
            .build();
    }

}
