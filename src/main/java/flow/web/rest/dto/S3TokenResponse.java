package flow.web.rest.dto;

import com.amazonaws.auth.BasicSessionCredentials;

public class S3TokenResponse {

	private String secretKey;
	private String accessKey;
	
	public S3TokenResponse(BasicSessionCredentials credentials){
		this.secretKey = credentials.getAWSSecretKey();
		this.accessKey = credentials.getAWSAccessKeyId();
	}
	
	public String getSecretKey(){
		return secretKey;
	}
	
	public String getAccessKey(){
		return accessKey;
	}
}
