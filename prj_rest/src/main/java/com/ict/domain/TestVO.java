package com.ict.domain;

import lombok.Data;

@Data // @Data는 순환참조 문제가 있음(인지 해두기)
public class TestVO {
	
	// @Data는 다 만드는 것이고, 
	// @Getter, @Setter를 사용하면 게터만/세터만 필요한 것만 만들 수 있음
	// @AllArgsConstructor : 생성자인데 모든 멤버변수를 파라미터로 받는 생성자 만드는 것
	// @NoArgsConstructor : 생성자인데 아무것도 입력 안 받아도 생성할 수 있음.
	// @ToString : toString 자동으로 만들어줌
	// @Getter, @Setter, @ToString 이렇게 세 개를 사용하는 경우도 있음.
	// 지금 당장은 순환참조 문제가 발생하지 않는 코드를 짜고 있기 때문에 상관없는 것!

	private Integer mno;
	private String name;
	private Integer age;
	
}
