package com.kc.springmicroservice.employeeservice.service;

import java.util.List;

import com.kc.springmicroservice.employeeservice.dto.AddressDTO;
import com.kc.springmicroservice.employeeservice.dto.EmployeeDTO;

public interface IEmployeeService {

	List<EmployeeDTO> getAllEmployees();
	EmployeeDTO getEmployee(long id);
	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
	EmployeeDTO updateEmployeeAddress(Long employeeID, AddressDTO addressDTO);
	EmployeeDTO updateEmployee(Long employeeID, EmployeeDTO employeeDTO);
	List<AddressDTO> getEmployeeAddress(Long employeeID);
	void deleteEmployee(long id);
}
