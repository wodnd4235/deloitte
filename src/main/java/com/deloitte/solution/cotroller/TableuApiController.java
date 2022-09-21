package com.deloitte.solution.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tableausoftware.documentation.api.rest.util.RestApiUtils;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping("/restService")
public class TableuApiController {
	
	@RequestMapping("/ApiCall")
	public ModelAndView tableuApiController() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("Data/Data");
		
		
		
		return mv;
		
		
	}

}
