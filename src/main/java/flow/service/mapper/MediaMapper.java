package flow.service.mapper;

import flow.domain.RMedia;
import flow.dto.Media;
import flow.dto.MediaInfo;
import flow.dto.MediaType;
import flow.service.util.MediaUtils;

/**
 * Mapper for media objects
 * @author DN
 *
 */
public class MediaMapper {
	
	private UserMapper userMapper = new UserMapper();

	/**
	 * To Media DTO
	 * @param rm
	 * @return
	 */
	public Media toMedia(RMedia rm){
		Media m = null;
		if(rm != null){
			m = new Media();
			MediaInfo portrait = mapMediaInfo(rm, MediaType.PORTRAIT);
			MediaInfo thumbnail = mapMediaInfo(rm, MediaType.THUMBNAIL);
			m.setPortrait(portrait);
			m.setThumbnail(thumbnail);
			m.setId(rm.getId());
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
			MediaInfo tmb = m.getThumbnail();
			
			if(tmb != null){
				rm.setThumbnailHeight(tmb.getHeight());
				rm.setThumbnailWidth(tmb.getWidth());
				rm.setThumbnailKey(tmb.getS3Key());
			}
			
			MediaInfo p = m.getPortrait();
			if(p != null){
				rm.setPortraitHeight(p.getHeight());
				rm.setPortraitWidth(p.getWidth());
				rm.setPortraitKey(p.getS3Key());
			}
			
			rm.setId(m.getId());
			rm.setCreatedDate(m.getCreatedDate());
		}
		
		return rm;
	}
	
	private MediaInfo mapMediaInfo(RMedia rm, MediaType type){
		MediaInfo mediaInfo = new MediaInfo();
		if(type == MediaType.PORTRAIT){
			mediaInfo.setHeight(rm.getPortraitHeight());
			mediaInfo.setWidth(rm.getPortraitWidth());
			mediaInfo.setUrl(MediaUtils.generateUrlFromKey(rm.getPortraitKey()));
			mediaInfo.setS3Key(rm.getPortraitKey());
		}
		
		if(type == MediaType.THUMBNAIL){
			mediaInfo.setHeight(rm.getThumbnailHeight());
			mediaInfo.setWidth(rm.getThumbnailWidth());
			mediaInfo.setUrl(MediaUtils.generateUrlFromKey(rm.getThumbnailKey()));
			mediaInfo.setS3Key(rm.getThumbnailKey());
		}
		
		return mediaInfo;
	}
	
}
