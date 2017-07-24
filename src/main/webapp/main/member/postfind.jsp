<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style/table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#findBtn').click(function(){
		var dong=$('#dong').val();
		if(dong.trim()==""){
			alert("동/읍/면을 입력하세요");
			return;
		}
		$.ajax({
			type:'POST',
			url:'postfind_result.do',
			data:{'dong':dong},
			success:function(response){
				$('#post_view').html(response);
			}			
		});
	});
	
});
</script>
</head>
<body>
	<div style="height:30px"></div>		<!-- 위로 너무 붙으니 좀 내리기 위해 만들었음 -->
	<center>
		<table id="table_content" width=430>
			<tr>
				<td>
					입력 : <input type=text name=dong size=12 id="dong">
					<input type=button value="검색" id="findBtn">
				</td>
			</tr>
		</table>
			<div id="post_view"></div>
	</center>
</body>
</html>