package com.razr.report;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by gary on 2/19/17.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Unable to load URL")
public class UrlNotFoundException extends RuntimeException {
	public UrlNotFoundException(String url) {
	}
}
