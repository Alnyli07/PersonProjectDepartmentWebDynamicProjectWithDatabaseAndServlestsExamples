package com.alnyli.service.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import com.alnyli.log.ProjeLogger;
import com.alnyli.service.ServiceFacade;
import com.alnyli.service.db.properties.ConfigureProperties;
import com.alnyli.service.db.properties.ConstantProperties;



/**
 * Servlet implementation class deneme
 */
public class ContextInitilazer implements ServletContextListener {
    /**
     * @throws NamingException 
     * @see HttpServlet#HttpServlet()
     * 
     */
	public ContextInitilazer(){
		super();
	}
   


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(" TomCat kapaniyor");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		new ProjeLogger();
		ConfigureProperties configureProp = new ConfigureProperties();
		configureProp.loadProperties();
		ConstantProperties.setProperties(configureProp.getProp());
		ServiceFacade srv = ServiceFacade.getInstance();
		srv.init();
		System.out.println(" TomCat çalışıyor ");
	}

}
