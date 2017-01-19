package flow.web.rest;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import flow.dto.BylawReport;
import flow.dto.Operation;
import flow.dto.User;
import flow.dto.ViolationType;
import flow.exception.NoPermissionException;
import flow.web.rest.dto.ResponseList;
import flow.web.rest.dto.RestResponse;

@RestController
@RequestMapping("/api/admin")
public class AdminReportController extends BaseController {

	private static final String REPORT_TYPE_PARAM = "type";
	
	/**
	 * Allow for an admin to find a report by id
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(value ="/reports/{id}", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse<BylawReport> getBylawReport(@PathVariable("id") Long id) throws NoPermissionException {
		User admin = getCurrentUser();
		BylawReport report = reportService.getBylawReport(id, admin);
		return new RestResponse<BylawReport>(report);
	}
	
	/**
	 * Return all reports if the current user is admin
	 * @param size
	 * @param page
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(value="/reports", method = RequestMethod.GET)
	@ResponseBody
	public ResponseList<BylawReport> getAllBylawReports(@Param(PARAM_SIZE) int size, 
			@Param(PARAM_PAGE) int page) throws NoPermissionException{
		User user = getCurrentUser();
		checkAdminAuthority(user);
		Page<BylawReport> allReports = reportService.getAllReports(new PageRequest(page, size));
		return new ResponseList<BylawReport>(allReports);
		
	}
	
	/**
	 * Find all reports by violation type.
	 * If no type is given, return all reports.
	 * @param size
	 * @param page
	 * @param criteria
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(value="/type/reports", method = RequestMethod.GET)
	@ResponseBody
	public ResponseList<BylawReport> getBylawReportsByType(@Param(PARAM_SIZE)int size,
			@Param(PARAM_PAGE) int page, @RequestParam(required=false) Map<String, String> criteria) throws NoPermissionException{
		User user = getCurrentUser();
		checkAdminAuthority(user);
		Page<BylawReport> reports;
		if(criteria.containsKey(REPORT_TYPE_PARAM)){
			ViolationType type = ViolationType.valueOf(criteria.get(REPORT_TYPE_PARAM));
			reports = reportService.findReportsByViolationType(type, new PageRequest(page, size));
		} else {
			reports = reportService.getAllReports(new PageRequest(page,size));
		}
		
		return new ResponseList<BylawReport>(reports);	
	}
	
	/**
	 * Admin removal of reports
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(value="reports/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public RestResponse<Long> removeBylawReport(@Param("id") Long id) throws NoPermissionException{
		User user = getCurrentUser();
		checkAdminAuthority(user);
		reportService.removeReport(id);
		return new RestResponse<Long>(Operation.DELETE, id);
	}
	
	
}
