package flow.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Optional media attachment for a bylaw report
 * @author DN
 *
 */
@Entity
@Table(name = "media")
public class RMedia extends AbstractAuditingEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8070329744224476811L;
	
    /**
	 * Unique Id
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * Name of the media file
	 */
	@Column(name = "filename", nullable = false)
	private String fileName;
	
	/**
	 * Report that the media is associated with
	 */
	@OneToOne
	private RBylawReport bylawReport;
	
	/**
	 * Id of the user that is making the report
	 */
	@Column(name = "reporter_id")
	private Long reporterId;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public RBylawReport getBylawReport() {
		return bylawReport;
	}

	public void setBylawReport(RBylawReport bylawReport) {
		this.bylawReport = bylawReport;
	}

	public Long getReporterId() {
		return reporterId;
	}

	public void setReporterId(Long reporterId) {
		this.reporterId = reporterId;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	
}
