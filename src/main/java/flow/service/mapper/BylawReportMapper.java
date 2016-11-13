package flow.service.mapper;

import flow.domain.RBylawReport;
import flow.domain.ReporterInformation;
import flow.dto.BylawReport;

/**
 * Mapper for BylawReports
 * @author DN
 *
 */
public class BylawReportMapper {

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
			if(rb.getMedia() != null){
				
			}
			
			if(rb.getReporterInformation() != null){
				setReporterInformation(report, rb.getReporterInformation());
			}
			
			report.setReportType(rb.getReportType());
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
			if(br.getReportMedia() != null && !br.getReportMedia().isEmpty()){
				
			}
		}
		
		return report;
	}
	
	private void setReporterInformation(BylawReport report, ReporterInformation ri){
		report.setReporterName(ri.getName());
		report.setReporterAddress(ri.getAddress());
		report.setReporterEmailAddress(ri.getEmailAddress());
		report.setReporterPhone(ri.getPhone());
	}
}
