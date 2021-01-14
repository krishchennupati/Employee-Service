package com.kc.springmicroservice.employeeservice.dto;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.EntityModel;

import com.kc.springmicroservice.employeeservice.dao.model.Employee;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ExternalDocumentation(description = "This is Address object")
public class AddressDTO extends EntityModel<AddressDTO>{

	@NotNull(message = "address1 should not be empty")
	private String address1;
	private String address2;
	@NotNull(message = "city should not be empty")
	private String city;
	@NotNull(message = "state should not be empty")
	private String state;
	@NotNull(message = "zipCode should not be empty")
	private String zipCode;
	@NotNull(message = "addressType should not be empty")
	private String addressType;
	
	private Long addressID;
	
	//private EmployeeDTO employee;
	
	private long version;
}
