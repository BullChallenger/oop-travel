package com.example.ooptravel.domain.hotel;

import com.example.ooptravel.domain.generic.money.Money;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OptionSpecification {

	private final String name;
	private final Money price;

	@Builder
	public OptionSpecification(String name, Money price) {
		this.name = name;
		this.price = price;
	}

}
