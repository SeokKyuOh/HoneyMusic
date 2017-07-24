<%@page import="java.util.ArrayList"%>
<%@page import="com.sist.playlist.dao.PlaylistMusicVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/resources/js/soundmanager2.js"/>"></script>
<script src="<c:url value="/resources/js/bar-ui.js"/>"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/bar-ui.css"/>" />
<script>
	
	var albumURL="http://211.238.142.109:8080/album_img/";
	var musicURL="http://211.238.142.109:8080/music/";
	
	var isImageClicked=0;		// 앨범아트 클릭 여부 확인
	
	$(function(){
		// 이용권 여부 확인
		
		$('#lyrics').hide();
		
		var album_art=[];
		var music_lyrics=[];
		
		// 앨범 아트를 클릭하면 현재 재생되고 있는 곡의 가사 불러오기
		$('#album').click(function(){
			if(isImageClicked==0){
				//alert(isImageClicked);
				isImageClicked++;
				$('#image').fadeTo(1000, 0.4);
				$('#lyrics').show();
			}
			// 다시 클릭하면 원래 이미지대로 출력
			else{
				//alert(isImageClicked);
				isImageClicked--;
				$('#image').fadeTo(1000, 1);
				// 이미지의 투명도(opacity)를 낮추고 가사 출력
				$('#lyrics').hide();
				
				// 현재 재생되고 있는 곡인지 check -> <li> 클릭 시 index로 구분
			}
		});
		/* 
		$('.sm2-playlist-bd').find('li').each(function(){
			
		});
		 */
		 
		// 현재 재생되고 있는 곡인지 check -> <li> 클릭 시 index로 구분
		// 각 곡을 클릭하면 곡에 해당하는 앨범아트와 가사 가져오기
		$(".mclick").click(function(){
			var index=$(this).attr("index");
			var album_art=$(this).attr("album_art");
			var music_lyrics=$(this).attr("music_lyrics");
			//alert(index);
			//$("li:eq("+index+")")
			//var url=$("#image").attr("src");
			//alert(url);
			// 앨범아트 바꾸기(추후 클릭한 곡의 album_art url 넣기)
			var album_replace="../main/player/cover/album.jpg";
			$("#image").attr("src", album_replace);
			$("#image").attr("src", albumURL+album_art+".jpg");
			//$("#image").attr("src", "../main/player/cover/album"+index+".jpg");
			// 가사 바꾸기
			var lyrics=lyrics;
			alert(album_replace);
			alert("album_art : "+album_art+", music_lyrics : "+music_lyrics);
			
		});
		/* 	
		// 삭제 버튼 클릭 시 
		$('.btclick').click({
			var playlist_music_id=$(this).attr('playlist_music_id');
			alert(playlist_music_id);
			$.ajax({
				type:"GET",
				url:"player_delete.do?playlist_music_id="+playlist_music_id,
				dataType:"text",
				error:function(){
					alert('통신 실패');
				},
				success:function(){
					alert("성공");
					$('.mclick').remove();
				}
			})
		});
		 */
	});
	
</script>
</head>
<body>

	<!-- 앨범아트 및 가사 구현 -->
	<!-- 선택된 곡이 여러개일 경우 가장 위에 있는 곡의 앨범아트, 곡 하나를 클릭해 추가되었을 경우 해당 곡의 앨범아트 -->
	<div id="album" style="position:relative; width:450px; height:400">
		<c:set var="album_art" value="${playlist[0].album_art }"/>
		<c:set var="lyric" value="${playlist[0].music_lyrics }"/>
		<img id="image" src="http://211.238.142.109:8080/album_img/${album_art}.jpg" width=450 height=400> 
		<!-- <img src="../main/player/cover/album1.jpg" id="image" width=450 height=400> -->
		<div id="lyrics" style="position:absolute; color:white; text-align:center; top:20%; left:45%">
			<p>
				${lyric }
			</p>
		</div>
	</div>
<!-- 	
	<div id="lyrics" style="overflow-y:scroll; overflow-x:hidden; background-color:blue; width:450px; height:400" >
		111111111111111111111<br>
		111111111111111111111<br>
		111111111111111111111<br>
	</div>
