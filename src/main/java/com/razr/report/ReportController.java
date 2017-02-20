package com.razr.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ReportController {

	private Logger logger = LoggerFactory.getLogger(ReportController.class);

	@RequestMapping(method = RequestMethod.GET, path = "/population")
	public ReportDTO population(@RequestParam(value = "url", defaultValue = "https://dl.dropboxusercontent.com/u/2436323/cities.jsonl") String url) {
		ReportService service = new ReportService();
		try {
			PopulationReport report = service.createPopulationReport(url);
			return new ReportDTO(report);
		} catch (IOException e) {
			throw new ResourceNotFoundException(url);
		}
	}
}
