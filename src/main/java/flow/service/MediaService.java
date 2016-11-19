package flow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flow.domain.RMedia;
import flow.dto.Media;
import flow.dto.RoleType;
import flow.dto.User;
import flow.exception.BadRequestException;
import flow.repository.MediaRepository;
import flow.service.mapper.MediaMapper;
import flow.service.util.RestPreconditions;

@Service
public class MediaService {
	
	@Autowired
	private MediaRepository mediaRepo;
	
	private MediaMapper mediaMapper = new MediaMapper();

	/**
	 * Create media
	 * @param media
	 * @return
	 */
	public Media createMedia(Media media){
		RestPreconditions.checkNotNull(media);
		RMedia rm = mediaMapper.toRMedia(media);
		RMedia saved = mediaRepo.save(rm);
		return mediaMapper.toMedia(saved);
	}
	
	/**
	 * For Admin use
	 * @param mediaId
	 * @return
	 * @throws BadRequestException 
	 */
	public void deleteMedia(Long mediaId) throws BadRequestException{
		RestPreconditions.checkNotNull(mediaId);
		mediaRepo.delete(mediaId);
	}
}
