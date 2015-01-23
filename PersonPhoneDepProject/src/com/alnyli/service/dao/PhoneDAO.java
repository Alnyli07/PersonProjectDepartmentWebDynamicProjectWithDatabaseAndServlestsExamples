package com.alnyli.service.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.alnyli.dto.PhoneDTO;
import com.alnyli.service.db.ConnectionHelper;

public class PhoneDAO extends ConnectionHelper{

	private ResultSet  rs;
	private Statement  st;
	private Logger logger;
	
	public PhoneDAO() {
		super();
		super.init();
		logger = Logger.getLogger(PhoneDAO.class);
	}
	
	/* look is there any Phone */
	private  boolean isPhoneInDB(PhoneDTO phn, Connection con){
		boolean res = false;
		phn.setId(this.getPhoneID(phn, con));
		try {
			st = con.createStatement(); /* boyle id ye sahip kayit var mi ? */
			String komut = "SELECT * FROM phone WHERE phone.Id = "+phn.getId() ;
			rs = st.executeQuery(komut);
			if(rs.next())
				res = true;
		} catch (SQLException e){
			logger.error(" SQLException : Phone :"+phn.toString()+"during the isPhoneInDB operation ");
			rollBack(con);
		}finally {
			super.closeStatement(st);
			super.closeResultSet(rs);
		}
		return res;
	}
	

	private int getPhoneID(PhoneDTO phn, Connection con){
		int res = -1;
		try {
			st=con.createStatement();
			String komut = "SELECT * FROM phone p WHERE p.num ='"+phn.getNumber()+"'";
			rs = st.executeQuery(komut);
			if(rs.next())
				res = Integer.parseInt(rs.getString(1));
			
		} catch (SQLException e) {
			logger.error(" SQLException : Phone :"+phn.toString()+"during the getPhoneID operation ");
			rollBack(con);
		}finally{
			super.closeResultSet(rs);
			super.closeStatement(st);
		}
		
		return res;
	}

	public boolean delPhone(PhoneDTO phn,Connection con){
		boolean res = false;
		Statement st=null, st1=null, st2=null;
		if(this.isPhoneInDB(phn,con)){
			res = true;
			String komut  = "DELETE FROM phone p WHERE p.Id ="+phn.getId();
			String komut1 = "DELETE FROM person_phone p WHERE p.phoneId =  "+phn.getId();
			String komut2 = "DELETE FROM dep_phone p WHERE p.phoneId =  "+phn.getId();
			try{	
				st  = con.createStatement();
				st1 = con.createStatement();
				st2 = con.createStatement();
				st.execute(komut);
				st1.execute(komut1);
				st2.execute(komut2);
				res = true;
				logger.debug(" Phone : "+phn.toString()+" was deleted.");
			}catch(SQLException e){
				logger.error(" SQLException : Phone :"+phn.toString()+"during the delPhone operation ");
				rollBack(con);
			}finally{
				super.closeStatement(st);
				super.closeStatement(st1);
				super.closeStatement(st2);
			}
		
		}
		return res;
	}
	/* add  phone entry */
	public boolean addPhone(PhoneDTO phn,Connection con ){
		boolean res = false; /* phone adding command */
		String komut = "INSERT INTO phone(num) VALUES"+phn.toString();
		this.getPhoneID(phn, con);
		if(!this.isPhoneInDB(phn, con) && !phn.getNumber().isEmpty()){
			try {
				st = con.createStatement();
				st.execute(komut);
				phn.setId(this.getPhoneID(phn, con));
				res = true;
				logger.debug(" Phone : "+phn.toString()+" was added.");
			} catch (SQLException e) {
				logger.error(" SQLException : Phone :"+phn.toString()+"during the addPhone operation ");
				rollBack(con);
			}finally{
				super.closeStatement(st);
			}

		}
	
		return res;
	}
	

}
