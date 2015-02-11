package com.test.myrest;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;

import com.test.dao.RestSQLDBAccessor;
import com.test.pojo.Employee;

@Path("/v4")
public class V4_InsertEmployeeData {
	
/*	@Path("/insertEmployee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String InsertEmployee(JSONObject json){
		
		//Response response =null;
		String eName = null;
		eName = empName;
		
		return eName;
	}*/
	
	@Path("/insertEmployee")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public String InsertEmployee(Employee empl){
		
		String returnStr = "Nothing to Update";
		PreparedStatement ps = null;
		Connection con = null;
		
		try{
				con = RestSQLDBAccessor.getSQLDBConn().getConnection();
				System.out.println("CHECK 1");	
				String sql = "INSERT INTO EMPLOYEE VALUES (?, ?, ?, ?, ?, ?)";
				System.out.println("CHECK 2");
				//Employee empl = (Employee)jsonEmp.get("emp");
		System.out.println("CHECK 2.1");		
				ps = con.prepareStatement(sql);
				System.out.println("CHECK 3");	
				ps.setString(1, empl.getfNamr());
				System.out.println("CHECK 4");	
				ps.setString(2, empl.getlName());
				System.out.println("CHECK 5");	
				ps.setString(3, empl.getEmpCode());	
				System.out.println("CHECK 6");	
				ps.setDate(4, empl.getbDate());
				System.out.println("CHECK 7");	
				ps.setString(5, empl.getSex()+"");
				System.out.println("CHECK 8");	
				ps.setInt(6, empl.getSalary());
				System.out.println("CHECK 9");	
				int i = ps.executeUpdate();	
				System.out.println("CHECK 10");	
				returnStr="Successfully inserted the employee data into the employee table. Inserted row count :"+i;
		}catch(Exception e){
			returnStr="Exception caught: "+e.toString();
		}	
		return returnStr;
	}

}
