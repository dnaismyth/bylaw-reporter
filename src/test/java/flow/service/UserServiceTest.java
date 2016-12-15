package flow.service;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import flow.dto.User;

public class UserServiceTest extends BaseTestClass {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testCreateDefaultGuestAccount(){
		User guest = userService.createDefaultGuestUser();
		Assert.assertNotNull(guest);
	}

}
