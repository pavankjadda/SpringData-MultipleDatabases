package com.pj.multipledatabasesdemo.web;

import com.pj.multipledatabasesdemo.domain.employee.Employee;
import com.pj.multipledatabasesdemo.repository.employee.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController( "/api/v1/employee")
public class EmployeeController
{
	private final EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeRepository employeeRepository)
	{
		this.employeeRepository = employeeRepository;
	}

	@GetMapping("/find/all")
	public List<Employee> findAll()
	{
		return employeeRepository.findAll();
	}
}
