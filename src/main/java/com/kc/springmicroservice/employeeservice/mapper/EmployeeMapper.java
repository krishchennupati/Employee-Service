package com.kc.springmicroservice.employeeservice.mapper;

import org.mapstruct.Mapper;

import com.kc.springmicroservice.employeeservice.dao.model.Employee;
import com.kc.springmicroservice.employeeservice.dto.EmployeeDTO;

@Mapper
public interface EmployeeMapper {

	EmployeeDTO getDTOFromDAO(Employee employee);

	Employee getDAOFromDTO(EmployeeDTO employeeDTO);
}
