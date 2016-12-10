package flow.service;

import org.apache.log4j.Logger;
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

	
}
