<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

	<div>
		<input type="date" id="date">
		<button type="button" id="submit">확인</button>
		<div id="resultDiv"></div>
	</div>
	
	<!-- ajax 요청을 넣기 위해 jquery CDN을 넣어준다. -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	
	<script>
	
	
	let requestUrl ="";
	
	// ■ 버튼 클릭시				
	$("#submit").on("click", function(){
		
		// ● 모던 js(es6)에서는 var대신 let(변수)과 const(상수)를 사용해 변수를 사용합니다.
			// 장점은 변수 호이스팅(지역 간선문제)에서 자유로운 자료를 사용할 수 있다는 것입니다.
			let movieData ="";
		
			// ● 접속용 키는 변동될 일이 없으므로 const로 선언해 상수처리합니다.
			const apiKey ="f5eef3421c602c6cb7ea224104795888";
			
			// ● 날짜는 변동될 수 있으므로 let으로 저장합니다.
			//let targetDt ="20091123";
			let targetDt ="";
				
				// 받아온 날짜 형식 2019-10-29 -> 날짜 포맷 안 바꾸고 걍 replace로 제거해버림
				date = $(this).siblings("#date").val();
				console.log(date);
				console.log(date.replace("-","").replace("-",""));
				
				targetDt = date.replace("-","").replace("-","");
				console.log("onClick 후 -> " + targetDt);
			
			console.log("클릭했음");
		
			// ● 자바스크립트에서 포맷팅을 쓸 때는 (키워드 : 자바스크립트 문자열 포매팅)
				// 1) 백틱(`)으로 문자열을 감싸주신다음
				// 2) 포맷팅 데이터가 들어갈 자리에는 \${변수명}을 적습니다.
				// getJSON 등으로 얻어온 데이터는 포맷팅 적용이 잘 안됩니다.
				// 밑에 // 때문에 requestUril 값이 주석처리 되어서 빼버림(뺐더니 안돼서 다시 추가)
				// .jsp에서는 el과 문법이 충돌하므로 $ 앞에 \를 붙입니다.
			requestUrl = 
				`http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=\${apiKey}&targetDt=\${targetDt}`;
			
			console.log(requestUrl);
 			
			// getJSON 소환
			getData();
			
			
		});// end onClick
		
		
		function getData(){ // ※ 선생님 답이랑 다르니까 참고 ※
		
			$.getJSON(requestUrl, (data) => {
					//console.log(data);
					//console.log(data['boxOfficeResult']['dailyBoxOfficeList']);
					//console.log(data['boxOfficeResult']['dailyBoxOfficeList'][0]);
					
				// ●  화살표 함수는 (파라미터) => {실행문}; 형식으로 작성합니다.
					// 익명 함수만 화살표 함수로 만들 수 있습니다. 역시 모던 js에서 사용하는 문법입니다.
					// 화살표 함수는 this 키워드를 가지지 않으므로, 
					// 이벤트 위임 등 this를 사용하는 로직은 어쩔 수 없이 function(파라미터){실행문}; 형식을 그대로 사용해야합니다.
					let str ="";
					
					$(data['boxOfficeResult']['dailyBoxOfficeList']).each(function(){
						console.log(this);
						//str += this.movieNm + ", ";
						str += "<div>" + 
									"순위 : " + this.rank + 
									", 영화명 : "+ this.movieNm + 
									", 개봉일 : " + this.openDt +
									", 일일 관객수 : " + this.audiCnt +
									", 누적 관객수 : " + this.audiAcc +
									", 일일 상영수 : " + this.scrnCnt +
							   "</div>";
					});
					$("#resultDiv").html(str);
				
			}); // end getJSON
		
		};//end getData()		
	
	</script>

</body>
</html>