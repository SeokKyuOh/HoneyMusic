<!-- 멜론차트 -->
<%@page import="com.sist.member.dao.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>멜론차트</title>
</head>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>

<script type="text/javascript">
function backMain(){
	location.href="main.do";	
}
</script>
<input type=submit class="btn btn-theme" value="돌아가기" id="bt_send" onclick="backMain()">
<h2>${keyword }</h2>
<table class="table table-hover">
	<thead>
		<tr>
			<th width=10%>번호</th>
			<th width=30%>곡명</th>
			<th width=30%>아티스트</th>
			<th width=30%>앨범정보</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="vo" items="${list }" varStatus="s">
			<tr>
				<td>${s.count }</td>
				<td>${vo.title }</td>
				<td>${vo.artist }</td>
				<td>${vo.albumname }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>