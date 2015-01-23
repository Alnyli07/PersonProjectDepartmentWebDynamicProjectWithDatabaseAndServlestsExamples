package com.alnyli.service.dao;

import java.sql.*;

import org.apache.log4j.Logger;

import com.alnyli.dto.PersonDTO;
import com.alnyli.log.ProjeLogger;
import com.alnyli.service.db.ConnectionHelper;

public class PersonDAO extends ConnectionHelper {

	private ResultSet  rs;
	private Statement  st;
	private Logger logger;
	
	public PersonDAO(){
		super();
		super.init();
		logger = Logger.getLogger(PersonDAO.class);
		
	}
	/* ADD */
	public boolean addPerson(PersonDTO per, Connection con){
		boolean res = false;
		String komut = "INSERT INTO person(name,sirname) VALUES"+per.toString();
		this.getPersonID(per, con);
		if(!this.isPersonInDB(per, con) && !per.getName().isEmpty() && !per.getSirname().isEmpty()){
			try {
				st = con.createStatement();
				st.execute(komut);
				per.setId(this.getPersonID(per, con));
				res = true;
				logger.debug(" Person: "+per.toString()+" was added.");
			} catch (SQLException e) {
				rollBack(con);
				logger.warn(" SQLException: during  the Person: "+per.toString()+" adding operation. ");
			}finally{
				closeStatement(st);
			}

		}
		return res;
	}
	
	private int getPersonID(PersonDTO per, Connection con ){
		int res = -1;
		try {
			
			st=con.createStatement();
			String komut = "SELECT * FROM person p WHERE p.name ='"+per.getName()+"' AND p.sirname='"+per.getSirname()+"'";
			rs = st.executeQuery(komut);
			if(rs.next())
				res = Integer.parseInt(rs.getString(1));
			
		} catch (SQLException e) {
			logger.warn(" SQLException: Person: "+per.toString()+" during the getPersonID operation. ");
			rollBack(con);
		}finally{
			super.closeResultSet(rs);
			super.closeStatement(st);
		}
		
		return res;
	}
	
	/* look is there any Person */
	private  boolean isPersonInDB(PersonDTO per,Connection con ){
		boolean res = false;
		per.setId(this.getPersonID(per, con));
		try {
			st = con.createStatement();/* boyle id ye sahip kayit var mi ? */
			String komut = "SELECT * FROM person WHERE person.Id = "+per.getId() ;
			rs = st.executeQuery(komut);
			if(rs.next())
				res = true;
		} catch (SQLException e) {
			logger.warn(" SQLException : Person: "+per.toString()+" during the isPersonInDB operation.");
			rollBack(con);
		}finally {
			super.closeStatement(st);
			super.closeResultSet(rs); 
		}
		return res;
	}
	
	
	/* DELETE OPRs */
	public boolean delPerson(PersonDTO per,Connection con){
		boolean res = false;
		Statement st=null, st1=null, st2=null;
		if(this.isPersonInDB(per,con)){
			
			String komut  = "DELETE FROM person p WHERE p.Id ="+per.getId();
			String komut1 = "DELETE FROM person_phone p WHERE p.kisiId =  "+per.getId();
			String komut2 = "DELETE FROM person_dep p WHERE p.kisiId =  "+per.getId();
			try {
				st  = con.createStatement();
				st1 = con.createStatement();
				st2 = con.createStatement();
				st.execute(komut);
				st1.execute(komut1);
				st2.execute(komut2);
				res = true;
				logger.debug(" Person: "+per.toString()+" was deleted.");
			} catch (SQLException e) {
				rollBack(con);
				logger.warn(" SQLException : Person: "+per.toString()+" during the deleting operation.");
			}finally{
				super.closeStatement(st);
				super.closeStatement(st1);
				super.closeStatement(st2);
			}
		}
		return res;
	}
	
	
}
