<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	<h2>앨범 정보</h2>
	<div>
		<div>
			<h3>앨범 기본정보</h3>
			<div style="float: left; width: 20%; padding: 10px;">
				<span><img
						 width="216"
						height="216"
						src="http://211.238.142.109:8080/album_img/${vo.album_art }.jpg"/></span>
			</div>

			<div>
				<p class="albumname">
					<strong class="none">앨범명</strong> <span>[${ vo.album_type}]</span>${vo.album_name }
				</p>
				<dl>
					<dt>아티스트</dt>
					<dd>
						<span>${vo.album_artist }</span>
					</dd>
					<dt>발매일</dt>
					<dd>
					<span>
								<fmt:formatDate value="${vo.album_release }" 
            						pattern="yyyy.MM.dd" /></span>
					</dd>
					<dt>발매사</dt>
					<dd>${vo.album_agency }</dd>
					<dt>기획사</dt>
					<dd>${vo.album_dist }</dd>
					<dt>장르</dt>
					<dd>${vo.genre_name }</dd>
				</dl>
		</div>
	</div>
</div>	

<input type=button class="btn btn-theme" value="선택 재생" id="bt_send">

<table class="table table-hover">
	<thead>
		<tr>
			<th width=10%><input type="checkbox" value="" id="allCheck">
			</th>
			<th width=20%>순위</th>
			<th width=60%>곡명</th>
			<th width=20%>아티스트</th>
		</tr>
	</thead>
	<tbody>
		<%
				int i = 1;
			%>
		<c:forEach var="vo" items="${list }">
			<tr>
				<td><input type="checkbox" music_id="${vo.music_id }"
					album_id="${vo.album_id}" name="checkbox_name"></td>
				<td><span><%=i%></span><span>위</span></td>
				<td> 
					<a
					href="player_playlist_id.do?member_id=${sessionScope.membervo.member_id}&musics=${vo.music_id}"> 
						<img src="<c:url value="/resources/img/play.png"/>"
						style="width: 20px; height: 20px"></a>
					<a
					href="player_playlist_id.do?member_id=${sessionScope.membervo.member_id}&musics=${vo.music_id}">	
						<img src="<c:url value="/resources/img/add.png"/>"
						style="width: 20px; height: 20px"></a> 
				
				<a href="player_playlist_id.do?member_id=${sessionScope.membervo.member_id}&musics=${vo.music_id}">
				${vo.music_name }</a>
				<td>${vo.music_artist }</td>
			</tr>
			<%
					i++;
				%>
		</c:forEach>
	</tbody>
</table>

</body>
</html>