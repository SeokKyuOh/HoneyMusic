<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style/table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script><!-- 라이브러리 로드 -->
<script type="text/javascript">
/*
 	jquery : javascript 라이브러리 (DOMSCRIPT)
	====
		DOM => 태그를 제어하는 프로그램
		1)selector - 태그 갖고올 때 쓰는것
 
 */
 var fileIndex=0;
 $(function(){
 	$('#addBtn').click(function(){
 		$('#fileView').append(
 		   '<tr id=f'+fileIndex+'>'
 		  +'<td width=20%>파일'+(fileIndex+1)+'</td>'
 		  +'<td width=80%><input type=file name=upload['+fileIndex+'] size=25></td>'
 		  +'</tr>'
 		);
 		fileIndex=fileIndex+1;
 	});
     $('#removeBtn').click(function(){
     	if(fileIndex>0)
     	{
 		  $('#f'+(fileIndex-1)).remove();
 		  fileIndex=fileIndex-1;
     	}
 	});
 });
 
 
</script>
</head>
<body>
	<center>
		<h3>수정하기</h3>
		<form method=post action="board_update_ok.do" enctype="multipart/form-data">
			<table id="table_content" width=600>
				<tr>
					<td width=15%>이름</td>
					<td width=85%>
						<input type="text" name="board_writer" size=12 value="${vo.board_writer }">
						<input type=hidden name=board_id value="${vo.board_id }">
						
					</td>
				</tr>
				<tr>
					<td width=15%>제목</td>
					<td width=85%>
						<input type="text" name="board_title" size=50 value="${vo.board_title }">
					</td>
				</tr>
				<tr>
					<td width=15%>내용</td>
					<td width=85%>
						<textarea rows="10" cols="55" name="board_content">${vo.board_content }</textarea>
					</td>
				</tr>
<!-- 				<tr>
					<td width=15%>비밀번호</td>
					<td width=85%>
						<input type="password" name=pwd size=10>
					</td>
				</tr>
 -->				<tr>
					<td colspan="2" align="center">
						<input type=submit value="수정">
						<input type=button value="취소" onClick="javascript:history.back()"> <!-- 공백이 없으면 따옴표 안 붙여도 되지만 있으면 따옴표 반드시 붙여야해 -->
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>