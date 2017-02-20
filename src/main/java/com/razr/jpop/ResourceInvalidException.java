package com.razr.jpop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by gary on 2/19/17.
 */
@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED, reason = "Only files of .gz or .jsonl supported")
public class ResourceInvalidException extends RuntimeException {
	private final transient Logger logger = LoggerFactory.getLogger(ResourceInvalidException.class);

	public ResourceInvalidException(String url) {
		logger.error("Resource URL is not valid: {}", url);
	}
}
