package flow.dto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class BylawReport {
	
	public Long id;
	public ZonedDateTime createdDate;
	/**
	 * Reporter information fields
	 */
	private String reporterName;
	private String reporterAddress;
	private String reporterPhone;
	private String reporterEmailAddress;
	
	/**
	 * Incident information fields
	 */
	private ZonedDateTime incidentDate;
	private String description;
	private ViolationType reportType;
	
	
	private Media reportMedia;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public ZonedDateTime getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(ZonedDateTime createdDate) {
		this.createdDate = createdDate;
	}


	public String getReporterName() {
		return reporterName;
	}


	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}


	public String getReporterAddress() {
		return reporterAddress;
	}


	public void setReporterAddress(String reporterAddress) {
		this.reporterAddress = reporterAddress;
	}


	public String getReporterPhone() {
		return reporterPhone;
	}


	public void setReporterPhone(String reporterPhone) {
		this.reporterPhone = reporterPhone;
	}


	public String getReporterEmailAddress() {
		return reporterEmailAddress;
	}


	public void setReporterEmailAddress(String reporterEmailAddress) {
		this.reporterEmailAddress = reporterEmailAddress;
	}


	public ZonedDateTime getIncidentDate() {
		return incidentDate;
	}


	public void setIncidentDate(ZonedDateTime incidentDate) {
		this.incidentDate = incidentDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ViolationType getReportType() {
		return reportType;
	}


	public void setReportType(ViolationType reportType) {
		this.reportType = reportType;
	}


	public Media getReportMedia() {
		return reportMedia;
	}


	public void setReportMedia(Media reportMedia){
		this.reportMedia = reportMedia;
	}

	
}
