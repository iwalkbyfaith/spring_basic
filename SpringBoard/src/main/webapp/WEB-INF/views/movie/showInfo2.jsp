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
		<div id="data"></div>
	</div>
	
	<!-- ajax 요청을 넣기 위해 jquery CDN을 넣어준다. -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	
	<script>
	
		// ■ 모던 js(es6)에서는 var대신 let(변수)과 const(상수)를 사용해 변수를 사용합니다.
			// 장점은 변수 호이스팅(지역 간선문제)에서 자유로운 자료를 사용할 수 있다는 것입니다.
			let movieData ="";
		
			// ● 접속용 키는 변동될 일이 없으므로 const로 선언해 상수처리합니다.
			const apiKey ="f5eef3421c602c6cb7ea224104795888";
			
			// ● 날짜는 변동될 수 있으므로 let으로 저장합니다.
			let targetDt ="20120101";
		
		
		// ■ 버튼 클릭시				
		$("#submit").on("click", function(){
			
			date = $(this).siblings("#date").val();
			console.log(date);
			
			console.log("클릭했음");
			
			// ●  화살표 함수는 (파라미터) => {실행문}; 형식으로 작성합니다.
				// 익명 함수만 화살표 함수로 만들 수 있습니다. 역시 모던 js에서 사용하는 문법입니다.
				// 화살표 함수는 this 키워드를 가지지 않으므로, 
				// 이벤트 위임 등 this를 사용하는 로직은 어쩔 수 없이 function(파라미터){실행문}; 형식을 그대로 사용해야합니다.
 			$.getJSON("http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20120101", function(data){
 				console.log(data);
 				console.log(data['boxOfficeResult']);
 				console.log(data['boxOfficeResult']['dailyBoxOfficeList']);
 				console.log(data['boxOfficeResult']['dailyBoxOfficeList'][0]);
 				console.log(data['boxOfficeResult']['dailyBoxOfficeList'][1]);
 				
 				let result = data['boxOfficeResult']['dailyBoxOfficeList'][0];
 				
 				console.log("▼");
 				console.log(result);
 				console.log(result.movieNm);
 				

 			});
			
		});// end onClick
		
		
	
	</script>

</body>
</html>