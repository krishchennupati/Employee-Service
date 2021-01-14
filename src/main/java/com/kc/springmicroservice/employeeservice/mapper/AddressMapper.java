package com.kc.springmicroservice.employeeservice.mapper;

import org.mapstruct.Mapper;

import com.kc.springmicroservice.employeeservice.dao.model.Address;
import com.kc.springmicroservice.employeeservice.dto.AddressDTO;

@Mapper
public interface AddressMapper {

	AddressDTO getDTOFromDAO(Address address);

	Address getDAOFromDTO(AddressDTO addressDTO);
}
