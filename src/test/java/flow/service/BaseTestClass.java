package flow.service;

import java.time.ZonedDateTime;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import flow.BylawReportApp;
import flow.dto.BylawReport;
import flow.dto.ViolationType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BylawReportApp.class)
public class BaseTestClass {
	protected static final Logger logger = Logger.getLogger(BaseTestClass.class); 
	
	@Autowired
	protected ReportService reportService;

	protected BylawReport createTestReport(String description, String reporterEmail, String reporterName, ViolationType type){
		BylawReport br = new BylawReport();
		br.setDescription(description);
		br.setReporterAddress("1234 Avenue");
		br.setReporterEmailAddress(reporterEmail);
		br.setReporterPhone("123-314-1241");
		br.setReportType(type);
		br.setReporterName(reporterName);
		br.setIncidentDate(ZonedDateTime.now());
		BylawReport created = reportService.createBylawReport(br, null);
		return created;
	}
	
}
