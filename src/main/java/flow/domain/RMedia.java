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
	 * Retina s3 key
	 */
	@Column(name = "portrait_key", nullable = false)
	private String portraitKey;
	
	/**
	 * Thumbnail s3 key
	 */
	@Column(name="thumbnail_key", nullable = false)
	private String thumbnailKey;

	/**
	 * Thumbnail height
	 */
	@Column (name="t_height", nullable = false)
	private int tHeight;
	
	/**
	 * Thumbnail width
	 */
	@Column (name="t_width", nullable = false)
	private int tWidth;
	
	/**
	 * Retina height
	 */
	@Column(name="p_height", nullable = false)
	private int pHeight;
	
	/**
	 * Retina Width
	 */
	@Column(name = "p_width", nullable = false)
	private int pWidth;
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	public String getPortraitKey() {
		return portraitKey;
	}

	public void setPortraitKey(String portraitKey) {
		this.portraitKey = portraitKey;
	}

	public String getThumbnailKey() {
		return thumbnailKey;
	}

	public void setThumbnailKey(String thumbnailKey) {
		this.thumbnailKey = thumbnailKey;
	}

	public int getThumbnailHeight() {
		return tHeight;
	}

	public void setThumbnailHeight(int tHeight) {
		this.tHeight = tHeight;
	}

	public int getThumbnailWidth() {
		return tWidth;
	}

	public void setThumbnailWidth(int tWidth) {
		this.tWidth = tWidth;
	}

	public int getPortraitHeight() {
		return pHeight;
	}

	public void setPortraitHeight(int pHeight) {
		this.pHeight = pHeight;
	}

	public int getPortraitWidth() {
		return pWidth;
	}

	public void setPortraitWidth(int pWidth) {
		this.pWidth = pWidth;
	}
	
}
