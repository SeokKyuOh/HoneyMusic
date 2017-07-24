<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section id="contentSection">
	<div class="center_content" style="width: 80%">
		<table class="table table-hover">
			<thead>
				<tr>
					<th colspan="6">앨범(<span style="color: red">${album_list_size }</span>)
					</th>
				</tr>
			</thead>
		</table>

		<c:forEach var="album_list" items="${album_list }">
				<div style="width: 500px; height: 150px ; float:left">
					<div style="width: 150px; height: 150px; float: left" >
						<img src="http://211.238.142.109:8080/album_img/${album_list.album_art }.jpg" width="140" height="140">
					</div>
					<div style="width: 350px; height: 150px; float: left" >
						<br><span style="font-size: 14px">&nbsp;&nbsp;[${album_list.album_type }]</span><br>
						<span style="font-size: 14px">&nbsp;${album_list.album_name }</span><br>
						<span style="font-size: 14px">&nbsp;${album_list.album_artist }</span><br>
						<span style="font-size: 14px">&nbsp;발매일 : <fmt:formatDate value="${album_list.album_release }" pattern="yyyy-MM-dd"/></span>
					</div>
				</div>
				<div style="width: 50px; height: 150px float:left"></div>
			</c:forEach>
		<div style="width: 100%; height:20px"></div>
	</div>
	</section>

</body>
</html>