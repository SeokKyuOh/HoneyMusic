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
	$('#okBtn').click(function(){
		parent.join_frm.member_nick.value=$('#member_nick').val();			//parent는 현재 join.jsp에서 띄운것이기 때문에 걔다.
		parent.Shadowbox.close();
	});
});
</script>
</head>
<body>
	<center>
		<table id="table_content" width=300>
			<c:if test="${count==0 }">		<!-- id 중복체크 안걸렸을때 -->
				<tr>
					<td align=center>
						<font color=yellow>
							${member_nick }는(은) 사용이 가능합니다.
						</font>
					</td>
				</tr>
				<tr>
					<td align=center>
						<input type=button value="확인" id="okBtn">
					</td>
				</tr>
			</c:if>
			<c:if test="${count!=0 }">		<!-- id 중복체크 걸렸을때 -->
				<tr>
					<td align=cent>
						<font color=blue>
							${member_nick }는(은) 이미 사용중입니다.
						</font>
					</td>
				</tr>
			</c:if>
		</table>
	</center>
</body>
</html>