package com.ict.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleServiceTests {
	
	@Autowired
	private ISampleService service;
	
	// ■ 맨 처음 잘 되나 실행
	//@Test
	public void testClass() {
		log.info(service);
		log.info(service.getClass().getName());
	}
	
	// ■ doAdd() 메서드 정상 작동
	@Test
	public void testAdd() throws Exception{
		log.info(service.doAdd("123", "456"));
	}
	
	// ■ doAdd() 메서드 예외 발생
	//@Test
	public void testAdd2() throws Exception{
		log.info(service.doAdd("123", "1231231A"));
	}
	

}
