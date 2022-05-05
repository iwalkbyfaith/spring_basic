package com.ict.test;

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

// 커넥션풀 연결은 @RunWith 어노테이션과 
// 빈 컨테이너 내부에 생성된 요소를 클래스로 가져오는 @ContextConfiguration("경로")가 같이 적혀야합니다.

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class OracleConnectionPoolTest {
	
	// 빈 컨테이너에 있는 DataSource를 주입해줌
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	// 테스트 수행시 @Test가 붙은 메서드 하나하나를 전부 실행함
	// 전부 실행하면 어떤 테스트의 결과인지 인지하기가 어려움.
	// 방법1) 그래서 보통 하나의 테스트가 끝나면 주석 처리해서 언제든 실행은 가능하지만 현재는 실행이 안되게 조치함.
		//@Test // <- 이런식으로 어노테이션 앞에만 주석처리해도(코드는 나둬도) 콘솔에 안찍힘
	// 방법2) 혹은 어노테이션 다 살려놓고 오른쪽의 JUnit 창에서 우클릭 > run을 해서 해당 코드만 실행할 수도 있음
	
	
	@Test
	public void testConnection() {
		try(Connection con = dataSource.getConnection()){
			log.info(con);
			log.info("hikariCP connection");
			System.out.println("히카리 연결 끝!");
		}catch(Exception e) {
			fail(e.getMessage());
		}
	}
	

	
	@Test
	public void testMyBatis() {
		
		try(SqlSession session = sqlSessionFactory.openSession();
				Connection con = session.getConnection();){
				System.out.println("마이바티스 연결 시작!");
				log.info(session);
				log.info(con);
				
		}catch(Exception e) {
			fail(e.getMessage());
		}
		
		
	}
	

}
