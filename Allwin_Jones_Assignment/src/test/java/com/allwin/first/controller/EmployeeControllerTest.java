package com.allwin.first.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.allwin.first.model.Address;
import com.allwin.first.model.AutoComplete;
import com.allwin.first.model.Employee;
import com.allwin.first.model.Notification;
import com.allwin.first.repo.AddressRepo;
import com.allwin.first.repo.AutoCompleteRepo;
import com.allwin.first.repo.EmployeeRepo;
import com.allwin.first.serviceImpl.EmployeeServiceImpl;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;
	
	@MockBean
	EmployeeRepo empRepo;
	
	@MockBean
	AddressRepo addRepo;
	
	@MockBean
	AutoCompleteRepo autoComRepo;
	
	@MockBean
	EmployeeServiceImpl employeeServiceImpl;
	
	@Mock
    Model m;
	
	@Mock
	BindingResult bindingResult;
	
	@Test
	void addressClassCheck() {
		assertThat(employeeController).isNotNull();
	}
	
	@Test
	void indexTest() {
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500"));
		listEmployee.add(new Employee("EMP006", "Jason", "Male", "jason@gmail.com", "9876897856", "Developer", "2021-11-23T20:03:53.113463500"));
		
		when(empRepo.findAll()).thenReturn(listEmployee);
		
		
		assertEquals("index", employeeController.index(m));
		verify(m).addAttribute("employeeList", listEmployee);
	}
	
	@Test
	void index2Test() {
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500"));
		listEmployee.add(new Employee("EMP006", "Jason", "Male", "jason@gmail.com", "9876897856", "Developer", "2021-11-23T20:03:53.113463500"));
		Notification notification = new Notification("Success", "Employee Added Successfully !");
		
		when(empRepo.findAll()).thenReturn(listEmployee);
		
		
		assertEquals("index", employeeController.index("Success", "Employee Added Successfully !", m));
		verify(m).addAttribute("employeeList", listEmployee);
		verify(m).addAttribute("notification", notification);
	}
	
	@Test
	void addEmployeeTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(null);
		when(employeeServiceImpl.addEmployeeServ(employee)).thenReturn(employee);
		
		assertEquals("redirect:/index/Success/Employee Added Successfully !", employeeController.addEmployee(employee, bindingResult, m));
	}
	
	@Test
	void addEmployeeException1Test() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		Notification notification = new Notification("Error", "Employee ID Already Exist !");
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(employee);		
		
		assertEquals("addEmployee", employeeController.addEmployee(employee, bindingResult, m));
		verify(m).addAttribute("employee", employee);
		verify(m).addAttribute("notification", notification);
	}
	
	@Test
	void addEmployeeException2Test() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(null);
		when(employeeServiceImpl.addEmployeeServ(employee)).thenReturn(null);
		
		assertEquals("redirect:/index/Error/Employee not Added !", employeeController.addEmployee(employee, bindingResult, m));
	}
	
	@Test
	void editEmployeeTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(employee);
		
		assertEquals("updateEmployee", employeeController.editEmployee("EMP001", m));
		verify(m).addAttribute("employee", employee);
	}
	
	@Test
	void editEmployeeExceptionTest() {
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(null);
		
		assertEquals("redirect:/index/Error/Employee Not Found !", employeeController.editEmployee("EMP001", m));
	}
	
	@Test
	void updateEmployeeTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		
		when(employeeServiceImpl.updateEmployeeServ(employee)).thenReturn(employee);
		
		assertEquals("redirect:/index/Success/Employee Updated Successfully !", employeeController.updateEmployee(employee, bindingResult, m));
	}
	
	@Test
	void updateEmployeeExceptionTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		
		when(employeeServiceImpl.updateEmployeeServ(employee)).thenReturn(null);
		
		assertEquals("redirect:/index/Error/Employee not Updated !", employeeController.updateEmployee(employee, bindingResult, m));
	}
	
	@Test
	void deleteEmployeeTest() {
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		listAddress.add(new Address(6, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500"));
		
		when(employeeServiceImpl.deleteEmployeeServ("EMP001")).thenReturn(1);
		when(employeeServiceImpl.findAllByEmpIdServ("EMP001")).thenReturn(listAddress);
		when(employeeServiceImpl.deleteAllByEmpIdServ("EMP001")).thenReturn(2);

		assertEquals("redirect:/index/Success/Employee Deleted Successfully !", employeeController.deleteEmployee("EMP001"));
	}
	
	@Test
	void deleteEmployeeException1Test() {
		when(employeeServiceImpl.deleteEmployeeServ("EMP001")).thenReturn(0);
		
		assertEquals("redirect:/index/Error/Employee not Deleted !", employeeController.deleteEmployee("EMP001"));
		verify(employeeServiceImpl).deleteEmployeeServ("EMP001");
	}
	
	@Test
	void deleteEmployeeException2Test() {
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		listAddress.add(new Address(6, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500"));
		
		when(employeeServiceImpl.deleteEmployeeServ("EMP001")).thenReturn(1);
		when(employeeServiceImpl.findAllByEmpIdServ("EMP001")).thenReturn(listAddress);
		when(employeeServiceImpl.deleteAllByEmpIdServ("EMP001")).thenReturn(1);
		
		assertEquals("redirect:/index/Error/Employee address(es) not Deleted !", employeeController.deleteEmployee("EMP001"));
	}
	
	@Test
	void searchEmployeeTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500"); 
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(new Employee("EMP006", "Jason", "Male", "jason@gmail.com", "9876897856", "Developer", "2021-11-23T20:03:53.113463500"));
		List<Employee> listEmployee2 = new ArrayList<>();
		listEmployee2.add(new Employee("EMP006", "Jason", "Male", "jason@gmail.com", "9876897856", "Developer", "2021-11-23T20:03:53.113463500"));
		listEmployee2.add(new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500"));
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		
		when(employeeServiceImpl.searchEmployeeServ(".*98.*")).thenReturn(listEmployee);
		when(employeeServiceImpl.searchAddressServ(".*98.*")).thenReturn(listAddress);
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(employee);

		assertEquals("index", employeeController.searchEmployee("98", m));
		verify(m).addAttribute("employeeList", listEmployee2);
		verify(m).addAttribute("searchText", "98");
	}
	
	@Test
	void searchEmployeeExceptionTest() {
		Notification notification = new Notification("Error", "No Match Found !");
		List<Employee> listEmployee = new ArrayList<>();
		List<Address> listAddress = new ArrayList<>();
		
		when(employeeServiceImpl.searchEmployeeServ("98")).thenReturn(listEmployee);
		when(employeeServiceImpl.searchAddressServ("98")).thenReturn(listAddress);

		assertEquals("index", employeeController.searchEmployee("98", m));
		verify(m).addAttribute("notification", notification);
		verify(m).addAttribute("searchText", "98");
	}
	
	@Test
	void addAddressTest() {
		Address address = new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500");
		Optional<AutoComplete> autoComplete = Optional.of(new AutoComplete("AutoCompleteCollection", 4)); 
		
		when(employeeServiceImpl.addAddressServ(address)).thenReturn(address);
		when(autoComRepo.findById("AutoCompleteCollection")).thenReturn(autoComplete);
		
		assertEquals("redirect:/index/Success/Address Added Successfully !", employeeController.addAddress(address, bindingResult, m)); 
	}
	
	@Test
	void addAddressExceptionTest() {
		Address address = new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500");
		Optional<AutoComplete> autoComplete = Optional.of(new AutoComplete("AutoCompleteCollection", 4));
		
		when(employeeServiceImpl.addAddressServ(address)).thenReturn(null);
		when(autoComRepo.findById("AutoCompleteCollection")).thenReturn(autoComplete);
		
		assertEquals("redirect:/index/Error/Address not Added !", employeeController.addAddress(address, bindingResult, m)); 
	}
	
	@Test
	void viewEmployeeTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		listAddress.add(new Address(6, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500"));
		
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(employee);
		when(employeeServiceImpl.findAllByEmpIdServ("EMP001")).thenReturn(listAddress);
		
		assertEquals("viewEmployee", employeeController.viewEmployee("EMP001", m)); 
		verify(m).addAttribute("employeeData", employee);
		verify(m).addAttribute("addressData", listAddress);
	}
	
	@Test
	void viewEmployeeExceptionTest() {
		when(employeeServiceImpl.findOneByIdServ("EMP001")).thenReturn(null);
		
		assertEquals("redirect:/index/Error/Employee Not Found !", employeeController.viewEmployee("EMP001", m)); 
	}
	
}
