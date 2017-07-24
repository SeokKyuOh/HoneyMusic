<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

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
		<h3>글쓰기</h3>
		<form method=post action="board_insert_ok.do" enctype="multipart/form-data">
			<input type="hidden" name="boardType_id" value="1">
			<table id="table_content" width=1000>
				<tr>									

				<th>작성자</th>						

				<td><input type="text" name="board_writer" size="20" /></td>

			</tr>
			<tr>

				<th>제목</th>

				<td><input type="text" name="board_title" size="60" /></td>

			</tr>


		<tr>

				<th height="500px">내용</th>

				<td><textarea rows="30" cols="80" name="board_content"></textarea></td>

			</tr>

				<tr>
					<th>첨부파일</th>
					<td>
						<table>
							<tr>
								<td>
									<input type=button id="addBtn" value="추가">
									<input type=button id="removeBtn" value="삭제">
								</td>
							</tr>
						</table>
						<table id="fileView">
						</table>
					</td>
				</tr>




				<tr>
					<td colspan="2" align="right">
						<input type=submit value="글쓰기">
						<input type=button value="취소" onClick="javascript:history.back()"> <!-- 공백이 없으면 따옴표 안 붙여도 되지만 있으면 따옴표 반드시 붙여야해 -->
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>