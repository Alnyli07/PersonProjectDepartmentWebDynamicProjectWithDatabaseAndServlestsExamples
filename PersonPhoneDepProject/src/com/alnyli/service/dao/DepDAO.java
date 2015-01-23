package com.alnyli.service.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.alnyli.dto.DepartmentDTO;
import com.alnyli.service.db.ConnectionHelper;

public class DepDAO  extends ConnectionHelper {

	private ResultSet  rs;
	private Statement  st;
	private Logger logger;
	
	public DepDAO() {
		super();
		super.init();
		logger = Logger.getLogger(DepDAO.class);
	}
	
	public boolean addDepartment(DepartmentDTO dep,Connection con){
		boolean res = false;
		String komut = "INSERT INTO department(name) VALUES"+dep.toString();
		this.getDepID(dep, con);
		if(!this.isDepInDB(dep, con) && !dep.getName().isEmpty()){
			try {
				st = con.createStatement();
				st.execute(komut);
				dep.setId(this.getDepID(dep, con));
				res = true;
				logger.debug(" Department : "+dep.getName()+" was added." );
			} catch (SQLException e) {
				rollBack(con);
				logger.warn(" SQLException : Department: "+dep.toString()+" during the adding operation.");
				
			}finally{
				closeStatement(st);
			}
		}
		return res;
	}
	
	public boolean delDep(DepartmentDTO d,Connection con){
		boolean res = false;
		Statement st=null, st1=null, st2=null;
		if(this.isDepInDB(d,con)){
			res = true;
			String komut  = "DELETE FROM department p WHERE p.Id ="+d.getId();
			String komut1 = "DELETE FROM person_dep p WHERE p.depId =  "+d.getId();
			String komut2 = "DELETE FROM dep_phone p WHERE p.depId =  "+d.getId();
			try {
				st  = con.createStatement();
				st1 = con.createStatement();
				st2 = con.createStatement();
				st.execute(komut);
				st1.execute(komut1);
				st2.execute(komut2);
				res = true;
				logger.debug(" Department : "+d.getName()+" was deleted." );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				rollBack(con);
				logger.warn(" SQLException : Department: "+d.toString()+" during the deleting operation.");
				
			}finally{
				closeStatement(st);
				closeStatement(st1);
				closeStatement(st2);
			}
		}
		return res;
		}
		
	private int getDepID(DepartmentDTO dep, Connection con ){
		int res = -1;
		try {
			st=con.createStatement();
			String komut = "SELECT * FROM department d WHERE d.name ='"+dep.getName()+"'";
			rs = st.executeQuery(komut);
			if(rs.next())
				res = Integer.parseInt(rs.getString(1));
			
		} catch (SQLException e) {
			logger.warn(" SQLException : Department: "+dep.toString()+" during the getDepId operation.");
			rollBack(con);
		}finally{
			closeResultSet(rs);
			closeStatement(st);
		}
		
		return res;
	}
	/* look is there any Department */
	private  boolean isDepInDB(DepartmentDTO dep,Connection con){
		boolean res = false;
		dep.setId(this.getDepID(dep, con));
		try{
			st = con.createStatement();/* boyle id ye sahip kayit var mi ? */
			String komut = "SELECT * FROM department d WHERE d.Id = "+dep.getId() ;
			rs = st.executeQuery(komut);
			if(rs.next())
				res = true;
		} catch (SQLException e) {
			logger.warn(" SQLException : Department: "+dep.toString()+" during the adding operation.");
			rollBack(con);
		}
		finally {
			closeStatement(st);
			closeResultSet(rs);
		}
		
		return res;
	}
	
	
	

}
