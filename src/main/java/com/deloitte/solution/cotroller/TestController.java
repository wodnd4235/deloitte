package com.deloitte.solution.cotroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {
	
	@GetMapping("/test")
	public void testController() {
//		ModelAndView mv = new ModelAndView();
		Object a = new Object();
		a = "asdasdasd";
		ModelAndView mv = new ModelAndView();
		mv.addObject("dddd",a);
		log.error("하이?ㄴㅁ이ㅓ모나ㅓ온모라ㅓ모라ㅓㅁ라ㅓ몬라ㅓㅗㅁㄴ로 {}", mv);
//		log.warn("sadjsadlkasjdalksjdjsad");
//		try {
//			
//		}catch(Exception e) {
//			
//		}
		
//		return mv;
	}
		
	@GetMapping("/test2")
	public String getApiTest() {
		return "test";
	}
}
