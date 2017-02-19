package com.razr.mapping;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO java object used by jackson to parse a jsonl entry into a java object.  Jsonl entry
 * should be in the format of
 * city (String): The name of the city
 * state (String): The 2 digit state abbreviation
 * population (Integer): The population of the city
 * <p>
 * Example
 * {"city": "New York", "state": "NY", "population": 8550405}
 */
public class CityDTO {
	@JsonProperty(value = "city", required = true)
	private String city;
	@JsonProperty(value = "state", required = true)
	private String state;
	@JsonProperty(value = "population", required = true)
	private Integer population;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("CityDTO{");
		sb.append("city='").append(city).append('\'');
		sb.append(", state='").append(state).append('\'');
		sb.append(", population=").append(population);
		sb.append('}');
		return sb.toString();
	}
}
