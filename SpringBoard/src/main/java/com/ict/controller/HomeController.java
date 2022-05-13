package com.ict.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
		// ■ No mapping found for HTTP request with URI [/favicon.ico] 에러가 나서 추가한 코드
			@RequestMapping(value = "/favicon.ico", method = RequestMethod.GET)
		
			public void favicon( HttpServletRequest request, HttpServletResponse reponse ) {
		
				try {
				  reponse.sendRedirect("/resources/favicon.ico");	
				} catch (IOException e) {	
				  e.printStackTrace();	
				}
			}
	
	
	@GetMapping("/test")
	public void agaxTest() {
		
	}
	
	@GetMapping("/test1")
	public void agaxTest1() {
		
	}
	
	@GetMapping("/test2")
	public void agaxOnClickReplyTest() {
		
	}
	
	@GetMapping("/test3")
	public void agaxTest3() {
		
	}	
	
	// ■ 댓글 등록 화면 테스트
	@GetMapping("/insertTest")
	public void insertTest() {
		
	}
	
	
}
