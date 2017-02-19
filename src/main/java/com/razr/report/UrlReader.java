package com.razr.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Utility class to open a reader for a url or optionally returning contents
 * in a List<CityDTO>
 */
public class UrlReader {

	private Logger logger = LoggerFactory.getLogger(UrlReader.class);

	/**
	 * Create a reader from a url to read a file line by line
	 *
	 * @param urlAddress address of jsonl contents in uri format
	 * @return BufferedReader from url
	 * @throws MalformedURLException url is malformed
	 * @throws IOException           unable to open or locate file
	 */
	protected BufferedReader createReader(String urlAddress) throws IOException {
		logger.debug("Creating BufferedReader for {}", urlAddress);
		URL url = new URL(urlAddress);
		InputStreamReader reader = new InputStreamReader(url.openStream());
		return new BufferedReader(reader);
	}
}