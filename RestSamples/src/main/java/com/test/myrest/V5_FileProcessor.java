package com.test.myrest;

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
	
	
    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    public String uploadFile(InputStream uploadedInputStream) {
        String resturnStr = uploadedInputStream.toString();
 

        return resturnStr;

    }


}
