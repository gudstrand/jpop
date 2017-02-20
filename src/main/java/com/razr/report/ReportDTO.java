package com.razr.report;

import java.util.Set;

/**
 * Data transfer object for the REST population report call.
 */
public class ReportDTO {

	private final long totalPopulation;
	private final long largestPopulation;
	private final Set<String> mostPopulousCities;
	private final Set<String> states;

	public ReportDTO(PopulationReport report) {
		this.totalPopulation = report.getTotalPopulation();
		this.largestPopulation = report.getLargestPopulation();
		this.mostPopulousCities = report.getMostPopulousCities();
		this.states = report.getStates();
	}

	public long getTotalPopulation() {
		return totalPopulation;
	}

	public long getLargestPopulation() {
		return largestPopulation;
	}

	public Set<String> getMostPopulousCities() {
		return mostPopulousCities;
	}

	public Set<String> getStates() {
		return states;
	}
}
