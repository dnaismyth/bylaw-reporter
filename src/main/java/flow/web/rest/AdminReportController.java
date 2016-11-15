package flow.web.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import flow.dto.BylawReport;
import flow.dto.User;
import flow.exception.NoPermissionException;
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
}
