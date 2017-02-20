package com.razr.report;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Map /error to a less generic page.
 */
@RestController
public class ApplicationErrorController implements ErrorController {

	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public String error() {
		return "You have encountered a system error.";
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}
