<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>검색결과</title>
</head>
<body>
	<section id="contentSection">
		<div class="center_content" style="width:80%">
			<table class="table table-hover">
				<thead>
					<tr>
						<th colspan="5">아티스트명으로 검색(<span style="color:red">${artist_list_size }</span>)</th>
					</tr>
					<tr>
						<th>NO</th>
						<th>앨범</th>
						<th>곡명</th>
						<th>아티스트</th>
						<th>앨범정보</th>
					</tr>
				</thead>
				<tbody>
					<% int i = 1; %>
					<c:if test="${artist_list_size>=10}" >
					<c:forEach var="artist_list" items="${artist_list }" begin="0" end="9" step="1">
						<tr>
							<td><%=i %></td>
							<td>
								<img src="http://211.238.142.109:8080/album_img/${artist_list.album_art }.jpg" width="50" height="50">
								<input type="image" id="bt_play" music_id="${artist_list.music_id} " src="<c:url value="/resources/img/play.png"/>" style="width: 20px; height: 20px">
								<input type="image"	id="bt_add" src="<c:url value="/resources/img/add.png"/>" style="width: 20px; height: 20px"> 
							</td>
							<td>${artist_list.music_name }</td>
							<td>${artist_list.music_artist }</td>
							<td>${artist_list.album_name }</td>
						</tr>
						<% i++; %>
					</c:forEach>
					</c:if>
					<c:if test="${artist_list_size<10}" >
					<c:forEach var="artist_list" items="${artist_list }">
						<tr>
							<td><%=i %></td>
							<td>
								<img src="http://211.238.142.109:8080/album_img/${artist_list.album_art }.jpg" width="50" height="50">
								<input type="image" id="bt_play" music_id="${artist_list.music_id} " src="<c:url value="/resources/img/play.png"/>" style="width: 20px; height: 20px">
								<input type="image"	id="bt_add" src="<c:url value="/resources/img/add.png"/>" style="width: 20px; height: 20px"> 
							</td>
							<td>${artist_list.music_name }</td>
							<td>${artist_list.music_artist }</td>
							<td>${artist_list.album_name }</td>
						</tr>
						<% i++; %>
					</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div style="text-align:right"><a href="search_artist.do" style="color: blue; font: bold;">아티스트 검색 결과 더보기</a></div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th colspan="5">곡명으로 검색(<span style="color:red">${song_list_size }</span>)</th>
					</tr>
					<tr>
						<th>NO</th>
						<th>앨범</th>
						<th>곡명</th>
						<th>아티스트</th>
						<th>앨범정보</th>
					</tr>
				</thead>
				<tbody>
					<% int j = 1; %>
					<c:if test="${song_list_size>=10}">
					<c:forEach var="song_list" items="${song_list }" begin="0" end="9" step="1">
						<tr>
							<td><%=j %></td>
							<td>
								<img src="http://211.238.142.109:8080/album_img/${song_list.album_art }.jpg" width="50" height="50">
								<input type="image" id="bt_play" music_id="${song_list.music_id} " src="<c:url value="/resources/img/play.png"/>" style="width: 20px; height: 20px">
								<input type="image"	id="bt_add" src="<c:url value="/resources/img/add.png"/>" style="width: 20px; height: 20px"> 
							</td>
							<td>${song_list.music_name }</td>
							<td>${song_list.music_artist }</td>
							<td>${song_list.album_name }</td>
						</tr>
					<% j++; %>
					</c:forEach>
					</c:if>
					
					<c:if test="${song_list_size<10}">
					<c:forEach var="song_list" items="${song_list }">
						<tr>
							<td><%=j %></td>
							<td>
								<img src="http://211.238.142.109:8080/album_img/${song_list.album_art }.jpg" width="50" height="50">
								<input type="image" id="bt_play" music_id="${song_list.music_id} " src="<c:url value="/resources/img/play.png"/>" style="width: 20px; height: 20px">
								<input type="image"	id="bt_add" src="<c:url value="/resources/img/add.png"/>" style="width: 20px; height: 20px"> 
							</td>
							<td>${song_list.music_name }</td>
							<td>${song_list.music_artist }</td>
							<td>${song_list.album_name }</td>
						</tr>
					<% j++; %>
					</c:forEach>
					</c:if>
					
				</tbody>
			</table>
			<div style="text-align:right"><a href="search_song.do" style="color: blue; font: bold;">곡 검색 결과 더보기</a></div>
			
			<table class="table table-hover">
				<thead>
					<tr>
						<th colspan="6">앨범(<span style="color:red">${album_list_size }</span>)</th>
					</tr>
				</thead>
			</table>
			<c:forEach var="album_list" items="${album_list }" begin="0" end="3" step="1">
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
			<div style="width: 100%; text-align:right; float:left"><a href="search_album.do" style="color: blue; font: bold">앨범 검색 결과 더보기</a></div>
			<div style="width: 100%; height:20px; float:left"></div>
		</div>
	</section>
</body>
</html>


