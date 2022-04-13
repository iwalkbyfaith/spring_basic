package com.ict.domain;

import lombok.Data;

@Data
public class SearchCriteria extends Criteria{
	
	private String searchType;  // 검색 타입
	private String keyword;	    // 검색 키워드

}
