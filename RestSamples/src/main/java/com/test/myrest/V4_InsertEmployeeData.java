package com.test.myrest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.test.dao.RestSQLDBAccessor;
import com.test.pojo.Employee;

@Path("/v4")
public class V4_InsertEmployeeData {

	/*
	 * @Path("/insertEmployee")
	 * 
	 * @POST
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON) public String
	 * InsertEmployee(JSONObject json){
	 * 
	 * //Response response =null; String eName = null; eName = empName;
	 * 
	 * return eName; }
	 */
	@Path("/insertEmployee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String InsertEmployee(JSONObject data) {

		String returnStr = "The JSON Object :";
		PreparedStatement ps = null;
		Connection con = null;

		try {
			con = RestSQLDBAccessor.getSQLDBConn().getConnection();
			String sql = "INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);

			Iterator e = data.keys();
			int i = 0;
			while (e.hasNext()) {
				i++;
				String key = (String) e.next();
				String value = data.getString(key);

				if (key.equalsIgnoreCase("bDate")) {
					ps.setDate(i, getBDate(value));

				} else if (key.equalsIgnoreCase("salary")) {
					ps.setInt(i, Integer.parseInt(value));

				} else {
					ps.setString(i, value);
				}
				returnStr = returnStr + key + ": " + value;
			}
			int row = ps.executeUpdate();
			returnStr = returnStr
					+ " has been sucessfully Inserted in the Employee Table";
		} catch (Exception e) {
			returnStr = returnStr + " Exception : " + e.getMessage();
		}

		return returnStr;
	}

	private static Date getBDate(String bDate) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		java.sql.Date date = null;
		try {
			java.util.Date bdate = formatter.parse(bDate);
			date = new java.sql.Date(bdate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Failed to Convert the Date Object");
		}
		return date;
	}

}
