package com.test.dao;

import javax.naming.*;
import javax.sql.*;

public class RestSQLDBAccessor {
	
	private static DataSource dbSQL, dbSQL2 = null;
	private static Context ctx = null;
	
	/**
	 * This Method will create a JDBC data source of SQLDB(actually DB2) in Bluemix environment.
	 * @return
	 * @throws Exception
	 */
	public static DataSource getSQLDBConn() throws Exception{
		
		if(dbSQL != null){
			return dbSQL;
		}
		
		try{
			if(ctx==null){
				ctx= new InitialContext();
			}
				//dbSQL=(DataSource) ctx.lookup("jdbc/MyDataSourcedb2");
				dbSQL=(DataSource) ctx.lookup("jdbc/mysql-purujit");
				System.out.println("dqlDB ==>"+dbSQL.toString());
		}catch(Exception e){
				System.out.println("Failed to create the  Data Source."+e);
		}
		
		return dbSQL;
	}
	
	

}
