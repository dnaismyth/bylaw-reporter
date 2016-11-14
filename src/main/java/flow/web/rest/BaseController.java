package flow.web.rest;

import org.springframework.beans.factory.annotation.Autowired;

import flow.dto.User;
import flow.service.ReportService;
import flow.service.UserService;

public class BaseController {
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ReportService reportService;
	
	/**
	 * Return the current logged in user
	 * @return
	 */
	protected User getCurrentUser(){
		User current = userService.getUserDTOWithAuthorities();
		return current;
	}
}
