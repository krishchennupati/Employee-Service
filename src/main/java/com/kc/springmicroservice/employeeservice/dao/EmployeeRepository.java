package com.kc.springmicroservice.employeeservice.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kc.springmicroservice.employeeservice.dao.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

}
