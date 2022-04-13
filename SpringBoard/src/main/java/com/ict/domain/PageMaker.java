package com.ict.domain;

import lombok.Data;

@Data
public class PageMaker {
	
	private int totalBoard;	 	 	// 전체 글 수
	private int startPage;	  		// 해당 그룹의 시작 페이지
	private int endPage;	  		// 해당 그룹의 끝 페이지
	private boolean prev;	  		// 이전 페이지
	private boolean next;	  		// 다음 페이지
	
	// 만약 페이지 하단 버튼 개수를 유동적으로 가져가고 싶은 경우는 displayPageNum을 선언한다.	
	private int displayPageNum;	    // nav 버튼을 몇 개 출력할지
	
	
	// 아래에 메서드에서 사용하기 위해서 Criteria가 필요하다
		// 현재 조회중인 페이지 정보 + 한 페이지에 깔리는 글 수 받아와야함.
	//private Criteria cri;
	
	// 04.13 검색창 만들기 위해 변경 Criteria -> SearchCriteria(검색 타입, 검색 키워드)
	private SearchCriteria cri;
	
	
	// ■ 필요한 모든 사항을 계산해주는 메서드 (시작버튼, 끝버튼, 버튼 몇 개 깔지 ... )
	public void calcData() {
		
		// ● 네비 버튼을 몇 개 출력하고 싶은지 결정
		this.displayPageNum = 10;
		
		
		// ● 명목상의 끝나는 지점 구하기 (현재 페이지(cri.getPageNum())을 근거로 페이지 그룹 중 끝나는 페이지를 구함)
			// 1. 현재 페이지 / (실수)displayPageNum 를 올림해줌 
			// 2. 1의 결과에 displayPageNum을 곱해줌
			// 3. 2의 결과를 인트int로 바꿔줌
		this.endPage = (int)(Math.ceil(cri.getPageNum() / (double)displayPageNum ) * displayPageNum);
		
		
		// ● 시작 지점 구하기 ( 끝나는 페이지를 토대로 페이지 그룹의 시작 페이지를 구함)
		this.startPage = (endPage - displayPageNum) + 1;
		
		
		// ● 찐찐 끝나는 지점 구하기 ( 페이지가 276에서 끝나는 경우는 endPage를 280까지 쓸 필요가 없음 )
			// 1. 전체 글 개수를 '한 페이지에 깔고 싶은 수' 만큼 나눔 ( totalBoard/ (double)cri.getNumber() ) => 1133 /10.0 = 113.3
			// 2. 1의 결과를 올림해준다. => 114
			// 3. endPage가 tempEndPage보다 크다면 tempEndPage로 고정시킨다. => 120 vs 114 => 114
		
			// 위의 endPage는 명목상의(단순 그룹 계산으로) 끝나는 페이지이기 때문에 실질적인 글 개수를 통해 보정해줘야함.
		int tempEndPage = (int)(Math.ceil(totalBoard / (double)cri.getNumber()));
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		
		// ● prev 이전 페이지가 있는지 확인하기 (삼항 연산자로 처리해준다)
			// prev는 startPage가 1인 경우에만 비활성화.
		prev = (startPage == 1 ? false : true );
		
		
		// ● next 이후 페이지가 있는지 확인하기
			// 여태까지 출력한 페이지에 속한 글 개수보다 DB내전체 글이 더 많은 경우에 활성화
			// 전체 글 수 101개일때 보고 있는 글이 100번이라면 next가 필요한 식
		next = (endPage * cri.getNumber() >= totalBoard ? false : true );
		
	}
	
	
	
	// ■ 전체 글 개수를 집어 넣을 때 다 같이 계산하게 만들기
	public void setTotalBoard(int totalBoard) {
		this.totalBoard = totalBoard;
		
		// ● calcData() 실행
		calcData(); // prev, next, endPage, startPage를 다 구해버리는 것 
	}
	

}
