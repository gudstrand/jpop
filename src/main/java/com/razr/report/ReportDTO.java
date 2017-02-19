package com.razr.report;

import java.util.Set;

/**
 * Created by gary on 2/19/17.
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

	//	private final long id;
//	private final String content;
//
//	public Greeting(long id, String content) {
//		this.id = id;
//		this.content = content;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public String getContent() {
//		return content;
//	}
}
