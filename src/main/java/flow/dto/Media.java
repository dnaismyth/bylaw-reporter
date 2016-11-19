package flow.dto;

import java.time.ZonedDateTime;

/**
 * DTO Media associated with a bylaw report
 * @author DN
 *
 */
public class Media {

	private Long id;
	private User reporter;
	private String fileName;
	private ZonedDateTime createdDate;
	
	public Media () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public User getReporter(){
		return reporter;
	}
	
	public void setReporter(User reporter){
		this.reporter = reporter;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
}
