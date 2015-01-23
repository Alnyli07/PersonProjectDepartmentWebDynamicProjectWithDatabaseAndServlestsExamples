package com.alnyli.log;

import org.apache.log4j.xml.DOMConfigurator;

public class ProjeLogger {

	/*       logger initilation. */
	public ProjeLogger(){
		DOMConfigurator.configure(getClass().getClassLoader().getResource("resources/log4j.xml"));	
	}
	
	
}


