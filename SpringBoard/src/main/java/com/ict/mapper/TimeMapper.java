package com.ict.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	
	@Select("SELECT SYSDATE FROM dual")
	public String getTime();

	// 지금처럼 파라미터를 요구하지 않는 단순한 구문에서는 @Select, @Insert ... 이런 것을 쓴다.
}
