package flow.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.BasicSessionCredentials;

import flow.dto.RoleType;
import flow.dto.User;
import flow.exception.NoPermissionException;
import flow.web.rest.dto.FlowResponseCode;
import flow.web.rest.dto.RestResponse;
import flow.web.rest.dto.S3TokenResponse;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

	/**
	 * Allow user access to S3 Bucket
	 * @return 
	 * @throws NoPermissionException 
	 */
	@RequestMapping(value = "/s3token", method = RequestMethod.GET)
	@ResponseBody
	public S3TokenResponse getS3AccessToken() throws NoPermissionException{
		User user = getCurrentUser();
		checkGuestAuthority(user);
		BasicSessionCredentials credentials = s3TokenService.getS3UserCredentials();
		return new S3TokenResponse(credentials);
	}
	
	/**
	 * Create a default guest user for bylaw reporters
	 * @return
	 */
	@RequestMapping(value = "/activate_guest", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse<User> createDefaultGuestUser(){
		User guest = userService.createDefaultGuestUser();
		return new RestResponse<User>(FlowResponseCode.OK, guest);
	}
}
