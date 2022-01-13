package org.sparta.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Snow{

	@JsonProperty("3h")
	private Double lastThreeHours;

	@JsonProperty("1h")
	private Double lastHour;

	public Double getLastThreeHours(){
		return lastThreeHours;
	}

	public Double getLastHour(){
		return lastHour;
	}
}