package com.deloitte.solution.databasecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.solution.cotroller.TestController;
import com.deloitte.solution.databaseservice.DatabaseService;
import com.deloitte.solution.databasevo.DataBaseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DatabaseController {
	
	
	
	@Autowired
	private DatabaseService databaseService;
//	public DatabaseController(DatabaseService databaseService) {
//		this.databaseService = databaseService;
//	}
//	
	@RequestMapping("/Data")
	public ModelAndView databaseController() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Data/Data");
		log.info("test");
		List<DataBaseVO> dataList = databaseService.getDatabaseList();
		
		
		return mv;
	}

}
