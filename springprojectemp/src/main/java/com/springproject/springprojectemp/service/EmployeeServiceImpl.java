package com.springproject.springprojectemp.service;

import java.util.*;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import com.springproject.springprojectemp.entity.Employee;
import com.springproject.springprojectemp.model.EmployeeUtilResponse;

public interface EmployeeServiceImpl {
	
	
	public EmployeeUtilResponse saveEmployee(Employee employee);
//	public List<Employee> getAllEmployees();
	public List<String> getAllEmployeesByName();
	//public Map<Integer,String> getAllEmployeesByName();
	public String deleteEmployee(int id) ;
	
	public Employee getEmployee(int id);
	public Employee updateEmployee(Employee employee,int id);

}
