package flow.dto;

import java.time.ZonedDateTime;

/**
 * DTO Media associated with a bylaw report
 * @author DN
 *
 */
public class Media {

	private Long id;
	private String fileName;
	private BylawReport bylawReport;
	private ZonedDateTime createdDate;
	
	public Media () {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BylawReport getBylawReport() {
		return bylawReport;
	}

	public void setBylawReport(BylawReport bylawReport) {
		this.bylawReport = bylawReport;
	}

	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
}
