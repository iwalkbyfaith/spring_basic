package com.ict.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class OracleConnectionPoolTest {
	
	/* 마이바티스 연동이 잘 되었는지 확인하는 파일*/
	
	// root-context에 있는 것들을 가져와서 쓸 수 있다.
		
		@Autowired
		private SqlSessionFactory sqlSessionFactory;

		
		@Test
		public void testMyBatis() {
			
			try(SqlSession session = sqlSessionFactory.openSession();
					Connection con = session.getConnection();){
					log.info("마이바티스 연결 시작!");
					log.info(session);
					log.info(con);
					
			}catch(Exception e) {
				fail(e.getMessage());
			}
			
			
		}

	
}
