package flow.web.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import flow.dto.RoleType;
import flow.dto.User;
import flow.exception.NoPermissionException;
import flow.service.MailService;
import flow.service.ReportService;
import flow.service.S3TokenService;
import flow.service.UserService;

public class BaseController {
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ReportService reportService;
	
	@Autowired
	protected S3TokenService s3TokenService;
	
	@Autowired 
	protected MailService mailService;
	
	protected static final String PARAM_PAGE = "page";
	protected static final String PARAM_SIZE = "size";
	
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
	
	protected void checkAdminAuthority(User user) throws NoPermissionException{
		if(user.getRole() != RoleType.ADMIN){
			throw new NoPermissionException("Only an admin can view this content");
		}
	}
	
	protected void checkGuestAuthority(User user) throws NoPermissionException{
		if(user.getRole() != RoleType.ADMIN && user.getRole() != RoleType.GUEST){
			throw new NoPermissionException("You must be a guest user to access resources.");
		}
	}
	
	/**
	 * Check if a user is valid by their id and role type
	 * @param userId
	 * @return
	 */
	protected boolean userIsValid(Long userId){
		User user = userService.findUserById(userId);
		if(user.getRole().equals(RoleType.ADMIN) || user.getRole().equals(RoleType.GUEST)){
			return true;
		}
		return false;
	}
}
