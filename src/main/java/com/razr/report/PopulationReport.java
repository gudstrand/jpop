package com.razr.report;

import com.razr.mapping.CityDTO;

import java.util.HashSet;
import java.util.Set;

/**
 * Create the PopulationReport object and add cities iteratively.  Report is generated
 * real time. toString() method will print it out.
 */
public class PopulationReport {
	private String mostPopulousCity = "";
	private Integer largestPopulation = new Integer(0);
	private Integer totalPopulation = new Integer(0);
	private Set<String> states = new HashSet<>();

	public String getMostPopulousCity() {
		return mostPopulousCity;
	}

	private void setMostPopulousCity(String mostPopulousCity) {
		this.mostPopulousCity = mostPopulousCity;
	}

	public Integer getLargestPopulation() {
		return largestPopulation;
	}

	private void setLargestPopulation(Integer largestPopulation) {
		this.largestPopulation = largestPopulation;
	}

	public Integer getTotalPopulation() {
		return totalPopulation;
	}


	public Set<String> getStates() {
		return states;
	}

	private void addState(String state) {
		this.states.add(state);
	}

	private void addPopulation(Integer population) {
		this.totalPopulation += population;
	}

	private void setMostPopulous(String city, Integer population) {
		if (population > this.getLargestPopulation()) {
			this.setLargestPopulation(population);
			this.setMostPopulousCity(city);
		}
	}

	public void addCity(CityDTO city) {
		this.addState(city.getState());
		this.addPopulation(city.getPopulation());
		this.setMostPopulous(city.getCity(), city.getPopulation());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PopulationReport{");
		sb.append("mostPopulousCity='").append(mostPopulousCity).append('\'');
		sb.append(", largestPopulation=").append(largestPopulation);
		sb.append(", totalPopulation=").append(totalPopulation);
		sb.append(", states=").append(states);
		sb.append('}');
		return sb.toString();
	}
}
