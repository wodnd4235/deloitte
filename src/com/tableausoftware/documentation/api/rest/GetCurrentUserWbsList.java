package com.tableausoftware.documentation.api.rest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


import org.springframework.core.io.ClassPathResource;

import com.tableausoftware.documentation.api.rest.Entity.MultiLaunchWbEntity;
import com.tableausoftware.documentation.api.rest.bindings.TableauCredentialsType;
import com.tableausoftware.documentation.api.rest.bindings.WorkbookType;
import com.tableausoftware.documentation.api.rest.util.RestApiUtils;

public class GetCurrentUserWbsList  {

	private static Logger s_logger = Logger.getLogger(GetCurrentUserWbsList.class);
	public static final String multiLaunchProjectName = "Default";


	private static final RestApiUtils s_restApiUtils = RestApiUtils.getInstance();

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

	public List<MultiLaunchWbEntity> getCurrentUserWbList(TableauCredentialsType credential,String currentSiteId,String currentUserId) {
		s_logger.info("inside getCurrentUserWbList method");
		String projectName = s_properties.getProperty("site.project.name");
		s_logger.info("cfs multi view project name :"+projectName);
		// Gets the list of workbooks the current user can read
		List<WorkbookType> currentUserWorkbooks = s_restApiUtils
				.invokeQueryWorkbooks(credential, currentSiteId, currentUserId)
				.getWorkbook();
		int i;
		List<MultiLaunchWbEntity> wbList = new ArrayList<MultiLaunchWbEntity>();
		for (i = 0; i < currentUserWorkbooks.size(); i++) {
			WorkbookType WorkbookTypeObj = currentUserWorkbooks.get(i);
			MultiLaunchWbEntity wbObj = new MultiLaunchWbEntity();
			wbObj.setProjectName(WorkbookTypeObj.getProject().getName());
			wbObj.setReportName(WorkbookTypeObj.getName());

			if (WorkbookTypeObj.getProject().getName()
					.equals(projectName)) {
				s_logger.info("List of cfs multiview workbook and project name  :"
						+ WorkbookTypeObj.getName()
						+ " and project name :"
						+ WorkbookTypeObj.getProject().getName());
				wbList.add(wbObj);
			}
		}

		// Signs out of the server. This invalidates the authentication token so
		// that it cannot be used for more requests.
		s_restApiUtils.invokeSignOut(credential);
		return wbList;
	}
	
	public List<MultiLaunchWbEntity> getGvoipCurrentUserWbList(TableauCredentialsType credential,String currentSiteId,String currentUserId) {
		s_logger.info("inside getGvoipCurrentUserWbList method");
		String projectName = s_properties.getProperty("site.gproject.name");
		s_logger.info("gvoip multi view project name :"+projectName);
		// Gets the list of workbooks the current user can read
		List<WorkbookType> currentUserWorkbooks = s_restApiUtils
				.invokeQueryWorkbooks(credential, currentSiteId, currentUserId)
				.getWorkbook();
		int i;
		List<MultiLaunchWbEntity> wbList = new ArrayList<MultiLaunchWbEntity>();
		for (i = 0; i < currentUserWorkbooks.size(); i++) {
			WorkbookType WorkbookTypeObj = currentUserWorkbooks.get(i);
			MultiLaunchWbEntity wbObj = new MultiLaunchWbEntity();
			wbObj.setProjectName(WorkbookTypeObj.getProject().getName());
			wbObj.setReportName(WorkbookTypeObj.getName());

			if (WorkbookTypeObj.getProject().getName()
					.equals(projectName)) {
				s_logger.info("List of gvoip multiview workbook and project name  :"
						+ WorkbookTypeObj.getName()
						+ " and project name :"
						+ WorkbookTypeObj.getProject().getName());
				wbList.add(wbObj);
			}
		}

		// Signs out of the server. This invalidates the authentication token so
		// that it cannot be used for more requests.
		s_restApiUtils.invokeSignOut(credential);
		return wbList;
	}
	
}
