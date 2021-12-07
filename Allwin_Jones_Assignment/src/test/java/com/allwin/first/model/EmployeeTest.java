package com.allwin.first.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeTest {

	@Test
	void employeeClassCheck() {
		Employee employee = new Employee();
		assertThat(employee).isNotNull();
	}
	
	@Test
	void employeeDataCheck() {
		Employee employee = new Employee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", "2021-12-02T20:03:53.113463500");
		employee.setEmpId("EMP006");
		employee.setEmpName("Jason");
		employee.setEmpGender("Male");
		employee.setEmpEmail("jason@gmail.com");
		employee.setEmpPhone("9876897856");
		employee.setEmpPosition("Developer");
		employee.setLastUpdated("2021-11-23T20:03:53.113463500");
		
		assertEquals("EMP006", employee.getEmpId());
		assertEquals("Jason", employee.getEmpName());
		assertEquals("Male", employee.getEmpGender());
		assertEquals("jason@gmail.com", employee.getEmpEmail());
		assertEquals("9876897856", employee.getEmpPhone());
		assertEquals("Developer", employee.getEmpPosition());
		assertEquals("2021-11-23T20:03:53.113463500", employee.getLastUpdated());
	}
	
}
