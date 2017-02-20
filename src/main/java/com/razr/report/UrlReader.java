package com.razr.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Utility class to open a reader for a url
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
		InputStream is;
		logger.debug("Creating BufferedReader for {}", urlAddress);
		if (isCompressed(urlAddress)) {
			is = getCompressedStream(urlAddress);
		} else {
			URL url = new URL(urlAddress);
			is = url.openStream();
		}
		InputStreamReader reader = new InputStreamReader(is);
		return new BufferedReader(reader);
	}

	/**
	 * If resource is a gz file, we create a gzip stream and return that instead.
	 *
	 * @param urlAddress
	 * @return
	 * @throws IOException
	 */
	private InputStream getCompressedStream(String urlAddress) throws IOException {
		URL url = new URL(urlAddress);
		GZIPInputStream zipstream = new GZIPInputStream(url.openStream());
		return zipstream;
	}

	private boolean isCompressed(String urlAddress) {
		return urlAddress.endsWith(".gz");
	}
}