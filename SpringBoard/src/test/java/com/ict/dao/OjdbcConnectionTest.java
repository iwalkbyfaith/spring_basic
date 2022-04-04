package com.ict.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class OjdbcConnectionTest {
	
	static {
		try {
			// 연동하려는 DB가 오라클임을 알려주는 코드 (저번에는 mysql이었음)
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testConnection() {
		try(Connection con = 
				DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521/XEPDB1",
						"mytest",
						"mytest"
						)){
			log.info(con);
			System.out.println(con);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}

}
