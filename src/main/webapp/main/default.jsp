<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<title>Insert title here</title>
<script src='https://code.jquery.com/jquery-1.11.2.min.js'></script>
 <script type="text/javascript">
	$(function(){
		setInterval("showGraph()",10000);
		
	});
	
	function showGraph(){
		$.ajax({
			type:'POST',
			url:'graph.do',
			success:function(response){
				$('#graph').html(response);
				
			}
		});
		
	}
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
</head>
<body>
<section id="sliderSection">
  <!-- 최신 앨범 이미지 보이는 곳 -->
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
        <div class="slick_slider"> <!-- 이동 버튼 -->
          <c:forEach var="album_main" items="${album_main }">
	          <div class="single_iteam"> <a href="#"> <img src="http://211.238.142.109:8080/album_img/${album_main.album_art }.jpg" ></a>
	            <div class="slider_article">
	              <h2><a class="slider_tittle" href="#">${album_main.album_name }</a></h2>
	              <p>${album_main.music_artist }</p>
	            </div>
	          </div>
          </c:forEach>
        </div>
      </div>
      <!-- 최신앨범 이미지 끝 -->
      
      <!-- 앨범 차트 구현 예정 자리 시작 -->
      <div class="col-lg-4 col-md-4 col-sm-4">
        <div class="latest_post">
          <h2><span>실시간 앨범 차트</span></h2>
          <div class="latest_post_container">
            <div id="prev-button"><i class="fa fa-chevron-up"></i></div>
            <ul class="latest_postnav">
            <% int i=1; %>
              <c:forEach var="daily_list" items="${daily_list}" begin="0" end="4" step="1">
              <li>
                <div class="media"> <a href="daily_chart.do" class="media-left"> <img src="http://211.238.142.109:8080/album_img/${daily_list.album_art }.jpg"> </a>
                  <div class="media-body"> <a href="daily_chart.do" ><%=i %>위 - ${daily_list. music_name} - ${daily_list.music_artist }</a> </div>
                </div>
              </li>
              <% i++; %>
              </c:forEach>
               <li></li>
            </ul>
            <div id="next-button"><i class="fa  fa-chevron-down"></i></div>
          </div>
        </div>
      </div>
      <!-- 앨범차트 구현 끝 -->
    </div>
  </section>
  <!-- 상단 2개 이미지 구현 끝 -->
  
  <!-- 다음 이미지 구현 구간 시작 -->
  <section id="contentSection">
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
        <div class="left_content">
          <div class="single_post_content">
            <h2><span>뮤직비디오</span></h2>
            <div class="single_post_content_left">
              <ul class="business_catgnav  wow fadeInDown">
                <li>
                  <figure class="bsbig_fig"> <a href="newMusicVideo.do" class="featured_img"> <div id="${mv[0] }" style="width:100%; height: 250px"></div> <span class="overlay"></span> </a>
                    <figcaption> <a href="newMusicVideo.do">${mv[0] }</a> </figcaption>
                    <p></p>
                  </figure>
                </li>
              </ul>
            </div>
            
            <div class="single_post_content_right">
              <ul class="spost_nav">
              	<c:forEach var="mv" items="${mv }" begin="1" end="2" step="1">
	              	<li>
	                  <figure class="bsbig_fig"> <a href="newMusicVideo.do" class="featured_img"> <div id="${mv }" style="width:100%; height: 180px"></div> <span class="overlay"></span> </a>
	                  </figure>
	                </li>
                </c:forEach>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <!-- 다음 이미지 구현 구간 끝 -->
      <!-- 우측 이미지2 구현 시작 -->
      <div class="col-lg-4 col-md-4 col-sm-4">
        <aside class="right_content">
          <div class="single_sidebar">
            <h2><span>해시태그</span></h2>

            <div class="tab-content">
              <div role="tabpanel" class="tab-pane active" id="category">
                <ul>
                  <li class="cat-item"><a href="recommand.do?no=1">#신나는 노래</a></li>
                  <li class="cat-item"><a href="recommand.do?no=2">#위로가 필요할 때</a></li>
                  <li class="cat-item"><a href="recommand.do?no=3">#비오는 날</a></li>
                  <li class="cat-item"><a href="recommand.do?no=4">#드라이브</a></li>
                  <li class="cat-item"><a href="recommand.do?no=5">#사랑 노래 추천</a></li>
                  <li class="cat-item"><a href="recommand.do?no=6">#새벽 감성</a></li>
                  <li class="cat-item"><a href="recommand.do?no=7">#눈오는 날</a></li>
                </ul>
              </div>
              </div>
              
          </div>
        </aside>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
        <div class="left_content">
          <div class="single_post_content">
            <h2><span>인기 검색어</span></h2>
			      <div id="graph">
			      		
			      </div>
            
          </div>
        </div>
      </div>

    </div>
  </section>
</body>
</html>
