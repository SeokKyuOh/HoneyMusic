<!-- 공지사항 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<title>공지</title>
</head>
<body>
	<section id="contentSection">
	<div class="row">
		<div class="col-lg-8 col-md-8 col-sm-8">
			<div class="left_content">
				<div class="contact_area">
					<h2>1:1문의하기</h2>
					<p>공지 내용구성</p>
					<form 
						class="contact_form">


						<input class="form-control" type="text" name="question_id"
							value="${vo.question_id }">

						<%-- <input type=hidden name=question_id value="${q.id} "> --%>
						 <input class="form-control" type="text" name="question_title" value="${vo.question_title }">
						  <textarea
							class="form-control" cols="30" rows="10" name="question_content" >${vo.question_content }</textarea>
						<input type="submit" value="글올리기"> <input type="submit"
							value="삭제하기">
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>

