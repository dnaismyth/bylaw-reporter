package flow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import flow.domain.RBylawReport;
import flow.dto.BylawReport;
import flow.dto.Media;
import flow.dto.RoleType;
import flow.dto.User;
import flow.exception.BadRequestException;
import flow.exception.NoPermissionException;
import flow.repository.BylawReportRepository;
import flow.service.mapper.BylawReportMapper;
import flow.service.util.RestPreconditions;

@Service
public class ReportService {
	
	@Autowired
	private BylawReportRepository reportRepo;
	
	@Autowired
	private MediaService mediaService;
	
	private BylawReportMapper reportMapper = new BylawReportMapper();
	
	/**
	 * Allow a new report to be created without media
	 * @param report
	 * @return
	 */
	public BylawReport createBylawReport(BylawReport report){
		RestPreconditions.checkNotNull(report);
		RBylawReport rb = reportMapper.toRBylawReport(report);
		RBylawReport saved = reportRepo.save(rb);
		return reportMapper.toBylawReport(saved);
	}
	
	/**
	 * Return a Bylaw report if the user is admin
	 * @param id
	 * @param user
	 * @return
	 * @throws BadRequestException
	 */
	public BylawReport getBylawReport(Long id, User user) throws NoPermissionException{
		RestPreconditions.checkNotNull(id);
		RestPreconditions.checkNotNull(user);
		if(user.getRole() != RoleType.ADMIN){
			throw new NoPermissionException("You must be an admin to view reports.");
		}
		
		RBylawReport found = reportRepo.findOne(id);
		return reportMapper.toBylawReport(found);
	}
	
	/**
	 * Return all reports (for admin use)
	 * @param pageable
	 * @return
	 */
	public Page<BylawReport> getAllReports(Pageable pageable){
		RestPreconditions.checkNotNull(pageable);
		List<RBylawReport> allReports = reportRepo.findAll();
		return new PageImpl<BylawReport>(reportMapper.toBylawReportList(allReports), pageable, allReports.size());
	}
	
	/**
	 * Create bylaw report with media attachment
	 * @param report
	 * @param media
	 * @return
	 */
	public BylawReport createBylawReportWithMedia(BylawReport report, Media media){
		RestPreconditions.checkNotNull(report);
		RestPreconditions.checkNotNull(media);
		
		Media reportMedia = mediaService.createMedia(media);
		report.getReportMedia().add(reportMedia);
		
		return createBylawReport(report);
	}
}
