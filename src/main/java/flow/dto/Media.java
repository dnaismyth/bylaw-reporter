package flow.dto;

import java.time.ZonedDateTime;

/**
 * DTO Media associated with a bylaw report
 * @author DN
 *
 */
public class Media {

	private Long id;
	private Reporter reporter;
	private MediaInfo portrait;
	private MediaInfo thumbnail;
	private ZonedDateTime createdDate;
	
	public Media () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Reporter getReporter(){
		return reporter;
	}
	
	public void setReporter(Reporter reporter){
		this.reporter = reporter;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public MediaInfo getPortrait() {
		return portrait;
	}

	public void setPortrait(MediaInfo portrait) {
		this.portrait = portrait;
	}

	public MediaInfo getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(MediaInfo thumbnail) {
		this.thumbnail = thumbnail;
	}

//	@Override
//	public String toString() {
//		return "BylawReport{" +
//	            "id='" + id + '\'' +
//	            ", createdDate='" + createdDate + '\'' +
//	            ", reporterName='" + reporter.getName() + '\'' +
//	            ", thumbnail='" + thumbnail.toString() + '\'' +
//	            ", portrait='" + portrait.toString() + '\'' +
//	            ", createdDate='" + createdDate + '\'' +
//	            '}';
//	}
	
	
	
}
