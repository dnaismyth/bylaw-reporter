package flow.web.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import flow.dto.User;
import flow.service.MailService;
import flow.service.ReportService;
import flow.service.UserService;

public class BaseController {
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ReportService reportService;
	
	@Autowired 
	protected MailService mailService;
	
	/**
	 * Return the current logged in user
	 * @return
	 */
	protected User getCurrentUser(){
		User current = userService.getUserDTOWithAuthorities();
		return current;
	}
	
	protected String getBaseUrl(HttpServletRequest request){
		   String baseUrl = request.getScheme() + // "http"
                "://" +                                // "://"
                request.getServerName() +              // "myhost"
                ":" +                                  // ":"
                request.getServerPort() +              // "80"
                request.getContextPath();              // "/myContextPath" or "" if deployed in root context
		   return baseUrl;
	}
}
