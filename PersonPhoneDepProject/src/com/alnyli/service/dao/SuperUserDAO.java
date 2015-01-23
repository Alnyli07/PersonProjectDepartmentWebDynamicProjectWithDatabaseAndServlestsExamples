package com.alnyli.service.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.alnyli.dto.DepartmentDTO;
import com.alnyli.dto.PersonDTO;
import com.alnyli.dto.PhoneDTO;
import com.alnyli.service.db.ConnectionHelper;

public class SuperUserDAO  extends ConnectionHelper{
	
	private ResultSet  rs;
	private Statement  st;
	private Logger logger;
	private String createPersonTable = "CREATE TABLE  person (Id serial unique,  name varchar(30),sirname varchar(30))";
	private String createDepartment = "CREATE TABLE department 	( Id serial unique,  name varchar(225) )";
	private String createPhone = "CREATE TABLE phone (Id serial unique,  num varchar(12) )";
	private String createPerPhone = "CREATE TABLE person_phone ( Id serial unique,  kisiId int,  phoneId int)";
	private String createPerDep = "CREATE TABLE person_dep ( Id serial unique,  kisiId int, depId int )";
	private String createDepPhone = "CREATE TABLE dep_phone ( Id serial unique,  depId int,  phoneId int )";
	
	
	public SuperUserDAO() {
		super();
		super.init();
		logger = Logger.getLogger(SuperUserDAO.class);
	}
	
	public void resetDBTables(){
		String komut;
		komut = "DROP TABLE person,department,phone,dep_phone,person_phone,person_dep";
		executeSQL(komut);
		executeSQL(createPersonTable);
		executeSQL(createDepartment);
		executeSQL(createPhone);
		executeSQL(createPerPhone);
		executeSQL(createPerDep);
		executeSQL(createDepPhone);
		logger.info("Database was resetted.");
	}
	
	public ArrayList<String> getPeopele(){
		ArrayList<String> pep = new ArrayList<String>();
		Connection con = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String komut = "SELECT * FROM person ";
			rs = st.executeQuery(komut);
			String temp;
			while(rs.next()){
				temp = rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+"\n";
				pep.add(temp);
			}
		} catch (SQLException e) {
			logger.warn("SQLException: during the getPerson");
		}finally{
			super.closeResultSet(rs);
			super.closeStatement(st);
			super.closeConnection(con); 
		}
		
