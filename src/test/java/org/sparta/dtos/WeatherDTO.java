package org.sparta.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDTO{

	@JsonProperty("coord")
	@JsonIgnore
	private Coord coord;

	@JsonProperty("weather")
	@JsonIgnore
	private List<WeatherItem> weather;

	@JsonProperty("base")
	@JsonIgnore
	private String base;

	@JsonProperty("main")
	@JsonIgnore
	private Main main;

	@JsonProperty("visibility")
	@JsonIgnore
	private Integer visibility;

	@JsonProperty("wind")
	@JsonIgnore
	private Wind wind;

	@JsonProperty("rain")
	@JsonIgnore
	private Rain rain;

	@JsonProperty("snow")
	@JsonIgnore
	private Snow snow;

	@JsonProperty("clouds")
	@JsonIgnore
	private Clouds clouds;

	@JsonProperty("dt")
	@JsonIgnore
	private Integer dt;

	@JsonProperty("sys")
	@JsonIgnore
	private Sys sys;

	@JsonProperty("timezone")
	@JsonIgnore
	private Integer timezone;

	@JsonProperty("id")
	@JsonIgnore
	private Integer id;

	@JsonProperty("name")
	@JsonIgnore
	private String name;

	@JsonProperty("cod")
	@JsonIgnore
	private Integer cod;

	@JsonProperty("message")
	@JsonIgnore
	private String message;

	public Coord getCoord() {
		return coord;
	}

	public List<WeatherItem> getWeather() {
		return weather;
	}

	public String getBase() {
		return base;
	}

	public Main getMain() {
		return main;
	}

	public Integer getVisibility() {
		return visibility;
	}

	public Wind getWind() {
		return wind;
	}

	public Rain getRain() {
		return rain;
	}

	public Snow getSnow() {
		return snow;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public Integer getDt() {
		return dt;
	}

	public Sys getSys() {
		return sys;
	}

	public Integer getTimezone() {
		return timezone;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getCod() {
		return cod;
	}

	public String getMessage() {
		return message;
	}
}