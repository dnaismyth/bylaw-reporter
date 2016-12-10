package flow.service.mapper;

import java.util.ArrayList;
import java.util.List;

import flow.domain.RBylawReport;
import flow.domain.RMedia;
import flow.domain.RReporter;
import flow.dto.BylawReport;
import flow.dto.Media;

/**
 * Mapper for BylawReports
 * @author DN
 *
 */
public class BylawReportMapper {

	private MediaMapper mediaMapper = new MediaMapper();
	
	/**
	 * To BylawReport DTO
	 * @param rb
	 * @return
	 */
	public BylawReport toBylawReport(RBylawReport rb){
		
		BylawReport report = null;
		if(rb != null){
			report = new BylawReport();
			report.setCreatedDate(rb.getCreatedDate());
			report.setId(rb.getId());
			report.setIncidentDate(rb.getIncidentDate());
			report.setReportMedia(mediaMapper.toMedia(rb.getMedia()));
			
			if(rb.getReporterInformation() != null){
				setReporterInformation(report, rb.getReporterInformation());
			}
			
			report.setReportType(rb.getReportType());
			report.setDescription(rb.getDescription());
		}
		
		return report;
	}
	
	/**
	 * To RBylawReport entity
	 * @param br
	 * @return
	 */
	public RBylawReport toRBylawReport(BylawReport br){
		RBylawReport report = null;
		if(br != null){
			report = new RBylawReport();
			report.setCreatedDate(br.getCreatedDate());
			report.setId(br.getId());
			report.setIncidentDate(br.getIncidentDate());
			report.setMedia(mediaMapper.toRMedia(br.getReportMedia()));
			RReporter ri = buildReporterInformation(br);
			report.setReporterInformation(ri);
			report.setDescription(br.getDescription());
		}
		
		return report;
	}
	
	/**
	 * Return list of BylawReport
	 * @param reportList
	 * @return
	 */
	public List<BylawReport> toBylawReportList(List<RBylawReport> reportList){
		List<BylawReport> reports = null;
		if(reportList != null){
			reports = new ArrayList<BylawReport>();
			for(RBylawReport rb : reportList){
				reports.add(toBylawReport(rb));
			}
		}
		return reports;
	}
	
	/**
	 * Set Reporter information
	 * @param report
	 * @param ri
	 */
	private void setReporterInformation(BylawReport report, RReporter ri){
		report.setReporterName(ri.getName());
		report.setReporterAddress(ri.getAddress());
		report.setReporterEmailAddress(ri.getEmailAddress());
		report.setReporterPhone(ri.getPhone());
	}
	
	/**
	 * Build ReporterInformation from BylawReport DTO
	 * @param br
	 * @return
	 */
	private RReporter buildReporterInformation(BylawReport br){
		RReporter ri = null;
		if(br != null){
			ri = new RReporter();
			ri.setAddress(br.getReporterAddress());
			ri.setEmailAddress(br.getReporterEmailAddress());
			ri.setName(br.getReporterName());
			ri.setPhone(br.getReporterPhone());
		}
		return ri;
	}
}
