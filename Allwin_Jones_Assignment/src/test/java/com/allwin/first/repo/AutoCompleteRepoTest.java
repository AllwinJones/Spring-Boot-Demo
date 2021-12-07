package com.allwin.first.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allwin.first.model.AutoComplete;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class AutoCompleteRepoTest {

	@MockBean
	AutoCompleteRepo autoComRepo;
	
	@Test
	void testAutoCompleteRepo() {
		AutoComplete autoComplete = new AutoComplete("AutoCompleteCollection", 5);
		Optional<AutoComplete> autoComplete2 = Optional.of(new AutoComplete("AutoCompleteCollection", 5));
		
		when(autoComRepo.findById("AutoCompleteCollection")).thenReturn(autoComplete2);
		when(autoComRepo.save(autoComplete)).thenReturn(autoComplete);
		
		assertEquals(autoComplete2, autoComRepo.findById("AutoCompleteCollection"));
		assertEquals(autoComplete, autoComRepo.save(autoComplete));
	}
	
}
