package com.alnyli.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alnyli.service.ServiceFacade;

/**
 * Servlet implementation class addDep
 */
//@WebServlet("/addDep")
public class DepartmentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepartmentServlet() {
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
      String docType =
      "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n";
      String res = Html.getDiv(ServiceFacade.getInstance().addDepartment(request));
      out.println(docType + Html.HTML_CSS   + Html.ADD_DEP   + Html.ADD_PER 
    		  + Html.LIST_PER  + Html.ADD_PHONE + Html.MULTI_ADD_FORM + res + "</body></html>");
      destroy();
	}

}
