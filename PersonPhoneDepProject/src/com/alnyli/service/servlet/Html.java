package com.alnyli.service.servlet;

public class Html {

	public static final String ADD_DEP = "<div class=\"div\" id=\"div\">  <form action=\"addDep\" method=\"POST\">" +
				"<h5 align=\"center\">   DEPARTMENT  </h5>"+
				"Department Name: <input type=\"text\" name=\"depName\">"+
				"<br /> <input type=\"submit\" name=\"addDep\" value=\"add\" />"+
				"<input type=\"submit\"  name=\"delDep\" value=\"Delete\"/> " +
				"</form> " ;
	public static final String ADD_PER = " <form action=\"PersonServlet\" method=\"POST\">"+
				"<h5 align=\"center\">   PERSON </h5>"+
			    "First Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=\"text\" name=\"first_name\"><br />"+
			    "Last  Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type=\"text\" name=\"last_name\" />"+
			    "<br /> <input type=\"submit\"  name=\"addPer\" value=\"Add \" />  "
			    +"<input type=\"submit\"  name=\"delPer\" value=\"Delete\"/> </form>";
	
	public static final String LIST_PER = " <form align=\"center\" action=\"getPersons\" method=\"POST\" >"+
			  "<input type=\"submit\" value=\"list / Refresh Tables\" />  </form>";
	public static final String MULTI_ADD_FORM = "<div class=\"div\" id=\"div3\">"+ LIST_PER+ "<form action=\"addMulti\" method=\"POST\" >" +
		 "<h5 align=\"center\">  MULTI ADD  </h5>"+
		 "First Name --------->: <input type=\"text\" name=\"perName\"> <br />"+
	  	 "Last Name ---------->: <input type=\"text\" name=\"perSName\"> <br />"+
	     "Phone Number --->:    <input type=\"text\" name=\"phnNum\"> <br />"+
	     "Depertment -------->: <input type=\"text\" name=\"depMName\"><br /><br />"
	     +"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	     "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	     "<input type=\"submit\"  name=\"multiAdd\"  value=\"  ADD \" /> <br /> "+
		 "</form> </div>";
	public static final String ADD_PHONE = " <form action=\"PhoneServlet\" method=\"POST\">"+
			  "<h5 align=\"center\">  PHONE  </h5>"+
			   "Phone Number :&nbsp;&nbsp;&nbsp;&nbsp; <input type=\"text\" name=\"tel\">"+
			  "<br /> <input type=\"submit\" name=\"addPhn\" value=\"add\" /> "+
			  "<input type=\"submit\"  name=\"delPhn\" value=\"Delete\"/>"+
			  "</div>"+
			  "</form> ";
	public static final String HTML_CSS = "<html><head><style >  #ana_div {height: 800px;  width: 1150px;     margin-left: 50px; "+
			" background-color: #112ff1;}  .div {  float: left;  height: 400px;  width: 480px;"+
			"margin: 2px;  background-color: #abcdef; } .div1{	  float: left;	  height: 400px; width: 250px;	  margin: 2px;"+
	  "background-color: #abcdef; }</style></head><title> Person_Dep </title><body bgcolor=\"#f1f1f1\" > <div id=\"ana_div\"> ";
	public static final String ADD_REL = " <form action=\"ResetServlet\" method=\"POST\"> "+
			"<input type=\"submit\" name=\"rsetDBTable\" value=\"Reset Database\" /> </form>";
  		 
	public static String getDiv(boolean result){
		String res="";
		res = " <div class=\"div\" id=\"divv1\" >";
		 if(result)
	    	  res += "<h6> Operation was succeed. Click 'list tables' </h6> ";
	      else
	    	  res += "<h6>  Operation was failed. ! Click 'list tables' </h6> ";
		 res += "</div>";
		return res;
	}
	
	private Html(){
		
	}
}
