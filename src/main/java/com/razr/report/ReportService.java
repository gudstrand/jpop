package com.razr.report;

import com.razr.mapping.CityMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Service to create reports from jsonl data files
 */
public class ReportService {

	private CityMapper mapper = new CityMapper();
	private UrlReader ureader = new UrlReader();

	/**
	 * Builds a population report
	 *
	 * @param url location of jsonl file with City popolation data
	 * @return Collated results as a report
	 * @throws IOException If we are unable to open the file
	 */
	public PopulationReport createPopulationReport(String url) throws IOException {
		BufferedReader br = ureader.createReader(url);
		PopulationReport report = new PopulationReport();
		Stream<String> filestream = br.lines();
		filestream.forEach(json -> mapper.mapAndAdd(report, json));
		br.close();
		return report;
	}
}
