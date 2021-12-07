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
public class AutoCompleteTest {

	@Test
	void autoCompleteClassCheck() {
		AutoComplete autoComplete = new AutoComplete();
		assertThat(autoComplete).isNotNull();
	}
	
	@Test
	void autoCompleteDataCheck() {
		AutoComplete autoComplete = new AutoComplete("AutoCompleteCollection", 5);
		autoComplete.setUniqId("AutoCompleteCollection");
		autoComplete.setAddressId(6);
		
		assertEquals("AutoCompleteCollection", autoComplete.getUniqId());
		assertEquals(6, autoComplete.getAddressId());
	}
	
}
