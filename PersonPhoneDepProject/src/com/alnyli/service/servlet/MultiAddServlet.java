package com.alnyli.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alnyli.service.ServiceFacade;

/**
 * Servlet implementation class addOrDell
 */
//@WebServlet("/addMulti")
public class MultiAddServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	    String docType ="<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
	    ServiceFacade.getInstance().addMulti(request);
	    String[] str = ServiceFacade.getInstance().getTables();
	    out.println(docType + Html.HTML_CSS + Html.ADD_DEP + Html.ADD_PER  + Html.ADD_PHONE + Html.MULTI_ADD_FORM + str[0]+str[1]+str[2]
	    		+Html.ADD_REL+"</body></html>");
	    destroy();
	}

}
