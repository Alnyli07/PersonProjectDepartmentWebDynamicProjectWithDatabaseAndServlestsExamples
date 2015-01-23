package com.alnyli.service.db.properties;

import java.util.Properties;

public final class ConstantProperties {
	
	private static Properties properties;
	
	public ConstantProperties() {
		
	}
	
	public static void setProperties(Properties properties){
		ConstantProperties.properties = properties;
	}
	
	public static  Properties getProperties(){
		return properties;
	}

}
