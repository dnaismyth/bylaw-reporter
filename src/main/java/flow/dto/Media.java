package flow.dto;

import java.time.ZonedDateTime;

/**
 * DTO Media associated with a bylaw report
 * @author DN
 *
 */
public class Media {

	private Long id;
	private Reporter reporter;	//TODO: change this to a reporter object
	private String fileName;
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
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
}
