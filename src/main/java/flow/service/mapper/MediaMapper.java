package flow.service.mapper;

import flow.domain.RMedia;
import flow.dto.Media;

/**
 * Mapper for media objects
 * @author DN
 *
 */
public class MediaMapper {

	/**
	 * To Media DTO
	 * @param rm
	 * @return
	 */
	public Media toMedia(RMedia rm){
		Media m = null;
		if(rm != null){
			m = new Media();
			m.setFileName(rm.getFileName());
			m.setId(rm.getId());
			// todo: set owner once user mapper is created
			m.setCreatedDate(rm.getCreatedDate());
		}
		
		return m;
	}
	
	/**
	 * To RMedia entity
	 * @param m
	 * @return
	 */
	public RMedia toRMedia(Media m){
		RMedia rm = null;
		if(m != null){
			rm = new RMedia();
			rm.setFileName(m.getFileName());
			rm.setId(m.getId());
			//TODO: set owner once user mapper is created
			rm.setCreatedDate(m.getCreatedDate());
		}
		
		return rm;
	}
	
	
}
