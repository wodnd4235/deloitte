package com.deloitte.solution.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TableuApiController {
	
	@RequestMapping("/ApiCall")
	public ModelAndView tableuApiController() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("Data/Data");
		
		
		
		return mv;
		
		
	}

}
