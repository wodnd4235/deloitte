package com.tableausoftware.documentation.api.rest;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tableausoftware.documentation.api.rest.Entity.MultiLaunchWbEntity;
import com.tableausoftware.documentation.api.rest.bindings.TableauCredentialsType;
import com.tableausoftware.documentation.api.rest.util.RestApiUtils;

@RestController
@RequestMapping("/restService")
public class GetMultiLaunchWbsController {

	private static Logger s_logger = Logger.getLogger(GetMultiLaunchWbsController.class);
	private static final RestApiUtils s_restApiUtils = RestApiUtils
			.getInstance();
	GetWorkbooksList getWbListClassObj = new GetWorkbooksList();
	GetCurrentUserWbsList getCurrentUserWbsListObj = new GetCurrentUserWbsList();
	private static Properties s_properties = new Properties();

	static {
		// Configures the logger to log to stdout
		BasicConfigurator.configure();

		// Loads the values from configuration file into the Properties instance
		try {
			s_properties.load(new ClassPathResource("config.properties").getInputStream());
		} catch (IOException e) {
			s_logger.error("Failed to load configuration files.");
		}
	}


	@RequestMapping(value = "/GetMultiLaunchWbs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllWbs() {
		s_logger.info("GetMultiLaunchWbsController.getAllWbs called");

		List<MultiLaunchWbEntity> MultiLaunchWbList = getWbListClassObj
				.getWbList();
		s_logger.info("total no of multi launch reports:"
				+ MultiLaunchWbList.size());
		Gson gson = new Gson();

		// convert java object to JSON format, 
		// and returned as JSON formatted  string
		String json = gson.toJson(MultiLaunchWbList);
		s_logger.info("converted json string :" + json); 
		// returning mapped data to URL (service)
		return new ResponseEntity<String>(json, new HttpHeaders(),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/tableauSignin", method = RequestMethod.POST)
	public ResponseEntity<String> tableauSignin(
			@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "Password", required = true) String Password,
			@RequestParam(value = "siteId", required = true) String siteId)
			throws Exception {

		s_logger.info("inside tableauSignin method ::::");

		try {
			TableauCredentialsType credential = s_restApiUtils.invokeSignIn(
					userId, Password, siteId);
			String currentSiteId = credential.getSite().getId();
			String currentUserId = credential.getUser().getId();

			s_logger.info("current loggedin userid :" + currentUserId);
			s_logger.info("current loggedin siteid :" + currentSiteId);

			List<MultiLaunchWbEntity> MultiLaunchWbList = getCurrentUserWbsListObj.getCurrentUserWbList(credential, currentSiteId, currentUserId);
					
			s_logger.info("total no of multi launch reports:"
					+ MultiLaunchWbList.size());
			Gson gson = new Gson();

			// convert java object to JSON format, 
			// and returned as JSON formatted  string
			String json = gson.toJson(MultiLaunchWbList);
			s_logger.info("converted json string :" + json); 
			// returning mapped data to URL (service)
			return new ResponseEntity<String>(json, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			s_logger.info("Exception Occured .....exiting tableauSignin method ::::");
			return new ResponseEntity<String>("Sign in is failed!",
					new HttpHeaders(), HttpStatus.OK);
			
		}

	}
	
	@RequestMapping(value = "/signInAndGetWbList", method = RequestMethod.POST)
    public ResponseEntity<String> signInAndGetWbList()
                                    throws Exception {
		s_logger.info("inside signInAndGetWbList method ::::");

		String userId = s_properties.getProperty("user.admin.name");
		String Password = s_properties.getProperty("user.admin.password");
		String siteId = s_properties.getProperty("site.default.contentUrl");
		try {
			TableauCredentialsType credential = s_restApiUtils.invokeSignIn(
					userId, Password, siteId);
			String currentSiteId = credential.getSite().getId();
			String currentUserId = credential.getUser().getId();

			s_logger.info("current loggedin userid :" + currentUserId);
			s_logger.info("current loggedin siteid :" + currentSiteId);

			List<MultiLaunchWbEntity> MultiLaunchWbList = getCurrentUserWbsListObj.getCurrentUserWbList(credential, currentSiteId, currentUserId);
					
			s_logger.info("total no of cfs multi launch reports:"
					+ MultiLaunchWbList.size());
			Gson gson = new Gson();

			// convert java object to JSON format, 
			// and returned as JSON formatted  string
			String json = gson.toJson(MultiLaunchWbList);
			s_logger.info("converted json string :" + json); 
			// returning mapped data to URL (service)
			return new ResponseEntity<String>(json, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			s_logger.info("Exception Occured .....exiting signInAndGetWbList method ::::");
			return new ResponseEntity<String>("Sign in is failed!",
					new HttpHeaders(), HttpStatus.OK);
			
		}
		
		
	}

	@RequestMapping(value = "/signInAndGetGvoipWbList", method = RequestMethod.POST)
    public ResponseEntity<String> signInAndGetGvoipWbList()
                                    throws Exception {
		s_logger.info("inside signInAndGetGvoipWbList method ::::");

		String userId = s_properties.getProperty("user.admin.name");
		String Password = s_properties.getProperty("user.admin.password");
		String siteId = s_properties.getProperty("site.default.contentUrl");
		try {
			TableauCredentialsType credential = s_restApiUtils.invokeSignIn(
					userId, Password, siteId);
			String currentSiteId = credential.getSite().getId();
			String currentUserId = credential.getUser().getId();

			s_logger.info("current loggedin userid :" + currentUserId);
			s_logger.info("current loggedin siteid :" + currentSiteId);

			List<MultiLaunchWbEntity> MultiLaunchWbList = getCurrentUserWbsListObj.getGvoipCurrentUserWbList(credential, currentSiteId, currentUserId);
					
			s_logger.info("total no of gvoip multi launch reports:"
					+ MultiLaunchWbList.size());
			Gson gson = new Gson();

			// convert java object to JSON format, 
			// and returned as JSON formatted  string
			String json = gson.toJson(MultiLaunchWbList);
			s_logger.info("converted json string :" + json); 
			// returning mapped data to URL (service)
			return new ResponseEntity<String>(json, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			s_logger.info("Exception Occured .....exiting signInAndGetGvoipWbList method ::::");
			return new ResponseEntity<String>("Sign in is failed!",
					new HttpHeaders(), HttpStatus.OK);
			
		}
		
		
	}


}
