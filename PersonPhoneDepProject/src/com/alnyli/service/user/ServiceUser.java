package com.alnyli.service.user;

import javax.servlet.http.HttpServletRequest;

import com.alnyli.dto.DepartmentDTO;
import com.alnyli.dto.PersonDTO;
import com.alnyli.dto.PhoneDTO;

public class ServiceUser {

	protected ServiceUser() {
		// TODO Auto-generated constructor stub
	}

	
	protected static PersonDTO getPerson( HttpServletRequest request ){
		
	  PersonDTO per = new PersonDTO();
      if(request.getParameter("multiAdd") != null){
    	  per.setName(request.getParameter("perName"));
    	  per.setSirname(request.getParameter("perSName"));
      }else if(request.getParameter("delPer") != null || request.getParameter("addPer") != null){
    	  per.setName(request.getParameter("first_name"));
    	  per.setSirname(request.getParameter("last_name"));
      }
	  return per;
	}
	
	protected static DepartmentDTO getDep(HttpServletRequest request){
	   DepartmentDTO dep = new DepartmentDTO();
	   dep.setName(request.getParameter("depName"));
	   String a="" ;
       if(request.getParameter("multiAdd") != null ){
        	  a= request.getParameter("depMName");
       }else if( request.getParameter("depName") != null || request.getParameter("depName") != null)
    	      a= request.getParameter("depName");
       dep.setName(a);
	   return dep;
	}
	
	protected static PhoneDTO getPhone( HttpServletRequest request ){
		PhoneDTO phn = new PhoneDTO();
		phn.setNumber(request.getParameter("tel"));
		String a="" ;
	    if( request.getParameter("multiAdd") != null ){
	          a= request.getParameter("phnNum");
	    }else if(request.getParameter("delPhn") != null || request.getParameter("addPhn") != null )
	    	  a= request.getParameter("tel");
		phn.setNumber(a);
		return phn;
	}
	
	protected static int getAddOperationCode(HttpServletRequest request){
		int res=-1;
		if(  (request.getParameter("phnNum") != null && !request.getParameter("phnNum").isEmpty()) && ( request.getParameter("depMName") != null && !request.getParameter("depMName").isEmpty())
				&&  ( request.getParameter("perName") != null && !request.getParameter("perName").isEmpty()) && ( request.getParameter("perSName") != null && !request.getParameter("perSName").isEmpty() )) // Person Phone Department Relation 
			res = 1;
		else if(  ( request.getParameter("perName") != null && !request.getParameter("perName").isEmpty()) && ( request.getParameter("perSName") != null && !request.getParameter("perSName").isEmpty() ) &&
				(request.getParameter("phnNum") != null && !request.getParameter("phnNum").isEmpty()))// Person Phone D Relation 
			res = 2;
		else if(  ( request.getParameter("perName") != null && !request.getParameter("perName").isEmpty()) && ( request.getParameter("perSName") != null && !request.getParameter("perSName").isEmpty() ) &&
				( request.getParameter("depMName") != null && !request.getParameter("depMName").isEmpty())  )// Person Department Relation 
			res = 3;
		else if((request.getParameter("phnNum") != null && !request.getParameter("phnNum").isEmpty()) && ( request.getParameter("depMName") != null && !request.getParameter("depMName").isEmpty())  )// Department Phone Relation 
			res = 4;
		return res;
		
	}
	
	
	protected static boolean operationIsUniquieAdd(HttpServletRequest request) {
		return (request.getParameter("addDep") != null || request.getParameter("addPhn") != null
				|| request.getParameter("addPer") != null);
	}
	
	
	
	
}
