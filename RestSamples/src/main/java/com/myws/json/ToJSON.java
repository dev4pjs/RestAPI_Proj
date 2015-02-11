package com.myws.json;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ToJSON {

	public static JSONArray toJASONArray(ResultSet rs) throws Exception{
		
		JSONArray json = new JSONArray();
		
		try{
			// 
			ResultSetMetaData rsmeta = rs.getMetaData();
			
			// loop through the result set
			while(rs.next()){
				
				// figures out how many column there 
				int columnNo = rsmeta.getColumnCount();
				System.out.println("Column COunt:"+columnNo);
				JSONObject jsonObj = new JSONObject();
				
				for(int i = 1; i<columnNo+1; i++){
					System.out.println("i == "+i);
					String columnName = rsmeta.getColumnLabel(i);
					System.out.print(columnName+" :");
					if(rsmeta.getColumnType(i)==java.sql.Types.ARRAY){
						System.out.println("ARRAY");
						jsonObj.put(columnName, rs.getArray(columnName));
						
					} else if(rsmeta.getColumnType(i)==java.sql.Types.BIGINT){
						System.out.println("BIGINT");	
						jsonObj.put(columnName, rs.getInt(columnName));
											
					} else if(rsmeta.getColumnType(i)==java.sql.Types.BOOLEAN){
						System.out.println("BOOLEAN");
						jsonObj.put(columnName, rs.getBoolean(columnName));
								
					} else if(rsmeta.getColumnType(i)==java.sql.Types.BLOB){
						System.out.println("BLOB");
						jsonObj.put(columnName, rs.getBlob(columnName));
						
					} else if(rsmeta.getColumnType(i)==java.sql.Types.CHAR){
						System.out.println("CHARACTER");
						jsonObj.put(columnName, rs.getCharacterStream(columnName));
						
					} else if(rsmeta.getColumnType(i)==java.sql.Types.DATE){
						System.out.println("DATE");
						jsonObj.put(columnName, rs.getDate(columnName));
						
					} else if(rsmeta.getColumnType(i)==java.sql.Types.DOUBLE){
						System.out.println("DOUBLE");
						jsonObj.put(columnName, rs.getDouble(columnName));
						
					} else if(rsmeta.getColumnType(i)==java.sql.Types.FLOAT){
						System.out.println("FLOAT");
						jsonObj.put(columnName, rs.getFloat(columnName));
						
					} else if(rsmeta.getColumnType(i)==java.sql.Types.LONGNVARCHAR){
						System.out.println("LONG VARCHAR");	
						jsonObj.put(columnName, rs.getString(columnName));
											
					} else if(rsmeta.getColumnType(i)==java.sql.Types.VARCHAR){
						System.out.println("VARCHAR");
						jsonObj.put(columnName, rs.getString(columnName));
							
					} else if(rsmeta.getColumnType(i)==java.sql.Types.INTEGER){
						System.out.println("INTEGER");
						jsonObj.put(columnName, rs.getInt(columnName));
						
					} else if(rsmeta.getColumnType(i)==java.sql.Types.TIME){
						System.out.println("TIME");
						jsonObj.put(columnName, rs.getTime(columnName));
						
					} else if(rsmeta.getColumnType(i)==java.sql.Types.TIMESTAMP){
						System.out.println("TIMESTAMP");
						jsonObj.put(columnName, rs.getTimestamp(columnName));
						
					} else if(rsmeta.getColumnType(i)==java.sql.Types.TINYINT){
						System.out.println("TINYINT");
						jsonObj.put(columnName, rs.getInt(columnName));
						
					} else if(rsmeta.getColumnType(i)==java.sql.Types.NUMERIC){
						System.out.println("NUMERIC");
						jsonObj.put(columnName, rs.getBigDecimal(columnName));						
					} else if(rsmeta.getColumnType(i)==java.sql.Types.SMALLINT){
						System.out.println("SAMLL INT");
						jsonObj.put(columnName, rs.getInt(columnName));
						
					}else{
					
						System.out.println("OBJECT");
						jsonObj.put(columnName, rs.getObject(columnName));
						
					}
					
				}// END of For Loop
				
				json.put(jsonObj);
				
			}
			
		}catch(Exception e){
			System.out.println("Exception thrown in ToJSON."+e);
		}finally{
			
		}
		
		return json;
	}
	
}
