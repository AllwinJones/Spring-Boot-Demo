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
public class NotificationTest {

	@Test
	void notificationClassCheck() {
		Notification notification = new Notification();
		assertThat(notification).isNotNull();
	}
	
	@Test
	void notificationDataCheck() {
		Notification notification = new Notification("Error", "Employee Not Added !");
		notification.setType("Success");
		notification.setMessage("Employee Added Successfully !");
		
		assertEquals("Success", notification.getType());
		assertEquals("Employee Added Successfully !", notification.getMessage());
	}
	
}
