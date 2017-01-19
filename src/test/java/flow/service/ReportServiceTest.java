package flow.service;

import java.time.ZonedDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import flow.dto.BylawReport;
import flow.dto.ViolationType;

public class ReportServiceTest extends BaseTestClass {


	@Test
	public void testCreateReport(){
		BylawReport br = new BylawReport();
		br.setDescription("Property");
		br.setReporterAddress("1234 Avenue");
		br.setReporterEmailAddress("reporter@flow.com");
		br.setReporterPhone("123-314-1241");
		br.setReportType(ViolationType.PROPERTY);
		br.setReporterName("Reporter");
		br.setIncidentDate(ZonedDateTime.now());
		BylawReport created = reportService.createBylawReport(br, null);
		Assert.assertNotNull(created);
	}
}
