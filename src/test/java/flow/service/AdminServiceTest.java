package flow.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import flow.dto.BylawReport;
import flow.dto.ViolationType;

public class AdminServiceTest extends BaseTestClass {
	
	@Autowired
	private AdminService adminService;
	
	private BylawReport report1, report2;

	@Before
	public void setUp(){
		report1 = createTestReport("Test admin", "testadmin@flow.com", "Admin", ViolationType.PROPERTY);
		report2 = createTestReport("Test admin2", "testadmin2@flow.com", "Admin2", ViolationType.TRAFFIC);
	}
	
	@After
	public void tearDown(){
		reportService.removeReport(report1.getId());
		reportService.removeReport(report2.getId());
	}
	
	// Test that all reports can be found
	@Test
	public void testFindAllReports(){
		Page<BylawReport> reports = reportService.getAllReports(new PageRequest(0,5));
		Assert.assertEquals(2, reports.getContent().size());
	}
	
	// Test that reports can be filtered based on their violation type
	@Test
	public void testFinReportsByViolationType(){
		Page<BylawReport> reports = reportService.findReportsByViolationType(ViolationType.PROPERTY, new PageRequest(0,5));
		Assert.assertEquals(1, reports.getContent().size());
	}
}
