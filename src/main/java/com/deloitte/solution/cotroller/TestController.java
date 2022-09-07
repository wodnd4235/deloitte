package com.deloitte.solution.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
	
	@GetMapping("/test")
	public ModelAndView testController() {
		ModelAndView mv = new ModelAndView();
	
		log.info("하이?ㄴㅁ이ㅓ모나ㅓ온모라ㅓ모라ㅓㅁ라ㅓ몬라ㅓㅗㅁㄴ로");
//	
//		try {
//			
//		}catch(Exception e) {
//			
//		}
		
		return mv;
	}
		
}
