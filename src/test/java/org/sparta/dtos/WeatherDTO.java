package org.sparta.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDTO{

	@JsonProperty("coord")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Coord coord;

	@JsonProperty("weather")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<WeatherItem> weather;

	@JsonProperty("base")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String base;

	@JsonProperty("main")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Main main;

	@JsonProperty("visibility")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer visibility;

	@JsonProperty("wind")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Wind wind;

	@JsonProperty("rain")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Rain rain;

	@JsonProperty("snow")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Snow snow;

	@JsonProperty("clouds")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Clouds clouds;

	@JsonProperty("dt")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer dt;

	@JsonProperty("sys")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Sys sys;

	@JsonProperty("timezone")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer timezone;

	@JsonProperty("id")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Integer id;

	@JsonProperty("name")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String name;

	@JsonProperty("cod")
	private Integer cod;

	@JsonProperty("message")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
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