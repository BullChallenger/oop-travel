package com.example.ooptravel.domain.hotel;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HotelServiceGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOTEL_SERVICE_GROUP_ID")
    private Long id;

    private String name;
    private boolean basic;
    private boolean exclusive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOTEL_SERVICE_GROUP_ID")
    private List<HotelService> optionSpecs = new ArrayList<>();

    public HotelServiceGroup(String name, boolean basic, boolean exclusive, List<HotelService> optionSpecs) {
        this.name = name;
        this.basic = basic;
        this.exclusive = exclusive;
        this.optionSpecs = optionSpecs;
    }

    public static HotelServiceGroup basic(String name, boolean exclusive, HotelService... options) {
        return new HotelServiceGroup(name, exclusive, true, options);
    }

    public static HotelServiceGroup exclusive(String name, boolean exclusive, HotelService... options) {
        return new HotelServiceGroup(name, exclusive, false, options);
    }

    public HotelServiceGroup(String name, boolean exclusive, boolean basic, HotelService... options) {
        this(name, exclusive, basic, Arrays.asList(options));
    }

}
