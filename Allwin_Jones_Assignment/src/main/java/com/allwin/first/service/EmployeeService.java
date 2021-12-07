package com.allwin.first.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.allwin.first.model.Address;
import com.allwin.first.model.Employee;

public interface EmployeeService {

	public List<Employee> searchEmployeeServ(String searchText);
	
	public List<Address> findAllByEmpIdServ(String empId);
	
	public int deleteAllByEmpIdServ(String empId);
	
	public List<Address> searchAddressServ(String searchText);
	
	public Employee findOneByIdServ(String searchText);
	
	public Employee addEmployeeServ(Employee employee);
	
	public Employee updateEmployeeServ(Employee employee);
	
	public int deleteEmployeeServ(String empId);
	
	public Address addAddressServ(Address address);
	
	public CompletableFuture<Employee> addAsyncEmployeeServ(Employee employee);
	
}
