package com.springproject.springprojectemp.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.springprojectemp.common.UserConstant;
import com.springproject.springprojectemp.dao.UserRepository;
import com.springproject.springprojectemp.entity.Employee;
import com.springproject.springprojectemp.entity.User;
import com.springproject.springprojectemp.exception.ResourceNotFoundException;
import com.springproject.springprojectemp.model.EmployeeUtilResponse;
import com.springproject.springprojectemp.service.EmployeeService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/employeelogin")
	public EmployeeUtilResponse login(@RequestHeader("Authorization")String auth) {
       String pair=new String(Base64.getDecoder().decode(auth.substring(6)));
       String password=pair.split(":")[1];
       String userName=pair.split(":")[0];
       
        Optional<User> optionalUser= repository.findByUserName(userName);
        EmployeeUtilResponse response= new EmployeeUtilResponse();
        if(!optionalUser.isEmpty())
        {
        	User u=optionalUser.get();
        	
			if(passwordEncoder.matches(password,u.getPassword()))
        	{
        		 	response.setCode(0);
        	    	response.setStatus("Success");
        	    	response.setMessage("User Authenticated Successfully");
        	    	response.setData(optionalUser.get());
        	        return response;
        	}
        	else {
        		response.setCode(1);
            	response.setStatus("Failure");
            	response.setMessage("Wrong User Credentials");
            	
        	}
        	
        
        }
        else {
        	response.setCode(1);
        	response.setStatus("Failure");
        	response.setMessage("Wrong User Credentials");
        }
    	return response;
    }
	
	@PostMapping("/employee")
	@Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public EmployeeUtilResponse saveEmployee(@RequestBody Employee employee)
	{
		//service class method needed to save employee details
		float emppaid;
		float sal;
		emppaid=employee.getAmount();
		sal=emppaid*12;
		employee.setTotal(sal);
		 return employeeService.saveEmployee(employee);
		
		
	}
	
/*	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees()
	{
	        List<Employee> employees = employeeService.getAllEmployees();
	        return employees;
	   
		
	}
	*/
	
	/*@GetMapping("/getAllEmployee")
	public Map<Integer,String> getAllEmployeesByName() throws ResourceNotFoundException 
	{		
    	return employeeService.getAllEmployeesByName();
	}*/
	
	@GetMapping("/employee")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR') or hasAuthority('ROLE_USER')")
	
	//map<List<employee>,obj>
	public EmployeeUtilResponse getAllEmployeesByName() throws ResourceNotFoundException 
	{	
		return employeeService.getAllEmployeesByName();
	}
	
	@DeleteMapping("/employee/{id}")
	@Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public  EmployeeUtilResponse deleteEmployee(@PathVariable int id) 
	{
		return employeeService.deleteEmployee(id);
	}
	
	
	@GetMapping("/employee/{id}")
	@Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public EmployeeUtilResponse getEmployee(@PathVariable int id) 
	{
		return employeeService.getEmployee(id);
	}
	
	@PutMapping("/employee/{id}")
	@Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public  EmployeeUtilResponse updateEmployee(@RequestBody Employee employee) 
	{
		float emppaid;
		float sal;
		emppaid=employee.getAmount();
		sal=emppaid*12;
		employee.setTotal(sal);
		return employeeService.updateEmployee(employee);
	}
	
}
