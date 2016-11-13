package flow.dto;

import java.time.ZonedDateTime;

/**
 * DTO Media associated with a bylaw report
 * @author DN
 *
 */
public class Media {

	private Long id;
	private UserItem owner;
	private String fileName;
	private ZonedDateTime createdDate;
	
	public Media () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public UserItem getOwner(){
		return owner;
	}
	
	public void setOwner(UserItem owner){
		this.owner = owner;
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
