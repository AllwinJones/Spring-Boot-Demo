package com.allwin.first.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.allwin.first.model.Address;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class AddressRepoTest {

	
	@MockBean
	AddressRepo addRepo;
	
	@Test
	void testAddressRepo() {
		List<Address> listAddress = new ArrayList<>();
		listAddress.add(new Address(5, "EMP001", "Gunthur Block", "New York City", "New York", "USA", "109876", "2021-12-02T20:03:53.113463500"));
		listAddress.add(new Address(6, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500"));
		
		Address address = new Address(7, "EMP001", "Blackham Street", "New Orleans", "California", "USA", "190876", "2021-11-23T20:03:53.113463500");
		
		when(addRepo.findAllByEmpId("EMP001")).thenReturn(listAddress);
		when(addRepo.deleteAllByEmpId("EMP001")).thenReturn(2);
		when(addRepo.searchAddress("876")).thenReturn(listAddress);
		when(addRepo.save(address)).thenReturn(address);
		
		assertEquals(listAddress, addRepo.findAllByEmpId("EMP001"));
		assertEquals(2, addRepo.deleteAllByEmpId("EMP001"));
		assertEquals(listAddress, addRepo.searchAddress("876"));
		assertEquals(address, addRepo.save(address));
	}
	
}
