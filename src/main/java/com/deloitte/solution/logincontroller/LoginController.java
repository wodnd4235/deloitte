package com.deloitte.solution.logincontroller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView loginController(@RequestParam(value="username", required=false)String userID, @RequestParam(value="password", required=false)String passWord) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login/login");
		try {
			if(userID != null && passWord != null) {
				URL url = new URL("https://10/api/3.4/auth/signin");
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				
				JSONObject obj = new JSONObject();
				
				obj.put("user-name", userID);
				obj.put("password", passWord);
				obj.put("site-name", "MarketingTeam");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;
				
				while((line = br.readLine()) != null) { 
					sb.append(line);
				}
				
				JSONObject obj2 = new JSONObject(sb.toString());
				
				
				log.info("asdsadasdasdas >>>>{}",obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("UserID = " +userID);
		log.info("passWord = " + passWord);
		
		
		
		return mv;
		
	}
}
