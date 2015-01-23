package com.alnyli.service;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.alnyli.dto.DepartmentDTO;
import com.alnyli.dto.PersonDTO;
import com.alnyli.dto.PhoneDTO;
import com.alnyli.service.dao.DepDAO;
import com.alnyli.service.dao.PersonDAO;
import com.alnyli.service.dao.PhoneDAO;
import com.alnyli.service.dao.SuperUserDAO;
import com.alnyli.service.user.ServiceUser;


public class ServiceFacade extends  ServiceUser {

	private static ServiceFacade ser;
	private PersonDAO usr;
	private DepDAO depD;
	private PhoneDAO phD;
	private SuperUserDAO spD;
		
	private ServiceFacade(){
		super();
	}
	
	public void init(){
		usr  = new PersonDAO();
		depD = new DepDAO();
		phD  = new PhoneDAO();
		spD  = new SuperUserDAO();
	}
	
	public static ServiceFacade getInstance(){
		if(ser == null)
			ser = new ServiceFacade();
		return ser;
	}
	/* ekleme parametreleirne bakılacak delete de yapilacak */
	public boolean addPerson(HttpServletRequest request){
		boolean res = false;
		Connection con = usr.getTransectionConnection();
		if ( super.operationIsUniquieAdd(request) || super.getAddOperationCode(request) != -1)
			res = usr.addPerson(super.getPerson(request),con);
		else
			res = usr.delPerson(super.getPerson(request), con);
		usr.closeConnection(con);
		return res;
	}
	
	public boolean addDepartment(HttpServletRequest request){
		boolean res = false;
		Connection con = depD.getTransectionConnection();
		if ( super.operationIsUniquieAdd(request) || super.getAddOperationCode(request) != -1)
			res = depD.addDepartment(super.getDep(request),con);
		else
			res = depD.delDep(super.getDep(request), con);
		depD.closeConnection(con);
		return res;
	}
	
	public boolean addPhone(HttpServletRequest request){
		boolean phoneIsSaved = false;
		Connection con = phD.getTransectionConnection();
		if ( super.operationIsUniquieAdd(request) || super.getAddOperationCode(request) != -1)
			phoneIsSaved = phD.addPhone(super.getPhone(request),con);
		else
			phoneIsSaved = phD.delPhone(super.getPhone(request), con);
		phD.closeConnection(con);
		return phoneIsSaved;
	}
	
	public ArrayList<String> getPeople(){
		return spD.getPeopele();
	}
	
	
	
	public void addMulti(HttpServletRequest request){
		int opCode = super.getAddOperationCode(request);
		if(opCode != -1){
			Connection con = spD.getTransectionConnection();
			PersonDTO per = super.getPerson(request);
			PhoneDTO phn = super.getPhone(request);
			DepartmentDTO dep = super.getDep(request);
			if(opCode == 1){// add PER PHONE DEP 
				usr.addPerson(per, con);
				phD.addPhone(phn, con) ;
				depD.addDepartment(dep, con) ;
				spD.addPerDep(per, dep, con) ;
				spD.addPerPhone(per, phn, con) ;
			}else if(opCode == 2){ // add PER PHONE
				usr.addPerson(per, con);
				phD.addPhone(phn, con);
				spD.addPerPhone(per, phn, con);
			}else if(opCode == 3){// add PER DEP
				usr.addPerson(per, con);
				depD.addDepartment(dep, con);
				spD.addPerDep(per, dep, con);
			}else{		// add DEP PHONE
			    phD.addPhone(phn, con) ;
				depD.addDepartment(dep, con) ;
				spD.addDepPhone(dep, phn, con);
			}
			spD.closeConnection(con);
		}
	}
	

	public ArrayList<String> getPeopele(){
		return spD.getPeopele();
	}
	
	public	ArrayList<String> getPhones(){
		return spD.getPhones();
	}
	
	public String[] getTables(){
		return spD.getTables();
	}
	
	public void resetDatabase(){
		spD.resetDBTables();
	}
	
	

}
