package com.example.ooptravel.domain.hotel;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OptionGroup {

	private final String groupName;
	private final List<OptionSpecification> optionSpecs;

	@Builder
	public OptionGroup(String groupName, List<OptionSpecification> optionSpecs) {
		this.groupName = groupName;
		this.optionSpecs = optionSpecs;
	}

}
