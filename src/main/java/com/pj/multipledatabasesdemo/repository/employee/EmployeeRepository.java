package com.pj.multipledatabasesdemo.repository.employee;

import com.pj.multipledatabasesdemo.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
}
