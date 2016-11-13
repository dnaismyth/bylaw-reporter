package flow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flow.domain.RBylawReport;
import flow.dto.BylawReport;
import flow.dto.RoleType;
import flow.dto.User;
import flow.exception.BadRequestException;
import flow.repository.BylawReportRepository;
import flow.service.mapper.BylawReportMapper;
import flow.service.util.RestPreconditions;

@Service
public class ReportService {
	
	@Autowired
	private BylawReportRepository reportRepo;
	
	private BylawReportMapper reportMapper = new BylawReportMapper();
	
	/**
	 * Allow a new report to be created
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
	public BylawReport getBylawReport(Long id, User user) throws BadRequestException{
		RestPreconditions.checkNotNull(id);
		RestPreconditions.checkNotNull(user);
		if(user.getRole() != RoleType.ADMIN){
			throw new BadRequestException("You must be an admin to view reports.");
		}
		
		RBylawReport found = reportRepo.findOne(id);
		return reportMapper.toBylawReport(found);
	}
}
