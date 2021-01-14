package com.kc.springmicroservice.employeeservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kc.springmicroservice.employeeservice.dao.EmployeeRepository;
import com.kc.springmicroservice.employeeservice.dto.AddressDTO;
import com.kc.springmicroservice.employeeservice.dto.EmployeeDTO;
import com.kc.springmicroservice.employeeservice.service.IEmployeeService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

	@Autowired
	IEmployeeService employeeService;


	Logger LOGGER = LoggerFactory.getLogger("EmployeeController");


	@Operation(description = "Returns all the Employees")
	@GetMapping("/")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

		List<EmployeeDTO> employeeList = employeeService.getAllEmployees();
		return new ResponseEntity<List<EmployeeDTO>>(employeeList, HttpStatus.OK);

	}
	
	@Operation(description = "Retrieve  Employee details using employeeID")
	@GetMapping("/{employeeID}")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("employeeID") Long employeeID) {

		EmployeeDTO employeeDTO = employeeService.getEmployee(employeeID);
		
		WebMvcLinkBuilder linkToAll = linkTo(methodOn(this.getClass()).getAllEmployees());
		employeeDTO.add(linkToAll.withRel("all-employees"));

		WebMvcLinkBuilder linkToSelf = linkTo(methodOn(this.getClass()).getEmployee(employeeID));
		employeeDTO.add(linkToSelf.withRel("self-link"));

		return new ResponseEntity<EmployeeDTO>(employeeDTO, HttpStatus.OK);

	}
	
	@Operation(description = "Add  Employee details")
	@PostMapping
	public ResponseEntity<HttpHeaders> addEmployee(@Validated @RequestBody EmployeeDTO  employeeDTO) {
		
		EmployeeDTO employeeDTOResult = employeeService.createEmployee(employeeDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/api/v1/employee/" + employeeDTOResult.getId());
		return new ResponseEntity<HttpHeaders>(headers, HttpStatus.CREATED);
	}
	

	@Operation(description = "Updates Employee details using employeeID")
	@PutMapping("/{employeeID}")
	public ResponseEntity<HttpHeaders> updateEmployee(@PathVariable("employeeID") Long employeeID,@Validated @RequestBody EmployeeDTO  employeeDTO) {
		
		EmployeeDTO employeeDTOResult = employeeService.updateEmployee(employeeID,employeeDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/api/v1/employee/" + employeeDTOResult.getId());
		return new ResponseEntity<HttpHeaders>(headers, HttpStatus.CREATED);
	}
	
	@Operation(description = "Updates employee address using employeeID")
	@PutMapping("/{employeeID}/address")
	public ResponseEntity<HttpHeaders> updateEmployeeAddress(@PathVariable("employeeID") Long employeeID,@Validated @RequestBody AddressDTO  addressDTO) {
		
		EmployeeDTO employeeDTOResult = employeeService.updateEmployeeAddress(employeeID,addressDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", "/api/v1/employee/" + employeeDTOResult.getId());
		return new ResponseEntity<HttpHeaders>(headers, HttpStatus.CREATED);
	}
	
	
	@Operation(description = "Retreives employee address using employeeID")
	@GetMapping("/{employeeID}/address")
	public ResponseEntity<List<AddressDTO>> getEmployeeAddress(@PathVariable("employeeID") Long employeeID) {
		
		List<AddressDTO> addressDTOResult = employeeService.getEmployeeAddress(employeeID);
		return new ResponseEntity<List<AddressDTO>>(addressDTOResult, HttpStatus.OK);
	}
	
	@Operation(description = "Delete the employee using employeeID")
	@DeleteMapping("/{employeeID}")
	public ResponseEntity<List<AddressDTO>> deleteEmployeeAddress(@PathVariable("employeeID") Long employeeID) {
		
		 employeeService.deleteEmployee(employeeID);
		 return ResponseEntity.noContent().build();
	}
}
