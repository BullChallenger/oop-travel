package com.example.ooptravel.domain.hotel;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Bed {

    private String type;
    private int quantity;

    public Bed(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

}
