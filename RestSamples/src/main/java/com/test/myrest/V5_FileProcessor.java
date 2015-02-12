package com.test.myrest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.dao.RestSQLDBAccessor;
import com.test.pojo.Employee;

@Path("/v5")
public class V5_FileProcessor {
	
	
	@Path("/upload")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String uploadFile(InputStream uploadedInputStream) {
        String resturnStr = "Hello World";
 
 /*       FileInputStream fileinputstream = (FileInputStream)uploadedInputStream;
		int content;
		StringBuilder sb = new StringBuilder();
		try {
			while ((content = fileinputstream.read()) != -1) {
				// convert to char and display it
				System.out.print((char) content);
				//resturnStr = resturnStr+((char)content);
				sb.append((char)content);
			}
			resturnStr = resturnStr+sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to Read the InputStream");
			resturnStr = "Failed to read the InputStream";
			e.printStackTrace();
		}finally{
			if(fileinputstream!=null)
				try {
					fileinputstream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
*/
        return resturnStr;

    }


}
