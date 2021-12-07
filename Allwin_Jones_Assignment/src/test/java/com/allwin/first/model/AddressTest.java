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
public class AddressTest {
	
	@Test
	void addressClassCheck() {
		Address address = new Address();
		assertThat(address).isNotNull();
	}
	
	@Test
	void addressDataCheck() {
		Address address = new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500");
		address.setAddressId(6);
		address.setEmpId("EMP006");
		address.setStreet("Blackham Street");
		address.setCity("New Orleans");
		address.setDistrict("California");
		address.setState("USA");
		address.setPincode("190876");
		address.setLastUpdated("2021-11-23T20:03:53.113463500");
		
		assertEquals(6, address.getAddressId());
		assertEquals("EMP006", address.getEmpId());
		assertEquals("Blackham Street", address.getStreet());
		assertEquals("New Orleans", address.getCity());
		assertEquals("California", address.getDistrict());
		assertEquals("USA", address.getState());
		assertEquals("190876", address.getPincode());
		assertEquals("2021-11-23T20:03:53.113463500", address.getLastUpdated());
	}
	
}
