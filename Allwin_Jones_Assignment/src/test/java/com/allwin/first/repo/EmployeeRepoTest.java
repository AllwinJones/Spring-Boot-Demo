package com.allwin.first.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allwin.first.model.Employee;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeRepoTest {

	@MockBean
	EmployeeRepo empRepo;
	
	@Test
	void testEmployeeRepo() {
		
		List<Employee> listEmployee = new ArrayList<>();
		listEmployee.add(new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500"));
		listEmployee.add(new Employee("EMP006", "Jason", "Male", "jason@gmail.com", "9876897856", "Developer", "2021-11-23T20:03:53.113463500"));
		
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		
		when(empRepo.searchEmployee("768")).thenReturn(listEmployee);
		when(empRepo.findOneById("EMP001")).thenReturn(employee);
		when(empRepo.save(employee)).thenReturn(employee);
		when(empRepo.findAll()).thenReturn(listEmployee);
		when(empRepo.existsById("EMP001")).thenReturn(true);
		empRepo.deleteById("EMP001");
		
		assertEquals(listEmployee, empRepo.searchEmployee("768"));
		assertEquals(employee, empRepo.findOneById("EMP001"));
		assertEquals(employee, empRepo.save(employee));
		assertEquals(listEmployee, empRepo.findAll());
		assertEquals(true, empRepo.existsById("EMP001"));
		verify(empRepo).deleteById("EMP001");
		
	}
	
}
