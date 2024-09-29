package com.springproject.springprojectemp.service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springproject.springprojectemp.dao.EmployeeDAO;
import com.springproject.springprojectemp.entity.Employee;
import com.springproject.springprojectemp.exception.ResourceNotFoundException;

import com.springproject.springprojectemp.model.EmployeeUtilResponse;
import com.springproject.springprojectemp.util.EmployeeUtil;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDAO dao;
	
	public EmployeeUtilResponse saveEmployee(Employee employee)
	{ 
		EmployeeUtilResponse emp1= new EmployeeUtilResponse();
	
		
		try {
			
			EmployeeUtilResponse validationDetails = EmployeeUtil.validateEmployee(employee);
            if(validationDetails.getStatus().equals("Failure")) {
                return validationDetails;
            }
			
			 Employee emp=dao.save(employee);
			
			emp1.setCode(0);
			emp1.setStatus("Success");
			emp1.setMessage("Employees Details Saved Successfully");
			
			emp1.setData(emp);
			
			
			
			
	/*		resp.put("code", 0);
			resp.put("status", "Success");
			resp.put("message", "Employees Data Added Successfully");
			resp.put("data", empp);
			System.out.println(resp.toString());
		//return "New employee added in Database,Name :"+employee.getFirstName()+"  "+employee.getLastName()+" "+ "ID :"+employee.getId();
		*/}
		catch(Exception e)
		{
			
			System.err.println(e.getMessage());
			emp1.setCode(1);
			emp1.setStatus("Failure");
		//	emp1.setMessage("Failed to save data");
			
			/*	resp.put("code", 1);
			resp.put("status", "Failed");
			resp.put("message", "Adding Data Failed");
		}
		return resp; 
		*/
		}
		return emp1;
	}	

	/*public List<Employee> getAllEmployees()
	{
		return (List<Employee>) dao.findAll(); 
		
	}
	*/
	/*public Map<Integer,String> getAllEmployeesByName()throws ResourceNotFoundException{
		
		
		List<Employee> employees = (List<Employee>)dao.findAll();
		boolean a=employees.isEmpty();
		if(a==true)
		{
			throw new ResourceNotFoundException("Database is Empty");
		}
		else
		{
			Map<Integer,String> map = new java.util.LinkedHashMap<Integer, String>();
	    	for(Employee employee : employees)
	    	{
	    		map.put(employee.getId(), employee.getFirstName() + " " + employee.getLastName());
	    	}
	    	return map;
			
		}
	}*/
	
	/*
	public List<Employee> getAllEmployeesByName() throws ResourceNotFoundException {
		List<Employee> employees = (List<Employee>)dao.findAll();
		List<Employee> list = new ArrayList<>();
		
		boolean a=employees.isEmpty();
			if(a==true)
			{
				throw new ResourceNotFoundException("Database is Empty");
			}
			else
			{
				employees.forEach(employee ->{
				 Employee e=new Employee();
		            e.setId(employee.getId());
		            e.setFirstName(employee.getFirstName());
		            e.setLastName(employee.getLastName());
		            e.setDepartment(employee.getDepartment());
		            e.setDesignation(employee.getDesignation());
		            e.setPhoneNo(employee.getPhoneNo());
		            e.setEmail(employee.getEmail());
		            e.setAddress(employee.getAddress());
		            list.add(e);
			});
				
			}
		
		return list;
	}
	*/
	
	public EmployeeUtilResponse getAllEmployeesByName(){
		
		EmployeeUtilResponse empr= new EmployeeUtilResponse();
		try {
			List<Employee> employees = (List<Employee>)dao.findAll();
			
			empr.setCode(0);
			empr.setStatus("Success");
			empr.setMessage("Employees Details Displayed Successfully");
			
			empr.setData(employees);
			
			
			/*resp.put("code", 0);
			resp.put("status", "Success");
			resp.put("message", "Employees Details Displayed Successfully");
			resp.put("data",employees);
			System.out.println(resp.toString());*/
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		/*	resp.put("code", 1);
			resp.put("status", "Failed");
			resp.put("message", "Employees Details Display Failed");*/
			
			empr.setCode(1);
			empr.setStatus("Failure");
			empr.setMessage("Employees Details Display Failed");
			
		
		}
		return empr;
	}
	
	public EmployeeUtilResponse getEmployee(int id) 
	{
		EmployeeUtilResponse emp2= new EmployeeUtilResponse();
		try 
		{
			Optional<Employee> employee=dao.findById(id);
			if(employee.isEmpty())
			{
				emp2.setCode(1);
				emp2.setStatus("Failure");
				emp2.setMessage("Employee with this ID does not exists");
				
				return emp2;
			}
			emp2.setCode(0);
			emp2.setStatus("Success");
			emp2.setMessage("Employees Details Displayed Successfully");
		
			emp2.setData(employee);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			/*resp.put("code", 1);
			resp.put("status", "Failed");
			resp.put("message", "Employees Details Display Failed");*/
		}
		return emp2;
	}
	
	public EmployeeUtilResponse deleteEmployee(int id) 
	{
	
		EmployeeUtilResponse empr= new EmployeeUtilResponse();
		try 
		{
			
			dao.deleteById(id);
			empr.setCode(0);
			empr.setStatus("Success");
			empr.setMessage("Employees Deleted Successfully");
			
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			empr.setCode(1);
			empr.setStatus("Failure");
			empr.setMessage("Employee with this ID does not exists");
			
			return empr;
		}
		return empr;
		
	}
	
	
	public EmployeeUtilResponse updateEmployee(Employee employee)
	{ 
		EmployeeUtilResponse emp3= new EmployeeUtilResponse();
	
		try 
		{
			
			Optional<Employee> existingEmployee=dao.findById(employee.getId());
			if(existingEmployee.isEmpty())
			{
				emp3.setCode(1);
				emp3.setStatus("Failure");
				emp3.setMessage("Employee with this Id does not Exists");
				return emp3;
			}
			EmployeeUtilResponse validationDetails = EmployeeUtil.validateEmployee(employee);
            if(validationDetails.getStatus().equals("Failure")) {
                return validationDetails;
            }
				Employee upEmployee=dao.save(employee);
				
				
				
				emp3.setCode(0);
				emp3.setStatus("Success");
				emp3.setMessage("Employees Updated Successfully");
				emp3.setData(upEmployee);
				
				
			
		}
		catch(Exception e)
		{
			
			System.err.println(e.getMessage());
			
		}
		return emp3; 
	}
}
	

