package com.example.ooptravel.domain.hotel;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOTEL_ID")
    private Long id;

    @Embedded
    private Address address;

    private String name;
    private String description;
    private boolean open;

    @Builder
    public Hotel(Address address, String name, String description, boolean open) {
        this.address = address;
        this.name = name;
        this.description = description;
        this.open = open;
    }

    public void open() {
        this.open = true;
    }

    public void close() {
        this.open = false;
    }

}
