<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>최신 뮤직비디오</title>
</head>
 <script type="text/javascript">
        /**
         * Youtube API 로드
         */
        var tag = document.createElement('script');
        tag.src = "https://www.youtube.com/iframe_api";
        var firstScriptTag = document.getElementsByTagName('script')[0];
        firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
 
        /**
         * onYouTubeIframeAPIReady 함수는 필수로 구현해야 한다.
         * 플레이어 API에 대한 JavaScript 다운로드 완료 시 API가 이 함수 호출한다.
         * 페이지 로드 시 표시할 플레이어 개체를 만들어야 한다.
         */
        var player;
        
        
        //가수,노래제목
        var mv = [
                     'twice_signal',
                     'minzy_ninano', 
                     'ftisland_wind',
                     'Lovelyz',
                     'hyukoh_tomboy',
                     '50cent'
                     ];
        
        //유튜브 뮤비 링크값
        var mvid = [
                    'VQtonf1fv_s',
                    'nmZGpBIz_Gg',
                    'QhPOwcvhGSA',
                    'wMCoQaE0LvQ',
                    'pC6tPEaAiYU',
                    '5qm8PH4xAss'
                    ];
        
        
        function onYouTubeIframeAPIReady() {
        	for(i=0;i<=mv.length;i++){
	        	player = new YT.Player(mv[i], {
	                height: '270',            // <iframe> 태그 지정시 필요없음
	                width: '460',             // <iframe> 태그 지정시 필요없음
	                videoId: mvid[i],   // <iframe> 태그 지정시 필요없음
	                playerVars: {             // <iframe> 태그 지정시 필요없음
	                    controls: '2'
	                },
	                events: {
	                    'onReady': onPlayerReady,               // 플레이어 로드가 완료되고 API 호출을 받을 준비가 될 때마다 실행
	                    'onStateChange': onPlayerStateChange    // 플레이어의 상태가 변경될 때마다 실행
	                }
	            });
            }
        }
        //https://youtu.be/9bZkp7q19f0
        function onPlayerReady(event) {
            console.log('onPlayerReady 실행');
            // 플레이어 자동실행 (주의: 모바일에서는 자동실행되지 않음)
			// event.target.playVideo();
        }
        function onPlayerStateChange(event) {
            playerState = event.data == YT.PlayerState.ENDED ? '종료됨' :
                    event.data == YT.PlayerState.PLAYING ? '재생 중' :
                    event.data == YT.PlayerState.PAUSED ? '일시중지 됨' :
                    event.data == YT.PlayerState.BUFFERING ? '버퍼링 중' :
                    event.data == YT.PlayerState.CUED ? '재생준비 완료됨' :
                    event.data == -1 ? '시작되지 않음' : '예외';
 
            console.log('onPlayerStateChange 실행: ' + playerState); 
            
        }
 
        function playYoutube() {
            // 플레이어 자동실행 (주의: 모바일에서는 자동실행되지 않음)
            player.playVideo();
        }
        
        
       
    </script>
<body>
	<section id="contentSection">
		<div class="center_content" style="width:80%">
			<table class="table table-hover">
				<thead>
					<tr>
						<th colspan="5">최신 뮤직비디오</th>
					</tr>
				</thead>
			</table>
			<c:forEach var="mv" items="${mv}">
				<div id="${mv }"></div>
				<c:if test="${mv_size%2!=0 }">
					<iframe style="width: 30px"></iframe>
				</c:if>
			</c:forEach>
		</div>
	</section>	
</body>
</html>