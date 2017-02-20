package com.razr.jpop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by gary on 2/19/17.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Unable to load resource")
public class ResourceNotFoundException extends RuntimeException {

	private final transient Logger logger = LoggerFactory.getLogger(ResourceNotFoundException.class);

	public ResourceNotFoundException(String url) {
		logger.error("Failed to locate resource {}", url);
	}
}
