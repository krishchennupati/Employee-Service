package com.kc.springmicroservice.employeeservice.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kc.springmicroservice.employeeservice.dao.model.Address;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ExternalDocumentation(description = "This is Employee object")
public class EmployeeDTO extends EntityModel<EmployeeDTO> {

	@NotNull(message = "firstName should not be empty")
	private String firstName;
	@NotNull(message = "lastName should not be empty")
	private String lastName;
	private long id;
	private long version;
	private String department;
    @JsonFormat(pattern="yyyy-MM-dd")
	private Date dateOfJoining;
	//private List<Address> addressList;
	private List<AddressDTO> addressList;
}
