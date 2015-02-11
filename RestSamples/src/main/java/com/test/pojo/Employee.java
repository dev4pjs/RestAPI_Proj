package com.test.pojo;

import java.sql.Date;

public class Employee {
	private String fNamr;
	private String lName;
	private String empCode;
	private Date bDate;
	private char sex;
	private int salary;
	
	
	public String getfNamr() {
		return fNamr;
	}
	public void setfNamr(String fNamr) {
		this.fNamr = fNamr;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
		
	@Override
	public String toString() {
		return new StringBuffer(" fNamr : ").append(this.fNamr)
				.append(" lName : ").append(this.lName).append(" empCode : ")
				.append(this.empCode).append(" bDate : ").append(this.bDate).append("sex").append(this.sex).append("salary").append(this.salary)
				.toString();
	}	
	
}
