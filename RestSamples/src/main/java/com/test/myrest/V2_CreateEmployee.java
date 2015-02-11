package com.test.myrest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.myws.json.ToJSON;
import com.test.dao.RestSQLDBAccessor;
import com.test.pojo.Employee;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Path("/v2")
public class V2_CreateEmployee {

	@Path("/createDB")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String createDB() {
		String result = "DB Not Created";
		Connection con = null;
		Statement s = null;
		try {

			con = RestSQLDBAccessor.getSQLDBConn().getConnection();
			String sql = "CREATE DATABASE IF NOT EXISTS TestDB";
			s = con.createStatement();
			s.execute(sql);
			result = "SUCCESSFULLY CREATED THE DB TestDB..impacted row: ";

		} catch (Exception e) {
			result = "Failed to CREATE THE DB TestDB ..Reason : "+e.getMessage();
			System.out.println("Failed to Create the DB TestDB" + e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (s !=null){
					s.close();
				}
			} catch (Exception e) {

			}
		}

		return result;

	}
	
	
	@Path("/dropEmployee")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String dropEmployeeTable() {
		String result = "Employee Table Not Dropped";
		Connection con = null;
		Statement s = null;
		try {

			con = RestSQLDBAccessor.getSQLDBConn().getConnection();
			String sql = "Drop TABLE EMPLOYEE";
			s = con.createStatement();
			s.execute(sql);
			result = "EMPLOYEE TABLE SUCESSFULLY DROPPED ";

		} catch (Exception e) {
			result = "Exception caught..Failed to DROP TABLE ..Reason : "+e.getMessage();
			
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (s !=null){
					s.close();
				}
			} catch (Exception e) {

			}
		}

		return result;

	}
	
	
	
	
	@Path("/createEmployeeTable")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String createTable() {
		String result = "Table  Not Created";
		Connection con = null;
		Statement s = null;
		try {

			con = RestSQLDBAccessor.getSQLDBConn().getConnection();
			String sql = "CREATE TABLE IF NOT EXISTS EMPLOYEE ( FNAME VARCHAR(25) NOT NULL, LNAME VARCHAR(15) NOT NULL, EMP_CODE VARCHAR(9) NOT NULL, BDATE DATE, SEX CHAR, SALARY INTEGER)";
			s = con.createStatement();
			s.execute(sql);
			result = "SUCCESSFULLY CREATED THE Table employee..impacted row: ";

		} catch (Exception e) {
			result = "Failed to CREATE the Table employee ..Errro "+e.getMessage();
			System.out.println("Failed to Create the DB TestDB" + e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (s !=null){
					s.close();
				}
			} catch (Exception e) {

			}
		}

		return result;

	}
	
	@Path("/insertEmployee/{id}")	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertEmployee(@QueryParam("id") JSONObject employee){
		String returnStr = "Did not inserted any Data. Prg exited with error";
		
		PreparedStatement ps = null;
		Connection con = null;
		
		try{
				con = RestSQLDBAccessor.getSQLDBConn().getConnection();
				String sql = "INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?, ?, ?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, employee.getString("fNamr"));
				ps.setString(2, employee.getString("lName"));
				ps.setString(3, employee.getString("empCode"));				
				ps.setDate(4, convertToDate(employee.getString("bDate")));
				ps.setString(5, employee.getString("sex").trim());
				ps.setInt(6, employee.getInt("salary"));
				int i = ps.executeUpdate();				
				returnStr="Successfully inserted the employee data into the employee table. Inserted row count :"+i;
		}catch(Exception e){
			returnStr="Exception caught: "+e.toString();
			
		}	
		
		return returnStr;
	}
	
	@Path("/allEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public String getAllEmployee(){
		String returnStr = "No Employee Records could able to retrieve.";
		
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		
		try{
				con = RestSQLDBAccessor.getSQLDBConn().getConnection();
				String sql = "SELECT * FROM EMPLOYEE";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				JSONArray jarray= ToJSON.toJASONArray(rs);
				returnStr= jarray.toString();
				
		}catch(Exception e){
			returnStr="Exception caught: "+e.toString();
			
		}	
		
		return returnStr;
	}

	private Date convertToDate(String bDate)throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		java.sql.Date date =null;
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
