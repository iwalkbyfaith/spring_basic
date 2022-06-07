package com.ict.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ict.domain.MovieVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/movie")
public class MovieController {

	
	@GetMapping("/formInsert")
	public String insertForm() {
		return "movie/showInfo";
	}
	
	@GetMapping("/formInsert2")
	public String insertForm2() {
		return "movie/showInfo2";
	}


	
	@GetMapping(value="/getData", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseBody
	public ResponseEntity<List<MovieVO>> getData(){
		
		ResponseEntity<List<MovieVO>> entity = null;
		
		try {
			//entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace(); // 콘솔에 에러가 찍힘
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	
	
	
}
