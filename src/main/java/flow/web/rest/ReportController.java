package flow.web.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import flow.dto.BylawReport;
import flow.dto.User;
import flow.exception.NoPermissionException;
import flow.web.rest.dto.RestResponse;


/**
 * Controller for CRUD operations of Bylaw Reports
 * @author DN
 *
 */
@RestController
@RequestMapping("/api/report")
public class ReportController extends BaseController{

	/**
	 * Allow for a user/guest to create a new bylaw report
	 * @param report
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public RestResponse<BylawReport> createBylawReport(@RequestBody BylawReport report){
		
		BylawReport created = reportService.createBylawReport(report);
		return new RestResponse<BylawReport>(created);
	}
	
	/**
	 * Allow for an admin to find a report by id
	 * @param id
	 * @return
	 * @throws NoPermissionException
	 */
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse<BylawReport> getBylawReport(@PathVariable("id") Long id) throws NoPermissionException {
		User admin = getCurrentUser();
		BylawReport report = reportService.getBylawReport(id, admin);
		return new RestResponse<BylawReport>(report);
	}
}
