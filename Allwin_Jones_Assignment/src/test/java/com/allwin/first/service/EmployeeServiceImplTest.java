package com.allwin.first.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allwin.first.model.Address;
import com.allwin.first.model.Employee;
import com.allwin.first.repo.AddressRepo;
import com.allwin.first.repo.EmployeeRepo;
import com.allwin.first.serviceImpl.EmployeeServiceImpl;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	EmployeeServiceImpl employeeServiceImpl;
	
	@MockBean
	EmployeeRepo empRepo;
	
	@MockBean
	AddressRepo addRepo;
	
	@Test
	void serviceClassCheck() {
		assertThat(employeeServiceImpl).isNotNull();
	}
	
	@Test
	void searchEmployeeServTest() {
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500"));
		listEmployee.add(new Employee("EMP006", "Jason", "Male", "jason@gmail.com", "9876897856", "Developer", "2021-11-23T20:03:53.113463500"));
		
		
		when(empRepo.searchEmployee("768")).thenReturn(listEmployee);
		
		assertEquals(listEmployee, employeeServiceImpl.searchEmployeeServ("768"));  
	}
	
	@Test
	void findAllByEmpIdServTest() {
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		listAddress.add(new Address(6, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500"));
		
		
		when(addRepo.findAllByEmpId("EMP001")).thenReturn(listAddress);
		
		assertEquals(listAddress, employeeServiceImpl.findAllByEmpIdServ("EMP001"));  
	}
	
	@Test
	void deleteAllByEmpIdServTest() {
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		listAddress.add(new Address(6, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500"));
		
		
		when(addRepo.deleteAllByEmpId("EMP001")).thenReturn(2);
		
		assertEquals(2, employeeServiceImpl.deleteAllByEmpIdServ("EMP001"));  
	}
	
	@Test
	void searchAddressServTest() {
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		listAddress.add(new Address(6, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500"));
		
		
		when(addRepo.searchAddress("876")).thenReturn(listAddress);
		
		assertEquals(listAddress, employeeServiceImpl.searchAddressServ("876"));  
	}
	
	@Test
	void findOneByIdServTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		
		when(empRepo.findOneById("EMP001")).thenReturn(employee);
		
		assertEquals(employee, employeeServiceImpl.findOneByIdServ("EMP001"));  
	}
	
	@Test
	void addEmployeeServTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		
		when(empRepo.save(employee)).thenReturn(employee);
		
		assertEquals(employee, employeeServiceImpl.addEmployeeServ(employee));  
	}
	
	@Test
	void updateEmployeeServTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		
		when(empRepo.save(employee)).thenReturn(employee);
		
		assertEquals(employee, employeeServiceImpl.updateEmployeeServ(employee));  
	}
	
	@Test
	void deleteEmployeeServTest() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		
		employeeServiceImpl.deleteEmployeeServ("EMP001"); 
		
		verify(empRepo).deleteOneById(employee.getEmpId());
	}
	
	@Test
	void addAddressServTest() {
		Address address = new Address(7, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500");
		
		when(addRepo.save(address)).thenReturn(address);
		
		assertEquals(address, employeeServiceImpl.addAddressServ(address));  
	}
	
}
