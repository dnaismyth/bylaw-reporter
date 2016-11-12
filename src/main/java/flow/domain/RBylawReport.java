package flow.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import flow.dto.ViolationType;


@Entity
@Table(name="report")
public class RBylawReport extends AbstractAuditingEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6286367746542743794L;
	
	
    /**
	 * Unique Id
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Date and Time of when the incident had been observed
	 */
	@Column(name = "incident_date", nullable = false)
	private ZonedDateTime incidentDate;
	
	/**
	 * Description of the incident
	 */
	@Column(name="description", nullable = false)
	private String description;
	
	/**
	 * The type of violation
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="violation_type")
	private ViolationType reportType;
	
	/**
	 * Optional image attachment
	 */
	@OneToOne
	private RMedia media;
	
	/**
	 * Basic user information of the REPORTER
	 */
	@Embedded
	private RReporterInformation reporterInformation;

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

	public RReporterInformation getUserInformation() {
		return reporterInformation;
	}

	public void setReporterInformation(RReporterInformation reporterInformation) {
		this.reporterInformation = reporterInformation;
	}
	
	public RMedia getMedia() {
		return media;
	}

	public void setMedia(RMedia media) {
		this.media = media;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
}