		return pep;
	}
	
	public ArrayList<String> getPhones(){
		ArrayList<String> phons = new ArrayList<String>();
		Connection con = super.getConnection();
		try {
			st = con.createStatement();
			String komut = "SELECT * FROM phone ";
			rs = st.executeQuery(komut);
			String temp;
			while(rs.next()){
				temp = rs.getString(1)+" | "+rs.getString(2)+" | "+"\n";
				phons.add(temp);
			}
			rs.close();
			st.close(); // close statement
		} catch (SQLException e) {
			logger.warn("SQLException: during the getPhone");
		}
		super.closeConnection(con); 
		
		return phons;
	}
	
	public ArrayList<String> getDeps(){
		ArrayList<String> deps = new ArrayList<String>();
		Connection con = super.getConnection();
		try {
			st = con.createStatement();
			String komut = "SELECT * FROM department ";
			rs = st.executeQuery(komut);
			String temp;
			while(rs.next()){
				temp = rs.getString(1)+" | "+rs.getString(2)+" | "+"\n";
				deps.add(temp);
			}
			rs.close();
			st.close(); // close statement
		} catch (SQLException e) {
			logger.warn("SQLException: during the getDeps");
		}
		super.closeConnection(con); 
		
		return deps;
	}
	

	public String[] getTables(){
		ArrayList<String> per = this.getPeopele();
		ArrayList<String> phn = this.getPhones();
		ArrayList<String> dep = this.getDeps();
		String[] res = new String[3];
		StringBuilder str = new StringBuilder();
		int i =0;
		str.append("<div class=\"div1\" id=\"divv1\" > <h6 align =\"center\"> <---  Person   --->  </h6>");
		str.append("<ul>");
		while(per.size() > i){
			str.append("<li>"+per.get(i)+"\n</li>");
			i++;
		}
		str.append("</ul> </div>");
		res[0] = str.toString();
		i=0;
		str = new StringBuilder();
		str.append("<div class=\"div1\" id=\"divv2\" > <h6 align =\"center\"> <---  Phone   --->  </h6>");
		str.append("<ul>");
		while(phn.size() > i){
			str.append("<li>"+phn.get(i)+"\n</li>");
			i++;
		}
		str.append("</ul> </div>");
		res[1] = str.toString();
		i=0;
		str = new StringBuilder();
		str.append("<div class=\"div1\" id=\"divv3\" > <h6 align =\"center\"> <---  Department   --->  </h6>");
		str.append("<ul>");
		while(dep.size() > i){
			str.append("<li>"+dep.get(i)+"</li>");
			i++;
		}
		str.append("</ul> </div>");
		res[2] = str.toString();
		return res;
	}
	
	public boolean addPerDep(PersonDTO per, DepartmentDTO dep,Connection con){
		boolean res = false;
		String komut =" INSERT INTO person_dep(kisiId,depId) VALUES("+per.getId()+","+dep.getId()+")";
		if(!isRelPerDep(per, dep, con) && per.getId() != -1 && dep.getId() != -1 ){
			try {
				st = con.createStatement();
				st.execute(komut);
				res = true;
			} catch (SQLException e) {
				logger.warn("SQLException: Person: "+per.toString()+" Department: "+dep.toString()+" during the addPerDep");
				rollBack(con);
			}finally{
				super.closeStatement(st);
			}
		}
		
		return res;
	}
	
	public boolean addPerPhone(PersonDTO per, PhoneDTO phn, Connection con){
		boolean res = false;
		String komut =" INSERT INTO person_phone(kisiId,phoneId) VALUES("+per.getId()+","+phn.getId()+")";
		if(!isRelPerPhone(per,phn,con)&& per.getId() != -1 && phn.getId() != -1){
			try {
				st = con.createStatement();
				st.execute(komut);
				res = true;
			} catch (SQLException e) {
				rollBack(con);
				logger.warn("SQLException: Person: "+per.toString()+" Phone: "+phn.toString()+" during the addPerPhone");
			}finally{
				super.closeStatement(st);
			}
		}
		
		return res;
	}
	
	public boolean addDepPhone(DepartmentDTO dep, PhoneDTO phn, Connection con){
		boolean res = false;
		String komut =" INSERT INTO dep_phone(depId,phoneId) VALUES("+dep.getId()+","+phn.getId()+")";
		if(!isRelDepPhone(dep, phn, con) && phn.getId() != -1 && dep.getId() != -1){
			try {
				st = con.createStatement();
				st.execute(komut);
				res = true;
			} catch (SQLException e) {
				rollBack(con);
				logger.warn("SQLException: Department: "+dep.toString()+" Phone: "+phn.toString()+" during the addDepPhone");
			}finally{
				super.closeStatement(st);
			}
		}
		
		return res;
	}
	
	/* CONTROL METHODS */
	private boolean isRelPerPhone(PersonDTO per, PhoneDTO phn,Connection con){
		boolean res = false;
		String komut = "SELECT * FROM person_phone pp WHERE pp.kisiId="+per.getId()+"AND pp.phoneId ="+phn.getId();
		try {
			st = con.createStatement();
			rs = st.executeQuery(komut);
			if(rs.next())
				res=true;
		} catch (SQLException e) {
			rollBack(con);
			logger.warn("SQLException: during the isRelPerPhone operation ");
		}finally{
			super.closeResultSet(rs);
			super.closeStatement(st);
		}
		return res;
	}
	
	private boolean isRelPerDep(PersonDTO per, DepartmentDTO dep,Connection con){
		boolean res = false;
		String komut = "SELECT * FROM person_dep pp WHERE pp.kisiId="+per.getId()+"AND pp.depId ="+dep.getId();
		try {
			st = con.createStatement();
			rs = st.executeQuery(komut);
			if(rs.next())
				res=true;
		} catch (SQLException e) {
			rollBack(con);
			logger.warn("SQLException: during the isRelPerDep operation ");
		}finally{
			super.closeResultSet(rs);
			super.closeStatement(st);
		}
		return res;
	}
	
	private boolean isRelDepPhone(DepartmentDTO dep,PhoneDTO phn,Connection con){
		boolean res = false;
		String komut = "SELECT * FROM dep_phone pp WHERE pp.depId="+dep.getId()+"AND pp.phoneId ="+phn.getId();
		try {
			st = con.createStatement();
			rs = st.executeQuery(komut);
			if(rs.next())
				res=true;
		} catch (SQLException e) {
			rollBack(con);
			logger.warn("SQLException: during the isRelDepPhone operation ");
		}finally{
			super.closeResultSet(rs);
			super.closeStatement(st);
		}
		return res;
	}

}
