package com.springproject.springprojectemp.model;

import com.springproject.springprojectemp.entity.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)

public class EmployeeUtilResponse {

	private int code;
	
	private   String status;

	private String message;
	private  Object data;
	private  Object errorParams;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getErrorParams() {
		return errorParams;
	}
	public void setErrorParams(Object errorParams) {
		this.errorParams = errorParams;
	}
	
	
	
	
	
	
    
    
}
