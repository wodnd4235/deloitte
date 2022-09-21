package com.tableausoftware.documentation.api.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
	Properties prop = new Properties();
	InputStream inputStream;

	/**
	 * Constructor to use .properties file from resources and read user, password
	 * etc
	 */
	public LoadProperties() {

	}

	public void getPropertyValue() throws IOException {
		String propFileName = "config.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(
				propFileName);

		if (inputStream != null) {
			this.prop.load(inputStream);
		} else {

			throw new FileNotFoundException("property file '" + propFileName
					+ "' not found in the classpath");
		}

	}

	/**
	 * returning ttl from config.properties
	 */
	public String getUserName() {
		return prop.getProperty("user.admin.name");

	}

	/**
	 * 
	 * @return IP from config.properties
	 */
	public String getPassword() {
		return prop.getProperty("user.admin.password");
	}

	/**
	 * 
	 * @return Port no. from config.properties
	 */
	public String getContentUrl() {
		return prop.getProperty("site.default.contentUrl");

	}

}
