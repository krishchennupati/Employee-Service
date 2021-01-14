package com.kc.springmicroservice.employeeservice.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kc.springmicroservice.employeeservice.dao.model.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

}
