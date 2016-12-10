package flow.service;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Resource;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.Statement.Effect;
import com.amazonaws.auth.policy.actions.S3Actions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetFederationTokenRequest;
import com.amazonaws.services.securitytoken.model.GetFederationTokenResult;

import flow.service.util.RestPreconditions;
import flow.service.util.S3Utils;

@Service
@Transactional
public class S3TokenService {

	@Autowired
	private AmazonS3 s3client;
	
	@Autowired
	private AWSCredentials credentials;
	

	public BasicSessionCredentials getS3UserCredentials() {
		AWSSecurityTokenServiceClient stsClient = new AWSSecurityTokenServiceClient(new BasicAWSCredentials(credentials.getAWSAccessKeyId(), credentials.getAWSSecretKey()));

		GetFederationTokenRequest getFederationTokenRequest = new GetFederationTokenRequest();
		getFederationTokenRequest.setDurationSeconds(7200);
		getFederationTokenRequest.setName("User");

		// Define the policy and add to the request.
		Policy policy = new Policy();
		policy.withStatements(new Statement(Effect.Allow).withActions(
				S3Actions.PutObject)
				.withResources(new Resource("arn:aws:s3:::".concat(S3Utils.S3_BUCKET))));

		getFederationTokenRequest.setPolicy(policy.toJson());

		// Get the temporary security credentials.
		GetFederationTokenResult federationTokenResult = stsClient
				.getFederationToken(getFederationTokenRequest);
		Credentials sessionCredentials = federationTokenResult.getCredentials();

		// Package the session credentials as a BasicSessionCredentials
		// object for an S3 client object to use.
		BasicSessionCredentials basicSessionCredentials = new BasicSessionCredentials(
				sessionCredentials.getAccessKeyId(),
				sessionCredentials.getSecretAccessKey(),
				sessionCredentials.getSessionToken());
		
		return basicSessionCredentials;
	}
}

