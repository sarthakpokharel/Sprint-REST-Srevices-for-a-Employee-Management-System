package com.web.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.example.exception.ResourceNotFoundException;
import com.web.example.model.Employee;
import com.web.example.repository.EmployeeRepository;
import com.web.example.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent())
//			return employee.get();
//		else
//			throw new ResourceNotFoundException("Employee","id",employee);
		
		return employeeRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Employee","id",id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
	Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()->
	new ResourceNotFoundException("Employee","ID",id));
	
	existingEmployee.setFirstName(employee.getFirstName());
	existingEmployee.setLastName(employee.getLastName());
	existingEmployee.setEmail(employee.getEmail());
	
	//save existing employee to db
	
	employeeRepository.save(existingEmployee);
	
	
	
	return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		
		//check whether an employee exists in DB or not 
		employeeRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Employee","ID",id)
				);
		
		
		employeeRepository.deleteById(id);		
	}
}
