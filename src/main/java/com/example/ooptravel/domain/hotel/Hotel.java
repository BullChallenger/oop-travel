package com.example.ooptravel.domain.hotel;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Address address;

    @OneToMany
    private List<Room> rooms = new ArrayList<>();

    private String name;
    private String description;
    private boolean open;

    @Builder
    public Hotel(Address address, List<Room> rooms, String name, String description, boolean open) {
        this.address = address;
        this.rooms = rooms;
        this.name = name;
        this.description = description;
        this.open = open;
    }

}
