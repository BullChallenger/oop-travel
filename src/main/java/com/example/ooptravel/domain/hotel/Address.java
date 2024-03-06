package com.example.ooptravel.domain.hotel;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    private String address;
    private String postalCode;

    @Builder
    public Address(String address, String postalCode) {
        this.address = address;
        this.postalCode = postalCode;
    }

}
