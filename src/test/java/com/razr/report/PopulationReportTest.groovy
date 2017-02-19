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
    PopulationReport report
    @Shared
    ReportService service

    def setup() {
        service = new ReportService()
        report = service.createPopulationReport(fileAddress)
    }

    def cleanup() {
    }

    def "test getMostPopulousCity"() {
        when:
        String city = report.getMostPopulousCity()
        then:
        city.equals("Greenbush")
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
        report.getLargestPopulation() == 937
        report.getMostPopulousCity().equals("Dillon")
        report.getTotalPopulation() == 2389
    }

    def "test error file"() {
        given:
        List<String> expected = ["NY", "IL"]
        report = service.createPopulationReport(errorFileAddress)
        when:
        report != null
        then:
        report.getStates().size() == 2
        report.getStates().containsAll(expected)
        report.getLargestPopulation() == 8550405
        report.getMostPopulousCity().equals("New York")
        report.getTotalPopulation() == 11270951
    }
}
