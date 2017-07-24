<!-- 공지사항 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<title>공지</title>
<script type="text/javascript">
$(function(){
	$('#delBtn').click(function(){
		alert("삭제 눌림");
		var question_id=$('#question_id').attr("value");
		var nick = $('#nick').val();
		var id = $('#id').val();
		alert(question_id + ", " + nick + ", " + id);
		
		var url = "notice_delete.do?question_id="+question_id + "&nick="+nick+"&id="+id;
		$(location).attr('href',url);
		
		/* var json={"question_id":question_id};
		$.ajax({
			type:'POST',
			url:'notice_delete.do',
			data:json,
			success:function(response){
				
				if(response.trim()==1){
					parent.location.href="mypage.do?nick="+nick+"&id="+id;

				}
				
			}
			
			
		}); */
	});
});
</script>
</head>
<body>
	<section id="contentSection">
	<div class="row">
		<div class="col-lg-8 col-md-8 col-sm-8">
			<div class="left_content">
				<div class="contact_area">
					<h2>1:1문의하기</h2>
					<p>공지 내용구성</p>
					<form class="contact_form" method="post" action="notice_update.do"  enctype="multipart/form-data">
						<input class="form-control" type="text" name="question_id" id="question_id"
							value="${vo.question_id }">

						  <input type=hidden name=nick value="${ nick}" id="nick">
						  <input type=hidden name=id value=" ${id }" id="id"> 
						 
						 <input class="form-control" type="text" name="question_title" value="${vo.question_title }">
						  <textarea
							class="form-control" cols="30" rows="10" name="question_content" >${vo.question_content }</textarea>
						<input type=submit value="수정하기" class="btn btn-theme">
						<input type="button" value="삭제하기" class="btn btn-theme" id="delBtn">
						<input type="button" value="취소하기" class="btn btn-theme" onClick="javascript:history.back()">
					</form>
				</div>
			</div>
		</div>
	</div>
	</section>
</body>
</html>

