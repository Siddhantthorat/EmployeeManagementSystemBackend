package com.springproject.springprojectemp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.springprojectemp.entity.Employee;

public interface EmployeeDAO extends JpaRepository<Employee,Integer> {

	

}
