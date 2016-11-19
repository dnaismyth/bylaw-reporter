package flow.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import flow.dto.BylawReport;
import flow.dto.User;
import flow.exception.NoPermissionException;
import flow.web.rest.dto.ResponseList;
import flow.web.rest.dto.RestResponse;

@RestController
@RequestMapping("/api/admin")
public class AdminReportController extends BaseController {

	
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
		checkUserAuthority(user);
		Page<BylawReport> allReports = reportService.getAllReports(new PageRequest(page, size));
		return new ResponseList<BylawReport>(allReports);
		
	}
}
