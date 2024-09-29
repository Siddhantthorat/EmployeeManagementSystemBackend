package com.springproject.springprojectemp.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.springproject.springprojectemp.entity.Employee;
import com.springproject.springprojectemp.model.EmployeeUtilResponse;

public class EmployeeUtil {
			
	    public static EmployeeUtilResponse validateEmployee(Employee employee) {
	        EmployeeUtilResponse response = new EmployeeUtilResponse();
	        Map<String,String> errorParam = new LinkedHashMap<>();
	        Boolean isValid = true;
	        Pattern digitRegex = Pattern.compile("\\d");


	        String firstName = employee.getFirstName();
	        if(firstName!=null && !firstName.isEmpty()) {
	            Matcher m = digitRegex.matcher(firstName);
	            if(m.find()) {
	                isValid= false;
	                errorParam.put("firstName","Enter a valid Firstname");
	            }
	        }
	        else {
	            isValid= false;
	            errorParam.put("firstName","Firstname should not be blank ");
	        }


	        String lastName = employee.getLastName();
	        if(lastName!=null && !lastName.isEmpty()) {
	            Matcher m = digitRegex.matcher(lastName);
	            if(m.find()) {
	                isValid= false;
	                errorParam.put("lastName","Enter a valid Lastname");
	            }
	        }
	        else {
	            isValid= false;
	            errorParam.put("lastName","Lastname should not be blank");
	        }


	        String department = employee.getDepartment();
	        if(department!=null && !department.isEmpty()) {
	            Matcher m = digitRegex.matcher(department);
	            if(m.find()) {
	                isValid= false;
	                errorParam.put("department","Enter the correct Department");
	            }
	        }
	        else {
	            isValid= false;
	            errorParam.put("department","Enter Valid Department");
	        }

	 

	        String designation = employee.getDesignation();
	        if(designation!=null && !designation.isEmpty()) {
	            Matcher m = digitRegex.matcher(designation);
	            if(m.find()) {
	                isValid= false;
	                errorParam.put("designation","Enter the correct Designation");
	            }
	        }
	        else {
	            isValid= false;
	            errorParam.put("designation","Enter Valid  Designation");
	        }

	        Long phoneNo = employee.getPhoneNo();
	        if(phoneNo!=null) {
	            if(!String.valueOf(phoneNo).matches("^\\d{10}$")) {
	                isValid= false;
	                errorParam.put("phoneNo","Enter a valid PhoneNo");
	            }
	        }
	        else {
	            isValid= false;
	            errorParam.put("phoneNo","PhoneNo should not be blank");
	        }
	        

	        String email = employee.getEmail();
	        if(email!=null && !email.isEmpty()) {
	            if(!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
	                isValid= false;
	                errorParam.put("email","Enter a proper email");
	            }
	        }
	        else {
	            isValid= false;
	            errorParam.put("email","Email should not be blank");
	        }

	        String address = employee.getAddress();
	        if(address!=null && !address.isEmpty()) {
	            if(!address.matches(address)){
	                isValid= false;
	                errorParam.put("email","Enter a proper address");
	            }
	        }
	        else {
	            isValid= false;
	            errorParam.put("email","Email should not be blank");
	        }

	       

	        if(isValid) {
	            response.setStatus("Success");
	            response.setMessage("Employee validated successful");
	        }
	        else {
	            response.setCode(1);
	            response.setStatus("Failure");
	            response.setMessage("Employee validation failed");
	            response.setErrorParams(errorParam);
	        }
	        return response;
	    }}


