package com.razr.mapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.razr.report.PopulationReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Maps jsonl data objects to java DTO CityDTO
 */
public class CityMapper {
	private Logger logger = LoggerFactory.getLogger(CityMapper.class);

	// jackson class to map jsonl to java objects
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Creates a DTO from each jsonl entry
	 *
	 * @param json jsonl String representation of the city population data
	 *             has format
	 *             city (String): The name of the city
	 *             state (String): The 2 digit state abbreviation
	 *             population (Integer): The population of the city
	 * @return dto CityDTO
	 */
	public CityDTO map(String json) throws MappingException {
		try {
			return mapper.readValue(json, CityDTO.class);
		} catch (IOException e) {
			throw new MappingException("Failed to map json file:: " + json, e);
		}
	}

	/**
	 * map jsonl record to a CityDTO object and add it to the population report
	 *
	 * @param report PopulationReport object
	 * @param json   jsonl String representation of the city population data
	 *               has format
	 *               city (String): The name of the city
	 *               state (String): The 2 digit state abbreviation
	 *               population (Integer): The population of the city
	 */
	public void mapAndAdd(PopulationReport report, String json) throws MappingException {
		try {
			CityDTO city = this.map(json);
			report.addCity(city);
		} catch (MappingException e) {
			logger.info("Failed to map CityDTO {}", json);
			throw new MappingException("failed to map json record " + json, e);
		}
	}

	/**
	 * allows bad records to be fitered in a lambda from the results without
	 * having to return a null or null type in the case of ignore errors
	 *
	 * @param report PopulationReport object
	 * @param json   jsonl String representation of the city population data
	 *               has format
	 *               city (String): The name of the city
	 *               state (String): The 2 digit state abbreviation
	 *               population (Integer): The population of the city
	 */
	public void mapAndAddIgnoreErrors(PopulationReport report, String json) {
		try {
			CityDTO city = this.map(json);
			report.addCity(city);
		} catch (MappingException e) {
			logger.info("Failed to map CityDTO {}", json);
		}
	}
}
