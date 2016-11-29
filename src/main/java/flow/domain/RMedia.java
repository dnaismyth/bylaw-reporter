package flow.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	
}
