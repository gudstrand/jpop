package com.razr.report;

import com.razr.mapping.CityDTO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create the PopulationReport object and add cities iteratively.  Report is generated
 * real time. toString() method will print it out.
 */
public class PopulationReport {
	private Set<String> mostPopulousCities = new HashSet<>();
	private Integer largestPopulation = new Integer(0);
	private Integer totalPopulation = new Integer(0);
	private Set<String> states = new HashSet<>();

	public Set<String> getMostPopulousCities() {
		return mostPopulousCities;
	}

	public void addMostPopulousCity(String city) {
		this.mostPopulousCities.add(city);
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
		this.states.add(state.toUpperCase());
	}

	private void addPopulation(Integer population) {
		this.totalPopulation += population;
	}

	private void setMostPopulous(String city, Integer population) {
		city = normalizeName(city);
		if (population.equals(this.getLargestPopulation())) {
			this.addMostPopulousCity(city);
		} else if (population > this.getLargestPopulation()) {
			this.mostPopulousCities.clear();
			this.setLargestPopulation(population);
			this.addMostPopulousCity(city);
		}
	}

	private String normalizeName(String city) {
		StringBuilder sb = new StringBuilder();
		String[] splits = city.trim().split(" +");
		List<String> nameParts = Arrays.stream(splits).map(split -> capitalizeWord(split)).collect(Collectors.toList());
		nameParts.stream().forEach(name -> sb.append(name + " "));
		return sb.toString().trim();
	}

	private String capitalizeWord(String word) {
		String cap = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
		return cap;
	}

	public void addCity(CityDTO city) {
		this.addState(city.getState());
		this.addPopulation(city.getPopulation());
		this.setMostPopulous(city.getCity(), city.getPopulation());
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PopulationReport{");
		sb.append("mostPopulousCities='").append(mostPopulousCities).append('\'');
		sb.append(", largestPopulation=").append(largestPopulation);
		sb.append(", totalPopulation=").append(totalPopulation);
		sb.append(", states=").append(states);
		sb.append('}');
		return sb.toString();
	}
}
