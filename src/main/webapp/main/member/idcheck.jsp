<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="assets/css/table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#idBtn').click(function(){
		var member_nick=$('#member_nick').val();		//입력된 값 가져오는 것 val
		if(member_nick==""){
			alert("ID를 입력하세요");
			$('#member_nick').focus();
			return;
		}
				
		$.ajax({
			type:"POST",
			url:"idcheck_result.do",
			data:{"member_nick":member_nick},
			success:function(response){
				$('#nick_view').html(response);
				
			}
		});
	});
	
});
</script>
</head>
<body>
	<center>
		<div style="height:30px"></div>
		<table id="table_content" width=300>
			<tr>
				<td align=left>
					ID:<input type=text name=member_nick size=12 id="member_nick">
					<input type=button value="아이디체크" id="idBtn">
				</td>
			</tr>
		</table>
		<div id="nick_view"></div>
	</center>
</body>
</html>














