package com.razr.report;

import groovy.lang.Grab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Grab("thymeleaf-spring4")

/**
 * Creates a web page population report using Thymeleaf and templates.
 */
@Controller
public class ReportController {

	private Logger logger = LoggerFactory.getLogger(ReportController.class);

	@RequestMapping(method = RequestMethod.GET, path = "/popreport")
	public String population(@RequestParam(value = "url", defaultValue = "https://dl.dropboxusercontent.com/u/2436323/cities.jsonl") String url, Model model) {
		ReportService service = new ReportService();
		try {

			PopulationReport report = service.createPopulationReport(url);
			model.addAttribute("totalpopulation", report.getTotalPopulation());
			model.addAttribute("largestpopulation", report.getLargestPopulation());
			model.addAttribute("mostpopulouscity", report.getMostPopulousCity());
			model.addAttribute("states", report.getStates());
			return "popreport";
		} catch (IOException e) {
			throw new ResourceNotFoundException(url);
		}
	}
}
