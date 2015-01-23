package com.alnyli.service.db;

import java.sql.*;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;
import com.alnyli.service.db.properties.ConstantProperties;


public class ConnectionHelper {
	
	/*private final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private final String USER = "alnyli";
	private final String PASSWORD = "a1234b";
	private final String SSL = "ture";*/
	private Statement st;
	private Properties configureProperties;  // 
	private Logger logger;
	
	public ConnectionHelper() {
		
		 configureProperties = ConstantProperties.getProperties();
		 logger = Logger.getLogger(ConnectionHelper.class);
	}
	//DatabaseConfigure.properties
	public void init(){
		uptadeDataSource();
		try {
			Class.forName(configureProperties.getProperty("jdbc.driver"));
		} catch (ClassNotFoundException e){
			logger.fatal("Driver : "+configureProperties.getProperty("jdbc.driver"));
	    }
	}
	private void uptadeDataSource(){

		Jdbc3PoolingDataSource  source = new Jdbc3PoolingDataSource();
		//source.setServerName("localhost");
		//source.setDatabaseName("postgres");
		source.setUser(configureProperties.getProperty("jdbc.username"));
		source.setPassword(configureProperties.getProperty("jdbc.password"));
		source.setMaxConnections(10);
		source.setSsl(true);
		try {
			source.setUrl(configureProperties.getProperty("jdbc.url"));
			try {
				new InitialContext().rebind("DataSource", source);
			  }catch (NamingException e) {
				  logger.fatal( " Datasource rebind error : :"+source.toString());
			}
		} catch (SQLException e1) {
			logger.fatal("Invalid url: "+configureProperties.getProperty("jdbc.url"));
		}
	}
	
	public Connection getConnection(){
		Connection con =null;
		try {
			DataSource source = ( DataSource ) new InitialContext().lookup("DataSource");
			 con = source.getConnection();
			//con = DriverManager.getConnection(configureProperties.getJdbcUrl(),configureProperties.getJdbcUserName(),configureProperties.getJdbcUserPassword());
			 logger.debug(" Database Connection succeed.");
		} catch (SQLException e) {
			logger.error(" Database Connection Failed.");
			return null;
		}
		catch (NamingException e) {
			logger.error(" Datasource not FOUND ! :: "+"Datasource");
		}
		
		return con;
	}
	
	public Connection getTransectionConnection(){
		Connection con =null;
		try {
			 con = getConnection();
			 con.setAutoCommit(false);
			 logger.debug(" Database transectionConnection succeed." );
		} catch (SQLException e) {
			logger.error(" Database transectionConnection failed.");
		}
		
		return con;
	}
	
	
	public boolean closeConnection(Connection con){
		try{
			if(con != null){
				con.setAutoCommit(true);
				con.close();
			}
			logger.debug("Database Connection closed.");
			return true;
		} catch (SQLException e) {
			logger.error("Database ConnectionClose  failed !");
			return false;
		}
	}
	
	public boolean closeStatement(Statement st){
		boolean res = false;
		try {
			if(st != null)
				st.close();
			res = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(" Statement Close failed !");
		}
		return res;
	}
	
	public boolean closeResultSet(ResultSet rs){
		boolean res = false;
		try {
			if(rs != null)
				rs.close();
			res = true;
		} catch (SQLException e) {
			logger.error(" ResultSet Close failed !");
		}
		return res;
	}
	
	public boolean executeSQL(String komut){
		boolean res = false;
		Connection con = getConnection();
		try {
			st = con.createStatement();
			res = st.execute(komut);
			res = true;
		} catch (SQLException e) {
			logger.error(" SQL komut execute failed !");
		}
		finally{
			closeStatement(st);
			closeConnection(con); 
		}
		return res;
	}
	
	public void rollBack(Connection con){
		try {
			con.rollback();
			logger.debug(" Connection rollback succeed. ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(" Connection rollback failed. ");
		}
	}
	
}


