<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

	<!-- ■ 05.19 jqueryCDN 추가 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- ■ 05.19 첨부파일을 위한 스타일 태그 추가 -->
	<style>
		.uploadResult{
			width:100%;
			background-color : whitesmoke;
		}
		.uploadResult ul{
			display:flex;
			flex-flow:row;
			justify-content:center;
			align-items: center;
		}
		.uploadResult ul li{
			list-style : none;
			padding: 10px;
		}
		.uploadResult ul li img{
			width:20px;
		}
	</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		</br><h1>게시글 입력하기</h1><br/>
		 <form action="/board/boardInsert" method="post">
		 	<div class="row">
		 		<div class="col-md-8">
 				 	<input type="text" name="title" size=38 placeholder="제목" class="form-control"/><br/>
			 	</div>
			 	<div class="col-md-4">
			 		<input type="text" name="writer" placeholder="글쓴이" class="form-control"/><br/><br/>
			 	</div>
			 	<div>
			 		<textarea rows="15" cols="40" name="content" class="form-control">내용</textarea><br/>
			 	</div>				
			 	<div class="col-md-10">
			 		<input type="reset" class="btn btn-danger"/>
			 	</div>
			 	<div class="col-md-1">
			 		<input type="submit" id="submitBtn" value="작성 완료" class="btn btn-primary"/> <!-- 05.19 id 추가 -->
			 	</div>
			 	
			 	<!-- ■ 05.19 토큰 넣어주기 -->
			 	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		 	</div>
		 </form><!-- end form -->
		 
		 
		<hr/>
		<h3> ▶ 첨부파일 영역</h3> 
		<!-- ■ 05.19 업로드 추가1) 폼 -->
	 	<div class="uploadDiv">
			<input type="file" name="uploadFile" multiple>
		</div>
		<!-- ■ 05.19 업로드 추가2)업로드된 파일 목록 보여주기 -->
		<div class="uploadResult">
			<ul>
				<!-- 업로드된 파일이 들어갈 자리 -->
			</ul>
		</div> 
		<!-- ■ 05.19 업로드 추가3)폼에 들어간 버튼이 아니기 때문에 스크립트 태그 안에서 처리해주어야함 -->
		<button id="uploadBtn">Upload</button>
		 
		 <hr/>
		 
		 <a href="/board/boardList" class="btn btn-warning">돌아가기</a>
		 
	 </div><!-- end container -->
	 
	 
	 
	 
	 <!-- ■ 05.19 업로드 로직 추가 -->
		
		
		<script>
		
		// ■ 토큰 추가 ( 스프링 시큐리티가 적용되어 있으면 넣어야 함 )
		var csrfHeaderName = "${_csrf.headerName}"
		var csrfTokenValue="${_csrf.token}"
		
		
			$(document).ready(function(){					// 'html코드가 로드되고 자바스크립트가 실행되라'는 뜻
				
				// ■ 1. 파일 확장자를 거르기 위한 정규 표현식 & 파일 크기 제한(~21)
				
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
					
				// 05.17 업로드가 끝난 다음에 파일 선택이 초기화 되도록 고치기	(교안13의 37)
					// 깊은 복사 : 힙 내부에서 아예 새롭게 복사
						// 1)업로드 직전에 .uploadDiv에서 빈 상태의 폼을 복사(첨부가 안 된 상태)
						// 2) 추후 첨부 완료 후에 '안 된 시점'의 .uploadDiv로 덮어씌운다.
							// 들어간 것을 삭제하는 것은 어렵기 때문에, 
							// 적재가 안 된 시점에 복사를 하고 업로드가 끝나면 빈 상태의 적재창을 덮어씌우는 것
							// 첨부를 했을 때 비우는 명령어가 없음. 그래서 빈 창을 복사해서 덮어씌우는 방식을 적용하는 것
					// 얕은 복사 : 주소값만 복사
				let cloneObj = $(".uploadDiv").clone();
		
				
				// ■ 2. ajax로 파일 업로드하기
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
						beforeSend : function(xhr) {
						 xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
						},
						dataType : 'json',				// 05.17 추가 (교안13의 36)
						success : function(result){
							alert("Uploaded");			
							console.log(result);		// 05.17 추가
							
							// ★ 05.17 화면상에 어떤 파일이 첨부 되었는지 보게 해주는
							showUploadedFile(result);
							
							// ★ 05.17 파일 업로드에 성공하면, 위에서 깊은 복사 해 둔 .uploadDiv로 덮어씌워서 첨부파일이 없는 상태로 돌려 놓는다.
							$(".uploadDiv").html(cloneObj.html());
								// 파일 첨부창을 일종의 '장전 상태'라고 생각해야함
								// 업로드 버튼을 누르더라도, 위와 같은 코드를 적어서 빈 상태로 덮어씌우지 않으면 무제한으로 업로드하게 되는 것
						}
					}); // ajax 끝
					
					console.log("ajax 종료");
					
				});	// onclick uploadBtn
				
				
				// ■ 05.17 업로드된 사진 정보를 uploadResult 내부에 저장할 수 있도록 하는 함수
				
				let uploadResult = $(".uploadResult ul");
				
				function showUploadedFile(uploadResultArr){
					let str = "";
					
					$(uploadResultArr).each(function(i, obj){	 // obj만 주면 -> 요소를 하나하나 가져오는 것으로 끝나고
																 // i와 obj를 주면 -> 첫번째 바퀴에 0, 두번째 바퀴에 1.. 인덱스 번호까지 i에 받아준다.
						// ● 업로드된 파일이 이미지가 아니라면 -> 파일 이미지
						//               이미지라면      -> 썸네일 + 파일 이름 출력
						//   + 
						if(!obj.fileType){	// 05.19 !obj.image -> !obj.fileType(VO에서 이미지 여부를 확인하는 멤버변수 이름을 변경했으므로)
							
							// 디버깅
							console.log("파일 이름 -> " + obj.fileName);
							console.log("이미지 파일이 아님");
							
							// * 05.18 일반 파일에 대응하는 이미지가 올라오면 클릭시 다운로드 되도록 (컨트롤러 /download 생성)
							//         => 첨부파일 다운, 첨부파일 삭제
							let fileCallPath = encodeURIComponent(
													obj.uploadPath + "/" + // 썸네일이 없기 때문에 s_를 뺀다
													obj.uuid + "_" +
													obj.fileName);
							
							// * 05.19 str 코드 변경(교안13의 64)
							str += "<li " +
								   "data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.fileType + "'>" +	
										 "<a href='/download?fileName=" + fileCallPath + "'>" + 
								 			 "<img src='/resources/attachment_87543.png'>" + 
								         	 obj.fileName + 
								         "</a>" +
								         "<span data-file=\'" + fileCallPath + "\' data-type='file'> X </span>" +
								   "</li>";
								   
						}else{
							//str += "<li>" + obj.fileName + "</li>";
							
							// ＊ 05.18 위의 코드 주석처리하고 아래 str 코드로 코드 수정(교안13의 47)
							// 		   썸네일 이미지 or 이름을 클릭하면 다운로드 되도록
								
								// 파일을 저장해주는 양식 그대로! 폴더 주소를 입력함
									// uploadPath : 몇 월 며칠인지
									// /s_ : 썸네일 파일 앞에는 이거 붙음
									// uuid : 파일마다 다름
									// fileName : 찐 파일명
									
								// 썸네일 보여주는 변수 (첨부파일 상태 보여주기)
								let fileCallPath = encodeURIComponent(
														obj.uploadPath + "/s_" + 
														obj.uuid + "_" + 
														obj.fileName);
							
								// 다운로드 받을때는 썸네일을 받으면 안되므로 변수 하나 더 생성 (다운로드는 오리지널로 받아야하므로)
								let fileCallPathOriginal = encodeURIComponent(
														obj.uploadPath + "/" + 
														obj.uuid + "_" + 
														obj.fileName);
								
								// * 05.19 디버깅
								console.log("이미지 파일 경로 : " + fileCallPath)
								
								// * 05.19 str 코드 수정
								// 원래코드는 썸네일만 나오는건데 나는 파일 이름까지 나오게 추가했음
								str += "<li " +
									   "data-path='" + obj.uploadPath + "' data-uuid='" + obj.uuid + "' data-filename='" + obj.fileName + "' data-type='" + obj.fileType + "'>" +
									   		"<a href='/download?fileName=" + fileCallPathOriginal + "'>" +
												"<img src='/display?fileName=" + fileCallPath + "'>"+ 
												obj.fileName + 
											"</a>" +
											"<span data-file=\'" + fileCallPath + "\' data-type='image'> X </span>" +
									   "</li>";
						}
						console.log("인덱스번호 -> " + i);
						console.log("실제 자료 ▼ ");
						console.log(obj);
						
						 
							
					});
					// str에 적재된 li들을 ul 태그 사이에 넣는다.
					uploadResult.append(str);
					
					
				} // showUploadedFile 종료
				
				
				
				// ■ 05.17 ajax 삭제 로직 (x 가 몇개가 들어올지 모르기때문에 '이벤트 위임'을 건다.)
				$(".uploadResult").on("click", "span", function(e){
					
					// 타겟 파일이 무엇인지 확인
					let targetFile = $(this).data("file");
					
					// 이미지인 경우 썸네일과 오리지널 파일을 없애야하므로 타입 확인을 해준다.
					let type = $(this).data("type");
					
					let targetLi = $(this).closest("li");
					
					$.ajax({
						
						url : '/deleteFile',
						data : {fileName : targetFile, type:type},
						beforeSend : function(xhr) {
						 xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
						},
						dataType : 'text',
						type : 'POST',
						success : function(result){
							alert(result);
							// 화면상에서도 삭제를 해야하므로
							targetLi.remove();
						}
						
					}); // ajax 종료
				}); // end click span
				
				
			
			
			
				// ■ 05.19 제출 버튼 누를 경우, 첨부파일 정보를 폼에 추가해서 전달하는 코드
				$("#submitBtn").on("click", function(e){
					
					// 1. 제출 버튼을 눌렀을 대 바로 작동하지 않도록 기능 막기
					e.preventDefault();
					
					// 2. var formObj = $("form");로 위쪽에 있던 폼 태그를 가져온다.
					let formObj = $("form");
					
					// 3. 05.19 수업에서는 첨부파일 내에 들어있던 이미지 정보를 콘솔에 찍기만하고 종료하고 내일 수업에 DB에 넣는 부분까지 진행합니다.
					$(".uploadResult ul li").each(function(i, obj){	// li 태그를 each로 받음
						
						// 3.1 제출버튼('작성완료')을 눌렀을때, 첨부파일에 대해 인식을 하는지 보기 위해 콘솔에 찍음
						console.log($(obj));
					});
				
				});// end click #submitBtn
			
			
			
			
			
			
			}); // $(document).ready 종료
			
			
			
			
			
			
			
		</script>
	 
</body>
</html>