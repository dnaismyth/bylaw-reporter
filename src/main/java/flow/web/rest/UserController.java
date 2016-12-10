package flow.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.BasicSessionCredentials;

import flow.web.rest.dto.S3TokenResponse;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

	/**
	 * Allow user access to S3 Bucket
	 * @return 
	 */
	@RequestMapping(value = "/s3token", method = RequestMethod.GET)
	public S3TokenResponse getS3AccessToken(){
		BasicSessionCredentials credentials = s3TokenService.getS3UserCredentials();
		return new S3TokenResponse(credentials);
	}
}
