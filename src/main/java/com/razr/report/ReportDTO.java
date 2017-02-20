package com.razr.report;

import java.util.Set;

/**
 * Data transfer object for the REST population report call.
 */
public class ReportDTO {

	private final long totalPopulation;
	private final long largestPopulation;
	private final String mostPopulousCity;
	private final Set<String> states;

	public ReportDTO(PopulationReport report) {
		this.totalPopulation = report.getTotalPopulation();
		this.largestPopulation = report.getLargestPopulation();
		this.mostPopulousCity = report.getMostPopulousCity();
		this.states = report.getStates();
	}

	public long getTotalPopulation() {
		return totalPopulation;
	}

	public long getLargestPopulation() {
		return largestPopulation;
	}

	public String getMostPopulousCity() {
		return mostPopulousCity;
	}

	public Set<String> getStates() {
		return states;
	}
}
