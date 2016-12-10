package flow.web.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import flow.dto.BylawReport;
import flow.dto.Media;
import flow.dto.User;
import flow.exception.NoPermissionException;
import flow.web.rest.dto.ResponseList;
import flow.web.rest.dto.RestResponse;


/**
 * Controller for CRUD operations of Bylaw Reports
 * @author DN
 *
 */
@RestController
@RequestMapping("/api/reports")
public class ReportController extends BaseController{

	/**
	 * Allow for a user/guest to create a new bylaw report
	 * @param report
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public RestResponse<BylawReport> createBylawReport(@RequestBody BylawReport report, HttpServletRequest request){
		String baseUrl = getBaseUrl(request);
		Media reportMedia = report.getReportMedia() != null ? report.getReportMedia() : null;
		BylawReport created = reportService.createBylawReport(report, reportMedia);
		String email = report.getReporterEmailAddress();
		String name = report.getReporterName();
		mailService.sendReportReceivedEmail(email, name, baseUrl);
		return new RestResponse<BylawReport>(created);
	}
	
}
