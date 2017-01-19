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
import flow.dto.ViolationType;
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
		Page<RBylawReport> allReports = reportRepo.findAll(pageable);
		return reportMapper.toBylawReportPage(allReports);
	}
	
	/**
	 * Create bylaw report with media attachment
	 * @param report
	 * @param media
	 * @return
	 */
	public BylawReport createBylawReport(BylawReport report, Media media){
		RestPreconditions.checkNotNull(report);
		
		if(media != null){
			Media reportMedia = mediaService.createMedia(media);
			report.setReportMedia(reportMedia);
		}	
		
		return createAndSaveBylawReport(report);
	}
	
	
	private BylawReport createAndSaveBylawReport(BylawReport report){
		RestPreconditions.checkNotNull(report);
		RBylawReport rb = reportMapper.toRBylawReport(report);
		RBylawReport saved = reportRepo.save(rb);
		return reportMapper.toBylawReport(saved);
	}
	
	/**
	 * Find BylawReports by violation type
	 * @param type
	 * @param pageable
	 * @return
	 */
	public Page<BylawReport> findReportsByViolationType(ViolationType type, Pageable pageable){
		RestPreconditions.checkNotNull(type);
		Page<RBylawReport> reports = reportRepo.findReportsByViolationType(type, pageable);
		return reportMapper.toBylawReportPage(reports);
	}
	
	/**
	 * Allow for an admin to delete a report
	 * @param reportId
	 */
	public void removeReport(Long reportId){
		RestPreconditions.checkNotNull(reportId);
		reportRepo.delete(reportId);
	}
}
