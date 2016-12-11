package flow.dto;

/**
 * Basic media properties/info
 * @author DN
 *
 */
public class MediaInfo {

	private String url;
	private String s3Key;
	private int width;
	private int height;
	private MediaType mediaType;
	
	public MediaInfo(){}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public MediaType getMediaType(){
		return mediaType;
	}
	
	public void setMediaType(MediaType mediaType){
		this.mediaType = mediaType;
	}
	
	public String getS3Key(){
		return s3Key;
	}
	
	public void setS3Key(String s3Key){
		this.s3Key = s3Key;
	}
	
	@Override
	public String toString() {
		return "BylawReport{" +
	            "url='" + url + '\'' +
	            ", s3Key='" + s3Key + '\'' +
	            ", width='" + width + '\'' +
	            ", height='" + height + '\'' +
	            ", mediaType='" + mediaType + '\'' +
	            '}';
	}
	
}
