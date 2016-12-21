package flow.web.rest;

import java.util.Collections;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.BasicSessionCredentials;

import flow.dto.User;
import flow.exception.NoPermissionException;
import flow.security.jwt.JWTConfigurer;
import flow.security.jwt.TokenProvider;
import flow.web.rest.dto.S3TokenResponse;

@RestController
@RequestMapping("/api/guests")
public class GuestController extends BaseController {
	
	@Inject
	private UserDetailsService userDetailsService;

	@Inject
    private TokenProvider tokenProvider;
	
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
	@RequestMapping(value="/activate", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> createDefaultGuestUser(HttpServletRequest req, HttpServletResponse response){
		req.getHeaderNames();
		User guest = userService.createDefaultGuestUser();
		return authenticateGuestAndInitializeSessionByUsername(guest.getLogin(), userDetailsService, req, response);
		//return new RestResponse<User>(FlowResponseCode.OK, guest);
	}
	
	private ResponseEntity<?> authenticateGuestAndInitializeSessionByUsername(
			String username, UserDetailsService userDetailsService,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// generate session if one doesn't exist
			request.getSession();
			// Authenticate the user
			UserDetails user = userDetailsService.loadUserByUsername(username);
			Authentication auth = new UsernamePasswordAuthenticationToken(user,
					null, user.getAuthorities());
			String jwt = tokenProvider.createToken(auth, false);
			SecurityContextHolder.getContext().setAuthentication(auth);
            response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return ResponseEntity.ok(new JWTToken(jwt));
		} catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("AuthenticationException", e.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
		}
	}
}
