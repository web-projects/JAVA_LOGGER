package com.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

public class ConfigurationUtil {

	private Environment environment;
	
	public static Environment getConfiguration() {
		 
		Environment environment = null;
		InputStream fis = null;
		try {
			fis = new FileInputStream(LogConstants.APP_CONFIG_PATH +  LogConstants.APP_CONFIG_FILENAME);
		} catch (FileNotFoundException e) {
			return null;
		}

		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS, false);

		try {
			environment = mapper.readValue(fis, Environment.class);
			 
		} catch (IOException e) {
			return null;
		}
		return environment;
	}
}
