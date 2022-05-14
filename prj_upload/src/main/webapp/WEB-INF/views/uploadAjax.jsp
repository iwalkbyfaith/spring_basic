<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Upload with ajax</h1>
	
	<!-- ■ 폼이 아니고 div 내부에 넣는다 -->
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple>
	</div>
	
	<!-- ■ 폼에 들어간 버튼이 아니기 때문에 스크립트 태그 안에서 처리해주어야함 -->
	<button id="uploadBtn">Upload</button>
	
	<!-- ■ jqueryCDN 추가 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script>
		$(document).ready(function(){					// 'html코드가 로드되고 자바스크립트가 실행되라'는 뜻
			
			// 1. 파일 확장자를 거르기 위한 정규 표현식 & 파일 크기 제한(~21)
			
				let regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
				let maxSize = 5242880; //5MB
				
				function checkExtension(fileName, fileSize){
					if(fileSize >= maxSize){
						alert("파일 사이즈 초과");
						return false;
					}
					if(regex.test(fileName)){
						alert("해당 종류의 파일은 업로드할 수 없습니다");
						return false;
					}
					return true;
				}
			
			
			
			
			
			// 2. ajax로 파일 업로드하기
			$("#uploadBtn").on("click", function(e){	// e : 현재 돌리고 있는 이벤트의 정보, 클릭한 대상의 정보 (안 넣어도 되긴 함)
														//     onclick은 클릭한 대상의 정보지만, 다른 것을 쓰면 '클릭한 대상의 정보'가 아닌 경우도 있다(참고)	
				
				let formData = new FormData();						// 폼 데이터를 하나 만듦(자바스크립트로 폼을 만들때 사용함)
				let inputFile = $("input[name='uploadFile']");		// 여러개의 파일을 업로드하는 input의 정보를 받음
				
				console.log(inputFile);								// inputFile을 콘솔에 찍어보면 더 많은 정보가 있지만
				let files = inputFile[0].files;						// 우리가 원하는건 input태그 내의 데이터이기 때문에 [0]을 잡는 것
																	// 그리고 [0].files를 해야 [0]내부의 원하는 정보(files)가 나온다.
				console.log(files);
				
				// 파일 데이터를 폼에 집어 넣기
				for(var i = 0; i < files.length; i++){
					
					// 위의 1.에서 추가된 checkExtension을 사용하여 이름과 사이즈 검증하기
					if(!checkExtension(files[i].name, files[i].size)){
						return false;
					}
					// 검증이 된 파일은 넣어준다.
					formData.append("uploadFile", files[i]);		// 폼 데이터 내부에 집어 넣어라
				}
				console.log("form 종료 후 formData");
				console.log(formData);
				
				$.ajax({
					url : '/uploadAjaxAction',
					processData : false,
					contentType : false,
					data : formData,
					type : 'POST',
					success : function(result){
						alert("Uploaded");
					}
				}); // ajax 끝
				
				console.log("ajax 종료");
				
			});	
		});
	</script>

</body>
</html>