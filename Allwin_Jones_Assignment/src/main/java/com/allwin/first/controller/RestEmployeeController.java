package com.allwin.first.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.allwin.first.exception.AddAddressException;
import com.allwin.first.exception.AddEmployeeException;
import com.allwin.first.exception.DeleteAddressException;
import com.allwin.first.exception.DeleteEmployeeException;
import com.allwin.first.exception.EmployeeIdAlreadyExistException;
import com.allwin.first.exception.EmployeeNotFoundException;
import com.allwin.first.exception.NoMatchesFoundException;
import com.allwin.first.exception.UpdateEmployeeException;
import com.allwin.first.model.Address;
import com.allwin.first.model.AutoComplete;
import com.allwin.first.model.Employee;
import com.allwin.first.model.FullEmployee;
import com.allwin.first.model.Notification;
import com.allwin.first.repo.AutoCompleteRepo;
import com.allwin.first.repo.EmployeeRepo;
import com.allwin.first.service.EmployeeService;

@RestController
public class RestEmployeeController {

	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private AutoCompleteRepo autoComRepo;
	
	@Autowired
	private EmployeeService servRepo;
	
	@GetMapping("restGetEmployees")
	public List<Employee> restGetEmployees() 
	{
		return empRepo.findAll();
	}
	
	@GetMapping("restViewEmployee/{empId}")
	public ResponseEntity<?> restViewEmployee(@PathVariable("empId") String empId)
	{		
		try {
			Employee emp = servRepo.findOneByIdServ(empId);
			
			if(emp == null) {
				throw new EmployeeNotFoundException("Employee Not Found !");
			}
			
			List<Address> addr = servRepo.findAllByEmpIdServ(empId);
			FullEmployee fullEmp = new FullEmployee(emp.getEmpId(), emp.getEmpName(), emp.getEmpGender(), emp.getEmpEmail(), emp.getEmpPhone(), emp.getEmpPosition(), addr);
			return new ResponseEntity<FullEmployee>(fullEmp, HttpStatus.OK);
		} catch(EmployeeNotFoundException e) {
			return new ResponseEntity<Notification>(new Notification("Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}		
	}
	
	@GetMapping("restAddEmployee")
	public ResponseEntity<?> addEmployee(@Valid @RequestBody Employee employee) 
	{
		try {
			if(servRepo.findOneByIdServ(employee.getEmpId()) != null) {
				throw new EmployeeIdAlreadyExistException("Employee ID Already Exist !");
			}
			LocalDateTime localDateTime = LocalDateTime.now();
			employee.setLastUpdated(localDateTime.toString());
			Employee addedEmp = servRepo.addEmployeeServ(employee);
			
			if(addedEmp == null) {
				throw new AddEmployeeException("Employee not Added !");
			}
			
			return new ResponseEntity<Notification>(new Notification("Success", "Employee Added Successfully !"), HttpStatus.OK);
		} catch(EmployeeIdAlreadyExistException e) {
			return new ResponseEntity<Notification>(new Notification("Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		 catch(AddEmployeeException e) {
			 return new ResponseEntity<Notification>(new Notification("Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("restUpdateEmployee")
	public ResponseEntity<?> restUpdateEmployee(@Valid @RequestBody Employee employee) 
	{
		try {
			if(servRepo.findOneByIdServ(employee.getEmpId()) == null) {
				throw new EmployeeNotFoundException("Employee Not Found !");
			}
			
			LocalDateTime localDateTime = LocalDateTime.now();
			employee.setLastUpdated(localDateTime.toString());
			Employee updatedEmp = servRepo.updateEmployeeServ(employee);
			
			if(updatedEmp == null) {
				throw new UpdateEmployeeException("Employee not Updated !");
			}
			
			return new ResponseEntity<Notification>(new Notification("Success", "Employee Updated Successfully !"), HttpStatus.OK);
		} catch(UpdateEmployeeException e) {
			return new ResponseEntity<Notification>(new Notification("Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch(EmployeeNotFoundException e) {
			return new ResponseEntity<Notification>(new Notification("Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("restDeleteEmployee/{empId}")
	public ResponseEntity<?> restDeleteEmployee(@PathVariable("empId") String empId) 
	{
		try {
			int deletedEmp = servRepo.deleteEmployeeServ(empId);
			
			if(deletedEmp != 1) {
				throw new DeleteEmployeeException("Employee not Deleted !");
			}
			
			List<Address> addrCount = servRepo.findAllByEmpIdServ(empId);
			int deletedAddr = servRepo.deleteAllByEmpIdServ(empId);
			
			if(addrCount.size() != deletedAddr) {
				throw new DeleteAddressException("Employee address(es) not Deleted !");
			}
			
			return new ResponseEntity<Notification>(new Notification("Success", "Employee Deleted Successfully !"), HttpStatus.OK);
		} catch(DeleteEmployeeException e) {
			return new ResponseEntity<Notification>(new Notification("Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch(DeleteAddressException e) {
			return new ResponseEntity<Notification>(new Notification("Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("restSearchEmployee/{searchText}")
	public ResponseEntity<?> restSearchEmployee(@PathVariable("searchText") String searchText) 
	{
		try {
			List<Employee> empList = servRepo.searchEmployeeServ(searchText);
			List<Address> addrList = servRepo.searchAddressServ(searchText);
			
			for(int i=0;i<addrList.size();i++) {
				Employee emp = servRepo.findOneByIdServ(addrList.get(i).getEmpId());
				if(empList.contains(emp) == false) { empList.add(emp); }
			}
			
			if(empList.size() == 0) {
				throw new NoMatchesFoundException("No Match Found !");
			}
			
			return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
		} catch(NoMatchesFoundException e) {
			return new ResponseEntity<Notification>(new Notification("Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("restAddAddress")
	public ResponseEntity<?> restAddAddress(@Valid @RequestBody Address address) 
	{
		try {
			LocalDateTime localDateTime = LocalDateTime.now();
			address.setLastUpdated(localDateTime.toString());
			
			AutoComplete autoCompleteId = autoComRepo.findById("AutoCompleteCollection").orElse(null);
			address.setAddressId(autoCompleteId.getAddressId()+1);
			autoComRepo.save(new AutoComplete("AutoCompleteCollection", autoCompleteId.getAddressId()+1));
			
			Address addedAddr = servRepo.addAddressServ(address);
			
			if(addedAddr == null) {
				throw new AddAddressException("Address not Added !");
			}
			
			return new ResponseEntity<Notification>(new Notification("Success", "Address Added Successfully !"), HttpStatus.OK);
		} catch(AddAddressException e) {
			return new ResponseEntity<Notification>(new Notification("Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("restBulkUpload")
	public ResponseEntity<?> bulkUpload(@RequestBody List<Employee> employee) 
	{
		try {
			LocalDateTime localDateTime = LocalDateTime.now();
			List<Notification> notiList = new ArrayList<>();
			
			for(int i=0;i<employee.size();i++) {
				employee.get(i).setLastUpdated(localDateTime.toString());
				CompletableFuture<Employee> users1 = servRepo.addAsyncEmployeeServ(employee.get(i));
				
				if(users1.get() == employee.get(i)) {
					notiList.add(new Notification("Success", "Employee("+employee.get(i).getEmpId()+") Added Successfully !"));
				} else {
					notiList.add(new Notification("Error", "Employee("+employee.get(i).getEmpId()+") Not Added !"));
				}
			}
				
			return new ResponseEntity<List<Notification>>(notiList, HttpStatus.OK);
		} catch(InterruptedException e) {
			return new ResponseEntity<Notification>(new Notification("Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch(ExecutionException e) {
			return new ResponseEntity<Notification>(new Notification("Error", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
}
