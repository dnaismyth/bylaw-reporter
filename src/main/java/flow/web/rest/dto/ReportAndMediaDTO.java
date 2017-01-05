package flow.web.rest.dto;

import javax.validation.constraints.NotNull;

import flow.dto.BylawReport;
import flow.dto.Media;

public class ReportAndMediaDTO {

	@NotNull
	private BylawReport report;
	
	private Media reportMedia;
	
	public ReportAndMediaDTO(){}
	
	public ReportAndMediaDTO(BylawReport report, Media reportMedia){
		this.report = report;
		this.reportMedia = reportMedia;
	}

	public BylawReport getReport() {
		return report;
	}

	public void setReport(BylawReport report) {
		this.report = report;
	}

	public Media getReportMedia() {
		return reportMedia;
	}

	public void setReportMedia(Media reportMedia) {
		this.reportMedia = reportMedia;
	}
	
//	@Override
//    public String toString() {
//        return "LoginDTO{" +
//            "[Bylaw Report]='" + report.toString() + '\'' +
//            ", [Bylaw Media]='" + reportMedia.toString() + '\'' +
//            '}';
//    }
}
