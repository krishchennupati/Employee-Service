package com.kc.springmicroservice.employeeservice.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kc.springmicroservice.employeeservice.dao.AddressRepository;
import com.kc.springmicroservice.employeeservice.dao.EmployeeRepository;
import com.kc.springmicroservice.employeeservice.dao.model.Address;
import com.kc.springmicroservice.employeeservice.dao.model.Employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Slf4j

public class EmployeeBootLoader implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;
	


	@Override
	public void run(String... args) throws Exception {

		Employee employee = Employee.builder().firstName("test").lastName("sam").department("HR").dateOfJoining(new Date()).build();
		Address address = Address.builder().address1("123 main st").city("columbia").state("sc").zipCode("29212")
				.addressType("private").employee(employee).build();
		Address address1 = Address.builder().address1("345 main st").city("greenville").state("sc").zipCode("29212")
				.addressType("mailing").employee(employee).build();
		employee.setAddressList(Arrays.asList(address, address1));
		
		employeeRepository.save(employee);

		
		Employee employee1 = Employee.builder().firstName("adam").lastName("cohen").department("Accounts").dateOfJoining(new Date()).build();
		Address address2 = Address.builder().address1("567 main st").city("columbia").state("sc").zipCode("29212")
				.addressType("private").employee(employee1).build();
		Address address3 = Address.builder().address1("789 main st").city("greenville").state("sc").zipCode("29212")
				.addressType("mailing").employee(employee1).build();
		employee1.setAddressList(Arrays.asList(address2, address3));
		
		employeeRepository.save(employee1);
		log.info("Number of records in the database -- " + employeeRepository.count());
	}
}
