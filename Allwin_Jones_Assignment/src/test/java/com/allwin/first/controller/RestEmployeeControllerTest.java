package com.allwin.first.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allwin.first.model.Address;
import com.allwin.first.model.AutoComplete;
import com.allwin.first.model.Employee;
import com.allwin.first.model.FullEmployee;
import com.allwin.first.model.Notification;
import com.allwin.first.repo.AddressRepo;
import com.allwin.first.repo.AutoCompleteRepo;
import com.allwin.first.repo.EmployeeRepo;
import com.allwin.first.serviceImpl.EmployeeServiceImpl;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class RestEmployeeControllerTest {

	@InjectMocks
	RestEmployeeController restEmployeeController;
	
	@MockBean
	EmployeeRepo empRepo;
	
	@MockBean
	AddressRepo addRepo;
	
	@MockBean
	AutoCompleteRepo autoComRepo;
	
	@MockBean
	EmployeeServiceImpl employeeServiceImpl;
	
	@Test
	void addressClassCheck() {
		assertThat(restEmployeeController).isNotNull();
	}
	
	@Test
	void restGetEmployeesTest() {
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500"));
		listEmployee.add(new Employee("EMP006", "Jason", "Male", "jason@gmail.com", "9876897856", "Developer", "2021-11-23T20:03:53.113463500"));
		
		when(empRepo.findAll()).thenReturn(listEmployee);
		
		assertEquals(listEmployee, restEmployeeController.restGetEmployees());  
	}
	
	@Test
	void restViewEmployeeTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		listAddress.add(new Address(6, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500"));
		FullEmployee fullEmployee = new FullEmployee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", listAddress);
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(employee);
		when(employeeServiceImpl.findAllByEmpIdServ("EMP001")).thenReturn(listAddress);
		
		assertEquals(fullEmployee, restEmployeeController.restViewEmployee("EMP001").getBody()); 
	}
	
	@Test
	void restViewEmployeeExceptionTest() {	
		Notification notification = new Notification("Error", "Employee Not Found !");
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(null);
		
		assertEquals(notification, restEmployeeController.restViewEmployee("EMP001").getBody()); 
	}
	
	@Test
	void addEmployeeTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		Notification notification = new Notification("Success", "Employee Added Successfully !");
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(null);
		when(employeeServiceImpl.addEmployeeServ(employee)).thenReturn(employee);
		
		assertEquals(notification, restEmployeeController.addEmployee(employee).getBody()); 
	}
	
	@Test
	void addEmployeeException1Test() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		Notification notification = new Notification("Error", "Employee ID Already Exist !");
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(employee);
		
		assertEquals(notification, restEmployeeController.addEmployee(employee).getBody()); 
	}
	
	@Test
	void addEmployeeException2Test() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		Notification notification = new Notification("Error", "Employee not Added !");
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(null);
		when(employeeServiceImpl.addEmployeeServ(employee)).thenReturn(null);
		
		assertEquals(notification, restEmployeeController.addEmployee(employee).getBody()); 
	}
	
	@Test
	void restUpdateEmployeeTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		Notification notification = new Notification("Success", "Employee Updated Successfully !");
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(employee);
		when(employeeServiceImpl.updateEmployeeServ(employee)).thenReturn(employee);
		
		assertEquals(notification, restEmployeeController.restUpdateEmployee(employee).getBody()); 
	}
	
	@Test
	void restUpdateEmployeeException1Test() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		Notification notification = new Notification("Error", "Employee Not Found !");
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(null);
		
		assertEquals(notification, restEmployeeController.restUpdateEmployee(employee).getBody()); 
	}
	
	@Test
	void restUpdateEmployeeException2Test() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		Notification notification = new Notification("Error", "Employee not Updated !");
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(employee);
		when(employeeServiceImpl.updateEmployeeServ(employee)).thenReturn(null);
		
		assertEquals(notification, restEmployeeController.restUpdateEmployee(employee).getBody()); 
	}
	
	@Test
	void restDeleteEmployeeTest() {
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		listAddress.add(new Address(6, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500"));
		Notification notification = new Notification("Success", "Employee Deleted Successfully !");
		
		when(employeeServiceImpl.deleteEmployeeServ("EMP001")).thenReturn(1);
		when(employeeServiceImpl.findAllByEmpIdServ("EMP001")).thenReturn(listAddress);
		when(employeeServiceImpl.deleteAllByEmpIdServ("EMP001")).thenReturn(2);
		
		assertEquals(notification, restEmployeeController.restDeleteEmployee("EMP001").getBody()); 
	}
	
	@Test
	void restDeleteEmployeeException1Test() {
		Notification notification = new Notification("Error", "Employee not Deleted !");
		
		when(employeeServiceImpl.deleteEmployeeServ("EMP001")).thenReturn(0);
		
		assertEquals(notification, restEmployeeController.restDeleteEmployee("EMP001").getBody()); 
	}
	
	@Test
	void restDeleteEmployeeException2Test() {
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		listAddress.add(new Address(6, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500"));
		Notification notification = new Notification("Error", "Employee address(es) not Deleted !");
		
		when(employeeServiceImpl.deleteEmployeeServ("EMP001")).thenReturn(1);
		when(employeeServiceImpl.findAllByEmpIdServ("EMP001")).thenReturn(listAddress);
		when(employeeServiceImpl.deleteAllByEmpIdServ("EMP001")).thenReturn(1);
		
		assertEquals(notification, restEmployeeController.restDeleteEmployee("EMP001").getBody()); 
	}
	
	@Test
	void restSearchEmployeeTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500"); 
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(new Employee("EMP006", "Jason", "Male", "jason@gmail.com", "9876897856", "Developer", "2021-11-23T20:03:53.113463500"));
		List<Employee> listEmployee2 = new ArrayList<>();
		listEmployee2.add(new Employee("EMP006", "Jason", "Male", "jason@gmail.com", "9876897856", "Developer", "2021-11-23T20:03:53.113463500"));
		listEmployee2.add(new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500"));
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		
		when(employeeServiceImpl.searchEmployeeServ("98")).thenReturn(listEmployee);
		when(employeeServiceImpl.searchAddressServ("98")).thenReturn(listAddress);
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(employee);
		
		assertEquals(listEmployee2, restEmployeeController.restSearchEmployee("98").getBody()); 
	}
	
	@Test
	void restSearchEmployeeExceptionTest() {		
		Notification notification = new Notification("Error", "No Match Found !");
		List<Employee> listEmployee = new ArrayList<>();
		List<Address> listAddress = new ArrayList<>();
		
		when(employeeServiceImpl.searchEmployeeServ("98")).thenReturn(listEmployee);
		when(employeeServiceImpl.searchAddressServ("98")).thenReturn(listAddress);
		
		assertEquals(notification, restEmployeeController.restSearchEmployee("98").getBody()); 
	}
	
	@Test
	void restAddAddressTest() {
		Address address = new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500");
		Notification notification = new Notification("Success", "Address Added Successfully !");
		Optional<AutoComplete> autoComplete = Optional.of(new AutoComplete("AutoCompleteCollection", 4));
		
		when(employeeServiceImpl.addAddressServ(address)).thenReturn(address);
		when(autoComRepo.findById("AutoCompleteCollection")).thenReturn(autoComplete);
		
		assertEquals(notification, restEmployeeController.restAddAddress(address).getBody()); 
	}
	
	@Test
	void restAddAddressExceptionTest() {
		Address address = new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500");
		Notification notification = new Notification("Error", "Address not Added !");
		Optional<AutoComplete> autoComplete = Optional.of(new AutoComplete("AutoCompleteCollection", 4));
		
		when(employeeServiceImpl.addAddressServ(address)).thenReturn(null);
		when(autoComRepo.findById("AutoCompleteCollection")).thenReturn(autoComplete);
		
		assertEquals(notification, restEmployeeController.restAddAddress(address).getBody()); 
	}
	
	@Test
	void bulkUploadTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		Employee employee2 = new Employee("EMP006", "Jason", "Male", "jason@gmail.com", "9876897856", "Developer", "2021-11-23T20:03:53.113463500");
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(employee);
		listEmployee.add(employee2);
		List<Notification> notiList = new ArrayList<>();
		notiList.add(new Notification("Success", "Employee(EMP001) Added Successfully !"));
		notiList.add(new Notification("Success", "Employee(EMP006) Added Successfully !"));
		
		when(employeeServiceImpl.addAsyncEmployeeServ(employee)).thenReturn(CompletableFuture.completedFuture(employee));
		when(employeeServiceImpl.addAsyncEmployeeServ(employee2)).thenReturn(CompletableFuture.completedFuture(employee2));
		
		assertEquals(notiList, restEmployeeController.bulkUpload(listEmployee).getBody()); 
	}
	
	@Test
	void bulkUploadFailTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		Employee employee2 = new Employee("EMP006", "Jason", "Male", "jason@gmail.com", "9876897856", "Developer", "2021-11-23T20:03:53.113463500");
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(employee);
		listEmployee.add(employee2);
		List<Notification> notiList = new ArrayList<>();
		notiList.add(new Notification("Success", "Employee(EMP001) Added Successfully !"));
		notiList.add(new Notification("Error", "Employee(EMP006) Not Added !"));
		
		when(employeeServiceImpl.addAsyncEmployeeServ(employee)).thenReturn(CompletableFuture.completedFuture(employee));
		when(employeeServiceImpl.addAsyncEmployeeServ(employee2)).thenReturn(CompletableFuture.completedFuture(null));
		
		assertEquals(notiList, restEmployeeController.bulkUpload(listEmployee).getBody()); 
	}
	
}
