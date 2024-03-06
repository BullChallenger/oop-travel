package com.example.ooptravel.domain.hotel;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOTEL_ID")
    private List<Room> rooms = new ArrayList<>();

    @Builder
    public Hotel(Address address, String name, String description, boolean open, List<Room> rooms) {
        this.address = address;
        this.name = name;
        this.description = description;
        this.open = open;
        this.rooms = rooms;
    }

}
