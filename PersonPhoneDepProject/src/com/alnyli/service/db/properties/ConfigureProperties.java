package com.alnyli.service.db.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class ConfigureProperties {
	private Properties properties;
	private Logger logger;

	public ConfigureProperties() {
		properties = new Properties();
		logger = Logger.getLogger(ConfigureProperties.class);
	}
	
	public void loadProperties(){
		ClassLoader classLoader = getClass().getClassLoader();
	   InputStream configurePropsStream =  classLoader.getResourceAsStream("resources/DatabaseConfigure.properties");// getClass().getResourceAsStream("DatabaseConfigure.properties");
	   try {
			properties.load(configurePropsStream);
			logger.info(" Database Properties was setted.");
		} catch (IOException e1) {
			logger.error(" Database Properties setting operation wa failed.");
		}
	}
	
	public Properties getProp(){
		return properties;
	}
}
