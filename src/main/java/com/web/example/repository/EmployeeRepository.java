package com.web.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.example.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
