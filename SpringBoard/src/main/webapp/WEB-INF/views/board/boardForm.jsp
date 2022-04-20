<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
			 		<input type="submit" value="작성 완료" class="btn btn-primary"/>
			 	</div>
		 	</div>
		 </form>
		 <br/>
		 <a href="/board/boardList" class="btn btn-warning">돌아가기</a>
	 </div>
	 
</body>
</html>