-->
	<div class="sm2-bar-ui playlist-open">
		<div class="bd sm2-main-controls">
			<div class="sm2-inline-texture"></div>
			<div class="sm2-inline-gradient"></div>
			<div class="sm2-inline-element sm2-button-element">
				<div class="sm2-button-bd">
					<a href="#play" class="sm2-inline-button sm2-icon-play-pause">Play
						/ pause</a>
				</div>
			</div>
			<div class="sm2-inline-element sm2-inline-status">
			
				<div class="sm2-playlist">
					<div class="sm2-playlist-target">
						playlist <ul> + <li> markup will be injected here
						if you want default / non-JS content, you can put that here.
						<noscript>
							<p>JavaScript is required.</p>
						</noscript>
					</div>
				</div>
				<!-- 상태바(현재는 플레이어 페이지가 뜨면서 재생 버튼을 눌러야 재생 시작! 
				곡을 클릭해서 플레이어로 들어왔을 때  -->
				<div class="sm2-progress">
					<div class="sm2-row">
						<div class="sm2-inline-time">0:00</div>
						<div class="sm2-progress-bd">
							<div class="sm2-progress-track">
								<div class="sm2-progress-bar"></div>
								<div class="sm2-progress-ball">
									<div class="icon-overlay"></div>
								</div>
							</div>
						</div>
						<div class="sm2-inline-duration">0:00</div>
					</div>
				</div>
			</div>
			<div class="sm2-inline-element sm2-button-element sm2-volume">
				<div class="sm2-button-bd">
					<span class="sm2-inline-button sm2-volume-control volume-shade"></span>
					<a href="#volume" class="sm2-inline-button sm2-volume-control">volume</a>
				</div>
			</div>
			<div class="sm2-inline-element sm2-button-element">
				<div class="sm2-button-bd">
					<a href="#prev" title="Previous"
						class="sm2-inline-button sm2-icon-previous">&lt; previous</a>
				</div>
			</div>
			<div class="sm2-inline-element sm2-button-element">
				<div class="sm2-button-bd">
					<a href="#next" title="Next"
						class="sm2-inline-button sm2-icon-next">&gt; next</a>
				</div>
			</div>
			<div class="sm2-inline-element sm2-button-element sm2-menu">
				<div class="sm2-button-bd">
					<a href="#menu" class="sm2-inline-button sm2-icon-menu">menu</a>
				</div>
			</div>
		</div>

		<div class="bd sm2-playlist-drawer sm2-element">
			<div class="sm2-inline-texture">
				<div class="sm2-box-shadow"></div>
			</div>
			<!-- 곡 리스트 구현 -->
			<div class="sm2-playlist-wrapper">

				<ul class="sm2-playlist-bd">
					<!-- 곡 다운로드 링크 있는 리스트 구현 -->
					<!-- 
					<li>
						<div class="sm2-row">
							<div class="sm2-col sm2-wide">
								<a
									href="http://freshly-ground.com/data/audio/sm2/SonReal%20-%20LA%20%28Prod%20Chin%20Injetti%29.mp3"><b>SonReal</b>
									- LA</a>
							</div>
						</div>
					</li> 
					-->
					<!-- standard one-line items -->
					<!-- 211.238.142.109:8080/food/main/music/18360609.mp3 
					../song/01. Amazons of Themyscira.mp3
					-->
					<!--  
					<li><a href="http://211.238.142.109:8080/food/main/music/18360609.mp3">Amazons
							of Themyscira</a></li>
					<li><a href="song/02. History Lesson.mp3">History
							Lesson</a></li>
					<li><a href="song/02. History Lesson.mp3">Angel on
							the Wing</a>
					</li>
					 -->
					 
					<c:forEach var="vo" items="${playlist}" varStatus="status">
						<%-- <li id="${vo.music_number }"> --%>
						<li class="mclick" album_art="${vo.album_art }" music_lyrics="${vo.music_lyrics}" index="${status.index }">
							<a href="http://211.238.142.109:8080/music/${vo.music_number }.mp3">
								${vo.music_name } - ${vo.music_artist }
							</a>
						</li>
						<%-- <input type="button" value="edit" class="btclick" playlist_music_id="${vo.playlist_music_id }"> --%>
					</c:forEach>
					
				</ul>
			</div>
			<div class="sm2-extra-controls">
				<div class="bd">
					<div class="sm2-inline-element sm2-button-element">
						<a href="#prev" title="Previous"
							class="sm2-inline-button sm2-icon-previous">&lt; previous</a>
					</div>
					<div class="sm2-inline-element sm2-button-element">
						<a href="#next" title="Next"
							class="sm2-inline-button sm2-icon-next">&gt; next</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>