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
	$(function() { //전체선택 체크박스 클릭 
		
		// ajax 처리 시 data 형식 중 배열 값을 넘기기 위한 설정
		$.ajaxSettings.traditional = true;
		
	// 선택된 곡의 music_id 저장
		var checkArr=new Array();
		checkArr.length=0;
		
		$("#allCheck").click(function() { //만약 전체 선택 체크박스가 체크된 상태일 경우 
			if ($("#allCheck").prop("checked")) { //해당화면에 전체 checkbox들을 체크해준다 
				$("input[type=checkbox]").prop("checked", true); // 전체선택 체크박스가 해제된 경우
			} else { //해당화면에 모든 checkbox들의 체크를 해제시킨다. 
				$("input[type=checkbox]").prop("checked", false);
			}
		});
		/* 
		// 체크박스 개별 선택
		$('input:checkbox[name="checkbox_name"]').click(function() {
			this.checked = true; //checked 처리	
			 if (this.checked) {//checked 처리된 항목의 값
				//alert(this.value);
				
			}
		});
		 */
		 
		// 비회원 & 이용권X / 회원+이용권
		$('#bt_send').click(function(){
			checkArr.length=0;
			alert('버튼 클릭');
			
			// 아무 곡도 선택되지 않은 경우
			
			// 곡 선택된 경우
			$("input[type=checkbox]:checked").each(function(){
				checkArr.push($(this).attr("music_id"));	// 체크된 모든 music_id값을 배열에 저장
			});
			getValue();	
		
		});
		 
		 
		 $('.bt_play').click(function(){
			 checkArr.length=0;
			 alert("개별 곡 클릭");
			 checkArr.push($(this).attr("music_id"));
			 getValue();
		 });
		 
		var getValue=function(){
			// member_id와 곡 id 전송
			<%
				// session에 로그인 정보가 없을 경우에는 member_id=null
				int member_id;
				MemberVO vo=(MemberVO)session.getAttribute("membervo");
				if(vo!=null){
					member_id=vo.getMember_id();
				}
				else{
					member_id=0;
				}
			%>
			var member_id=<%=member_id%>;
			//var sendVal={"member_id":<%=session.getAttribute("member_id")%>, "musics":checkArr};
			alert("member_id : "+member_id);
			alert("musics : "+checkArr);
			
			// JSON.stringify(sendVal)
			$.ajax({
				type:"POST",
				url:"player_playlist_id.do",
				data:{"member_id":member_id, "musics":checkArr},
				error:function(request, status, error){
					if(request.status==404){
						alert(error)
					}
					alert("code : "+request.status+"\n"+"message : "+request.responseText+"\n"+"error : "+error);
				},
				success:function(data){
					alert("성공");
					// playlist_music이 없는 경우
					//window.open("player/player_temp.jsp","HoneyMusicPlayer","width=450, height=800");
					
					// playlist_music이 있는 경우
					window.open("player.do","HoneyMusicPlayer","width=517px, height=680px");
				}
			});
		}
	})
</script>

<input type=button class="btn btn-theme" value="선택 재생" id="bt_send">

<table class="table table-hover">
	<thead>
		<tr>
			<th width=10%><input type="checkbox" value="" id="allCheck">
			</th>
			<th width=10%>순위</th>
			<th width=20%>앨범</th>
			<th width=20%>음악</th>
			<th width=20%>아티스트</th>
			<th width=20%>앨범정보</th>
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
				<a href="albumInfo.do?album_id=${vo.album_id }">
				<img
					src="http://211.238.142.109:8080/album_img/${vo.album_art }.jpg"
					width=50 height=50> 
				</a>	
					<input type="image" class="bt_play" music_id="${vo.music_id} "
						src="<c:url value="/resources/img/play.png"/>"
						style="width: 20px; height: 20px">
					<input type="image"	id="bt_add"
						src="<c:url value="/resources/img/add.png"/>"
						style="width: 20px; height: 20px"> 

				<td><a onClick="getValue()">
				${vo.music_name }</a></td>
				<td>${vo.music_artist }</td>
				<td><a href="albumInfo.do?album_id=${vo.album_id }">${vo.album_name }</a></td>

			</tr>
			<%
					i++;
				%>
		</c:forEach>
	</tbody>
</table>

</body>
</html>

