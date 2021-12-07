package com.allwin.first.serviceImpl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allwin.first.model.Address;
import com.allwin.first.model.Employee;
import com.allwin.first.repo.AddressRepo;
import com.allwin.first.repo.EmployeeRepo;
import com.allwin.first.service.EmployeeService;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private AddressRepo addRepo;
	
	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@Override
	public List<Employee> searchEmployeeServ(String searchText) {
		return empRepo.searchEmployee(searchText);
	}
	
	@Override
	@Cacheable(value="cacheEmpL1", key = "'Address'+#empId")
	public List<Address> findAllByEmpIdServ(String empId) {
		return addRepo.findAllByEmpId(empId);
	}
	
	@Override
	@CacheEvict(value="cacheEmpL1", key = "'Address'+#empId")
	public int deleteAllByEmpIdServ(String empId) {
		return addRepo.deleteAllByEmpId(empId);
	}
	
	@Override
	public List<Address> searchAddressServ(String searchText) {
		return addRepo.searchAddress(searchText);
	}
	
	@Override
	@Cacheable(value="cacheEmpL1", key = "'Employee'+#empId")
	public Employee findOneByIdServ(String empId) {
		return empRepo.findOneById(empId);
	}
	
	@Override
	@CachePut(value="cacheEmpL1", key = "'Employee'+#employee.empId")
	public Employee addEmployeeServ(Employee employee) {
		return empRepo.save(employee);
	}
	
	@Override
	@Async
	public CompletableFuture<Employee> addAsyncEmployeeServ(Employee employee) {
		logger.info("Employee Added by "+Thread.currentThread().getName());
		return CompletableFuture.completedFuture(empRepo.save(employee));
	}
	
	@Override
	@CachePut(value="cacheEmpL1", key = "'Employee'+#employee.empId")
	public Employee updateEmployeeServ(Employee employee) {
		return empRepo.save(employee);
	}
	
	@Override
	@CacheEvict(value="cacheEmpL1", key = "'Employee'+#empId")
	public int deleteEmployeeServ(String empId) {
		return empRepo.deleteOneById(empId);
	}
	
	@Override
	@CacheEvict(value="cacheEmpL1", key = "'Address'+#address.empId")
	public Address addAddressServ(Address address) {
		return addRepo.save(address);
	}
	
}
