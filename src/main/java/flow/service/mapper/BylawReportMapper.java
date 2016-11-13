package flow.service.mapper;

import flow.domain.RBylawReport;
import flow.domain.RMedia;
import flow.domain.ReporterInformation;
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
			if(rb.getMedia() != null && !rb.getMedia().isEmpty()){
				for(RMedia rm : rb.getMedia()){
					report.getReportMedia().add(mediaMapper.toMedia(rm));
				}
			}
			
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
			if(br.getReportMedia() != null && !br.getReportMedia().isEmpty()){
				for(Media m : br.getReportMedia()){
					report.getMedia().add(mediaMapper.toRMedia(m));
				}
			}
			ReporterInformation ri = buildReporterInformation(br);
			report.setReporterInformation(ri);
			report.setDescription(br.getDescription());
		}
		
		return report;
	}
	
	/**
	 * Set Reporter information
	 * @param report
	 * @param ri
	 */
	private void setReporterInformation(BylawReport report, ReporterInformation ri){
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
	private ReporterInformation buildReporterInformation(BylawReport br){
		ReporterInformation ri = null;
		if(br != null){
			ri = new ReporterInformation();
			ri.setAddress(br.getReporterAddress());
			ri.setEmailAddress(br.getReporterEmailAddress());
			ri.setName(br.getReporterName());
			ri.setPhone(br.getReporterPhone());
		}
		return ri;
	}
}
