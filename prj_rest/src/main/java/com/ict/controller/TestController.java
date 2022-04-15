package com.ict.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onetwo")
public class TestController {
	
	@RequestMapping("/three")
	public String sayHello() {
		return "Hello Hello";
	}
	
	@RequestMapping("/saybye")
	public String sayBye() {
		return "bye bye";
	}

}
