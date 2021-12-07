package com.allwin.first.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class FullEmployeeTest {

	@Test
	void fullEmployeeClassCheck() {
		FullEmployee fullEmployee = new FullEmployee();
		assertThat(fullEmployee).isNotNull();
	}
	
	@Test
	void fullEmployeeDataCheck() {
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		List<Address> listAddress2 = new ArrayList<>();
		listAddress.add(new Address(6, "EMP006", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500"));
		FullEmployee fullEmployee = new FullEmployee("EMP001", "Glenn", "Male", "glenn@gmail.com", "8976875434", "Developer", listAddress);
		
		fullEmployee.setEmpId("EMP006");
		fullEmployee.setEmpName("Jason");
		fullEmployee.setEmpGender("Male");
		fullEmployee.setEmpEmail("jason@gmail.com");
		fullEmployee.setEmpPhone("9876897856");
		fullEmployee.setEmpPosition("Developer");
		fullEmployee.setAddress(listAddress2);
		
		assertEquals("EMP006", fullEmployee.getEmpId());
		assertEquals("Jason", fullEmployee.getEmpName());
		assertEquals("Male", fullEmployee.getEmpGender());
		assertEquals("jason@gmail.com", fullEmployee.getEmpEmail());
		assertEquals("9876897856", fullEmployee.getEmpPhone());
		assertEquals("Developer", fullEmployee.getEmpPosition());
		assertEquals(listAddress2, fullEmployee.getAddress());
	}
	
}
