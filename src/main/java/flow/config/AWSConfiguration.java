package flow.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

@Configuration
@PropertySource("classpath:/S3Credentials.properties")
public class AWSConfiguration {
	protected static final Logger logger = Logger.getLogger(AWSConfiguration.class); 

	   @Autowired
	   private static Environment env;
	   
	   @Autowired
	   private static PropertyPlaceholderConfigurer ppc;
	   
	   @Value("${AWSAccessKeyId}")
	   private String awsId;
		
	   @Value("${AWSSecretKey}")
	   private String awsKey;
		
	   @Bean
	   public AmazonS3 s3client() {
		   AmazonS3 s3Client = new AmazonS3Client(credential());
		   Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		   s3Client.setRegion(usWest2);
		   return s3Client;
	   }
	   
	   @Bean
	   public AWSCredentials credential() {
	   		return new BasicAWSCredentials(awsId, awsKey);
	   }
	   

}