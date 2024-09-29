package com.springproject.springprojectemp.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.springprojectemp.common.UserConstant;
import com.springproject.springprojectemp.dao.UserRepository;
import com.springproject.springprojectemp.entity.User;
import com.springproject.springprojectemp.model.EmployeeUtilResponse;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class UserController {

	 
		@Autowired
	    private UserRepository repository;

	    @Autowired
	    private BCryptPasswordEncoder passwordEncoder;
	
	    
	    
	 @PostMapping("/join")
	    public EmployeeUtilResponse joinGroup(@RequestBody User user) {
	        user.setRoles(UserConstant.DEFAULT_ROLE);//USER
	        String encryptedPwd = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encryptedPwd);
	       
	        Optional<User> optionalUser= repository.findByUserName(user.getUserName());
	        EmployeeUtilResponse response= new EmployeeUtilResponse();
	        if(!optionalUser.isEmpty())
	        {
	        	
	        	response.setCode(1);
	        	response.setStatus("Failure");
	        	response.setMessage("User registeration Failed ,Already Exists");
	        	return response;
	        }
	        
	        repository.save(user);
	        response.setCode(0);
        	response.setStatus("Success");
        	response.setMessage("User registered Successfully");
	        return response;
	    }
	//If loggedin user is ADMIN -> ADMIN OR MODERATOR
	    //If loggedin user is MODERATOR -> MODERATOR
	 
	 @GetMapping("/access/{userId}/{userRole}")
	    //@Secured("ROLE_ADMIN")
	 @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
	 public String giveAccessToUser(@PathVariable int userId, @PathVariable String userRole, Principal principal) {
	        User user = repository.findById(userId).get();
	        List<String> activeRoles = getRolesByLoggedInUser(principal);
	        String newRole = "";
	        if (activeRoles.contains(userRole)) {
	            newRole = user.getRoles() + "," + userRole;
	            user.setRoles(newRole);
	        }
	        repository.save(user);
	        return "Hi " + user.getUserName() + " New Role assign to you by " + principal.getName();
	    }
	 
	 @GetMapping("/gettest")
	    @Secured("ROLE_ADMIN")
	    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    public List<User> loadUsers() {
	        return repository.findAll();
	    }

	    @GetMapping("/test")
	    @PreAuthorize("hasAuthority('ROLE_USER')")
	    public String testUserAccess() {
	        return "user can only access this !";
	    }
	 
	 private List<String> getRolesByLoggedInUser(Principal principal) {
	        String roles = getLoggedInUser(principal).getRoles();
	        List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
	        if (assignRoles.contains("ROLE_ADMIN")) {
	            return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
	        }
	        if (assignRoles.contains("ROLE_MODERATOR")) {
	            return Arrays.stream(UserConstant.MODERATOR_ACCESS).collect(Collectors.toList());
	        }
	        return Collections.emptyList();
	    }
	 
	 private User getLoggedInUser(Principal principal) {
	        return repository.findByUserName(principal.getName()).get();
	    }
}
