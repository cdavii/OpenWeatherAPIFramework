package org.sparta.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sys{

	@JsonProperty("country")
	private String country;

	@JsonProperty("sunrise")
	private Integer sunrise;

	@JsonProperty("sunset")
	private Integer sunset;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("type")
	private Integer type;

	@JsonProperty("message")
	private String message;

	public String getCountry(){
		return country;
	}

	public Integer getSunrise(){
		return sunrise;
	}

	public Integer getSunset(){
		return sunset;
	}

	public Integer getId(){
		return id;
	}

	public Integer getType(){
		return type;
	}

	public String getMessage(){
		return message;
	}
}