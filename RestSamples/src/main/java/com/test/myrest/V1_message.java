package com.test.myrest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.sql.*;

import com.test.dao.RestSQLDBAccessor;

@Path("/mymessage")
public class V1_message {	
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnMessage(){
		String returnStr = "<h3>Hello World. <br>This is my First REST Service.</h3>";
		try{
			DataSource ds = RestSQLDBAccessor.getSQLDBConn();
			returnStr="Data Source Created Successfully:"+ds.toString();
		}catch(Exception e){
			returnStr=e.toString();
			System.out.println("Exception in rest class");
		}
		return returnStr;
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/version")
	public String getVersion(){
		return "version 1.01";
	}

}
