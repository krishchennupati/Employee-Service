package com.kc.springmicroservice.employeeservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kc.springmicroservice.employeeservice.EmployeeNotFoundException;
import com.kc.springmicroservice.employeeservice.dao.AddressRepository;
import com.kc.springmicroservice.employeeservice.dao.EmployeeRepository;
import com.kc.springmicroservice.employeeservice.dao.model.Address;
import com.kc.springmicroservice.employeeservice.dao.model.Employee;
import com.kc.springmicroservice.employeeservice.dto.AddressDTO;
import com.kc.springmicroservice.employeeservice.dto.EmployeeDTO;
import com.kc.springmicroservice.employeeservice.mapper.AddressMapper;
import com.kc.springmicroservice.employeeservice.mapper.EmployeeMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	AddressMapper addressMapper;
	
	@Override
	public List<EmployeeDTO> getAllEmployees() {
		log.info("Getting all data");
		List<EmployeeDTO> empList = new ArrayList<>();
		employeeRepository.findAll().forEach(emp -> empList.add(employeeMapper.getDTOFromDAO(emp)));
		return empList;
		
	}

	@Override
	public EmployeeDTO getEmployee(long id) {
		
		return employeeMapper.getDTOFromDAO(employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("No details found for employee id = " + id)));
	}

	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		Employee employee = employeeMapper.getDAOFromDTO(employeeDTO);
		employee.getAddressList().forEach(addr->addr.setEmployee(employee));
		return employeeMapper.getDTOFromDAO(employeeRepository.save(employee));
	}

	@Override
	public EmployeeDTO updateEmployeeAddress(Long empID,AddressDTO addressDTO) {

		Optional<Employee>  optionalEmployee = employeeRepository.findById(empID);
		if(!optionalEmployee.isPresent()) {
			
			throw   new EmployeeNotFoundException("No details found for employee id = " + empID);
		}
		Address address = addressMapper.getDAOFromDTO(addressDTO);
		Employee existingEmployeeResult = optionalEmployee.get();
		
		address.setAddressID(existingEmployeeResult.getAddressList().get(0).getAddressID());
		address.setEmployee(existingEmployeeResult);
		
		addressRepository.save(address);
		
		return employeeMapper.getDTOFromDAO(existingEmployeeResult);
	}
	
	@Override
	public EmployeeDTO updateEmployee(Long empID, EmployeeDTO employeeDTO) {

		Optional<Employee> optionalEmployee = employeeRepository.findById(empID);
		if (!optionalEmployee.isPresent()) {

			throw  new EmployeeNotFoundException("No details found for employee id = " + empID);
		}
		Employee employee = employeeMapper.getDAOFromDTO(employeeDTO);
		Employee existingEmployeeResult = optionalEmployee.get();

		employee.setId(existingEmployeeResult.getId());

		return employeeMapper.getDTOFromDAO(employeeRepository.save(employee));
	}

	@Override
	public List<AddressDTO> getEmployeeAddress(Long employeeID) {
		
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeID);
		if (!optionalEmployee.isPresent()) {

			throw  new EmployeeNotFoundException("No details found for employee id = " + employeeID);
		}
		
		Employee existingEmployeeResult = optionalEmployee.get();
		
		List<Address> addressList = existingEmployeeResult.getAddressList();
		
		List<AddressDTO> addressDTOList = new ArrayList<>();
		addressList.forEach(address->addressDTOList.add(addressMapper.getDTOFromDAO(addressRepository.findById(address.getAddressID()).orElse(new Address()))));
	
		return addressDTOList;
	}

	@Override
	public void deleteEmployee(long employeeID) {
		
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeID);
		if (!optionalEmployee.isPresent()) {

			throw  new EmployeeNotFoundException("No details found for employee id = " + employeeID);
		}
		log.info("Deleting empoyee with ID "+employeeID);
		employeeRepository.deleteById(employeeID);
		
	}

}
