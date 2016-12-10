package flow.service.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/S3Resources.properties")
public class S3Utils {

    public static String S3_BUCKET;
    
    public static String S3_HOST_NAME;

    @Value("${BUCKET_NAME}")
    public void setBucket(String bucket) {
        S3_BUCKET = bucket;
    }
    
    @Value("${HOST_NAME}")
    public void setHostName(String hostName){
    	S3_HOST_NAME = hostName;
    }
	
}
