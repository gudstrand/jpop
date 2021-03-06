package com.razr.report;

import com.razr.jpop.ResourceInvalidException;
import com.razr.jpop.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Spring Boot controller to provide a REST interface to the population report data.
 */
@RestController
public class ReportRestController {

	private Logger logger = LoggerFactory.getLogger(ReportRestController.class);

	@RequestMapping(method = RequestMethod.GET, path = "/population")
	public ReportDTO population(@RequestParam(value = "url", defaultValue = "https://dl.dropboxusercontent.com/u/2436323/cities.jsonl") String url,
	                            @RequestParam(value = "ignoreErrors", defaultValue = "false") boolean ignoreErrors) {

		return getReportDTO(url, ignoreErrors);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/population")
	public ReportDTO populationPOST(@RequestParam(value = "url", defaultValue = "https://dl.dropboxusercontent.com/u/2436323/cities.jsonl") String url,
	                                @RequestParam(value = "ignoreErrors", defaultValue = "false") boolean ignoreErrors) {

		return getReportDTO(url, ignoreErrors);
	}

	private ReportDTO getReportDTO(String url, boolean ignoreErrors) {
		// check for a jsonl or gz extension, otherwise exit
		if (!isValid(url)) {
			logger.error("Invalid Resource {}", url);
			throw new ResourceInvalidException(url);
		}

		ReportService service = new ReportService();
		try {
			PopulationReport report = service.createPopulationReport(url, ignoreErrors);
			return new ReportDTO(report);
		} catch (IOException e) {
			logger.error("Resource {} not found ", url);
			logger.error("Exception is ", e);
			throw new ResourceNotFoundException(url);
		}
	}

	private boolean isValid(String url) {
		return url.endsWith(".gz") || url.endsWith(".jsonl");
	}
}