package org.sparta.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Clouds{

	@JsonProperty("all")
	private Integer all;

	public Integer getAll(){
		return all;
	}
}