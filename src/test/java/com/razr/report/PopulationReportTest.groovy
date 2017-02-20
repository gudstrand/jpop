package com.razr.report

import com.razr.mapping.CityDTO
import spock.lang.Shared
import spock.lang.Specification

/**
 * Spock test of PopulationReport
 */
class PopulationReportTest extends Specification {

    @Shared
            fileAddress = "file:src//test//resources//populationreport//smalltowns.jsonl"
    @Shared
            errorFileAddress = "file:src//test//resources//populationreport//filewitherrors.jsonl"
    @Shared
            samePopFile = "file:src//test//resources//populationreport//samepopulation.jsonl"
    @Shared
            mixedcase = "file:src//test//resources//populationreport//mixedcase.jsonl"
    @Shared
    PopulationReport report
    @Shared
    ReportService service

    def setup() {
        service = new ReportService()
        report = service.createPopulationReport(fileAddress, true)
    }

    def cleanup() {
    }

    def "test getMostPopulousCity"() {
        when:
        Set<String> cities = report.getMostPopulousCities()
        then:
        cities.size() == 1
        cities.contains("Greenbush")
    }

    def "test getLargestPopulation"() {
        when:
        Integer largestpop = report.getLargestPopulation()
        then:
        largestpop == 717
    }

    def "test getTotalPopulation"() {
        when:
        Integer totalpop = report.getTotalPopulation()
        then:
        totalpop == 1452
    }

    def "test getStates"() {
        when:
        List<String> expected = ["MN", "ND"]
        Set<String> states = report.getStates()
        then:
        states.size() == 2
        states.containsAll(expected)

    }

    def "test addCity"() {
        when:
        List<String> expected = ["MN", "ND", "CO"]
        CityDTO dto = new CityDTO()
        dto.setCity("Dillon")
        dto.setPopulation(937)
        dto.setState("CO")
        report.addCity(dto)
        then:
        report.getStates().size() == 3
        report.getStates().containsAll(expected)
        report.getMostPopulousCities().contains("Dillon")
        report.getLargestPopulation() == 937
        report.getTotalPopulation() == 2389
    }

    def "test error file"() {
        given:
        List<String> expected = ["NY", "IL"]
        report = service.createPopulationReport(errorFileAddress, true)
        when:
        report != null
        then:
        report.getStates().size() == 2
        report.getStates().containsAll(expected)
        report.getLargestPopulation() == 8550405
        report.getMostPopulousCities().contains("New York")
        report.getTotalPopulation() == 11270951
    }

    def "test same population"() {
        given:
        List<String> expected = ["Greenbush", "Strathcona"]
        report = service.createPopulationReport(samePopFile, true)
        when:
        report != null
        then:
        report.getMostPopulousCities().containsAll(expected)
    }

    def "mixed case"() {
        given:
        List<String> expected = ["New York"]
        List<String> expectedStates = ["CA", "PA", "NY", "AZ", "TX"]
        report = service.createPopulationReport(mixedcase, true)
        when:
        report != null
        then:
        report.getMostPopulousCities().containsAll(expected)
    }
}

