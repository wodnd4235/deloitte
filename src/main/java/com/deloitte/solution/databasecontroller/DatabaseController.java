package com.deloitte.solution.databasecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.solution.databaseservice.DatabaseService;
import com.deloitte.solution.databasevo.DataBaseVO;

@Controller
public class DatabaseController {
	
	private DatabaseService databaseService;
	
	@RequestMapping("/Data")
	public ModelAndView databaseController() {
		ModelAndView mv = new ModelAndView();
		
		List<DataBaseVO> dataList = databaseService.getDatabaseList();
		
		
		return mv;
	}

}
