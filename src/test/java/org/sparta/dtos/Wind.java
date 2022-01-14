package org.sparta.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind{

	@JsonProperty("deg")
	private Integer deg;

	@JsonProperty("speed")
	private Double speed;

	@JsonProperty("gust")
	private Double gust;

	public Integer getDeg(){
		return deg;
	}

	public Double getSpeed(){
		return speed;
	}

	public Double getGust(){
		return gust;
	}
}