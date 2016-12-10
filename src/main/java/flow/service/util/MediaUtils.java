package flow.service.util;

public class MediaUtils {

	public static String generateUrlFromKey(String key){
		return S3Utils.S3_HOST_NAME.concat("/" + key);
	}
}
