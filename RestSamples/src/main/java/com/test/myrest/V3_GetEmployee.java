package com.test.myrest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.myws.json.ToJSON;
import com.test.dao.RestSQLDBAccessor;

@Path("/v3")
public class V3_GetEmployee {
	
	@Path("/getEmployeeDetails/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String getEmployeeDetails(@QueryParam("name") String name){
				
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		String returnStr = null;
		
		if(name==null)
			returnStr="Must add emp as query String parameter for better result";
		else{
			try{
				con = RestSQLDBAccessor.getSQLDBConn().getConnection();
				String sql = "SELECT * FROM EMPLOYEE where FNAME=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, name);
				rs = ps.executeQuery();
				
				JSONArray jarray= ToJSON.toJASONArray(rs);
				returnStr= jarray.toString();
				
		}catch(Exception e){
			returnStr="Exception caught: "+e.toString();
			
		}	
		}
		
		
		
		return returnStr;
	}

}
