package org.sparta.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Snow{

	@JsonProperty("3h")
	private Double jsonMember3h;

	@JsonProperty("1h")
	private Double jsonMember1h;

	public Double getJsonMember3h(){
		return jsonMember3h;
	}

	public Double getJsonMember1h(){
		return jsonMember1h;
	}
}