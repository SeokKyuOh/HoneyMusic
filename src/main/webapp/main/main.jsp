<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>HoneyMusic:달달한 음악이 필요한 순간</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">  
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="assets/css/animate.css">
<link rel="stylesheet" type="text/css" href="assets/css/font.css">
<link rel="stylesheet" type="text/css" href="assets/css/li-scroller.css">
<link rel="stylesheet" type="text/css" href="assets/css/slick.css">
<link rel="stylesheet" type="text/css" href="assets/css/jquery.fancybox.css">
<link rel="stylesheet" type="text/css" href="assets/css/theme.css">
<link rel="stylesheet" type="text/css" href="assets/css/style.css">
<!--[if lt IE 9]>
<script src="assets/js/html5shiv.min.js"></script>
<script src="assets/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function() {
	$('#logoutBtn').click(function() {
		$('#logoutForm').submit();
	});
	
});
</script>
</head>
<body>
<div id="preloader">
  <div id="status">&nbsp;</div>
</div>
<a class="scrollToTop" href="#"><i class="fa fa-angle-up"></i></a>
<div class="container">
  <header id="header">
    <div class="row">
     <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="header_top">
         <div class="header_top_left">
            <!-- <ul class="top_nav">
              <li><a href="main.do">Home</a></li>
              <li><a href="#">About</a></li>
              <li><a href="pages/contact.html">Contact</a></li> -
            </ul> -->
          </div>
          <div class="header_top_right">
          </div>
        </div>
      </div>
      <div class="col-lg-12 col-md-12 col-sm-12">
        <div class="header_bottom">
          <div class="logo_area"><a href="main.do" class="logo"><img src="images/honey_logo.png" alt=""></a></div>
          	<div style="margin: 15px 5px -90px 5px"> 
				<form method="post" action="search.do" class="contact_form">
					<input type="text" name=music_search style="width: 22%; height: 40px; margin: 30px 5px -80px 25px" placeholder="  검색어를 입력하세요"> 	
					<input type="submit" class="btn btn-theme" style="height: 40px; width: 7%" value="검색">
				</form>
			</div>
          <c:if test="${sessionScope.membervo.member_nick==null}">
	          <div align="right" style="margin: 50px 5px 10px 5px">
	          	<a href="login.do"><input type="button" class="btn btn-theme" value="로그인" width="50px"></a>
	            <h6><a href="#">아이디/비밀번호 찾기 |</a> <a href="join.do">회원가입</a></h6>          
	          </div>
          </c:if>
          <c:if test="${sessionScope.membervo.member_nick!=null}">
	          <div align="right" style="margin: 50px 5px 10px 5px">
	          	<h5>${sessionScope.membervo.member_name }(${ sessionScope.membervo.member_nick})님 환영합니다</h5>
	            <form method=post action="logout.do" id=logoutForm>
	            <input type=button class="btn btn-theme" value="로그아웃" id=logoutBtn>   
	            </form>       
	          </div>
          </c:if>
        </div>
      </div>
    </div>
  </header>
  <section id="navArea">
    <nav class="navbar navbar-inverse" role="navigation">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav main_nav">
          <li class="active"><a href="main.do"><span class="fa fa-home desktop-home"></span><span class="mobile-show">Home</span></a></li>
          <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">허니차트</a>
          	<ul class="dropdown-menu" role="menu">
          		<li><a href="daily_chart.do">일간</a></li>
          		<li><a href="weekly_chart.do">주간</a></li>
          	</ul>
          </li>
          <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">최신</a>
            <ul class="dropdown-menu" role="menu">
              	<li><a href="newAlbumChart.do">최신곡</a></li>
              	<li><a href="newAlbum.do">최신앨범</a></li>
              	<li><a href="newMusicVideo.do">최신뮤직비디오</a></li>
              	<li><a href="newChartFinder.do">차트파인더</a></li>
            </ul>
          </li>
          <li><a href="genreMusicChart.do?genre_id=1">장르</a></li>
          <li><a href="buy_ticket.do">이용권구매</a></li>
          <li><a href="board_list.do?boardType_id=1">공지사항</a></li>
          <li><a href="mypage_into.do?nick=${sessionScope.membervo.member_nick }&id=${sessionScope.membervo.member_id }">마이뮤직</a></li>
        </ul>
      </div>
    </nav>
  </section>
  <section id="newsSection">
    <div class="row">
      <div class="col-lg-12 col-md-12">
        <div class="latest_newsarea"> <span></span>
         <% int i=1; %>
          <ul id="ticker01" class="news_sticker">
          	<c:forEach var="daily_list" items="${daily_list}" begin="0" end="9" step="1">
	            <li><a href="daily_chart.do"><img src="http://211.238.142.109:8080/album_img/${daily_list.album_art }.jpg"><%=i %>위 - ${daily_list. music_name} - ${daily_list.music_artist }</a></li>
	            <%i++; %>
	         </c:forEach>
          </ul>
        </div>
      </div>
    </div>
  </section>
<!-- 바디 부분 이미지들 시작-->
<jsp:include page="${main_jsp }"></jsp:include>

	<!-- 메인컨텐츠 끝 -->
	<footer id="footer">
		<div class="footer_top">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="footer_widget wow fadeInLeftBig">
						<h2>바로가기</h2>
							<ul class="tag_nav">
				              <li><a href="daily_chart.do">허니차트-일간</a></li>
				              <li><a href="weekly_chart.do">허니차트-주간</a></li>
				              <li><a href="newAlbumChart.do">최신곡</a></li>
				              <li><a href="newAlbum.do">최신앨범</a></li>
				              <li><a href="genreMusicChart.do?genre_id=1">장르</a></li>
				              <li><a href="buy_ticket.do">이용권구매</a></li>
				              <li><a href="board_list.do?boardType_id=1">공지사항</a></li>
				              <li><a href="mypage_into.do?nick=${sessionScope.membervo.member_nick }&id=${sessionScope.membervo.member_id }">마이뮤직</a></li>
				           </ul>
					</div>
				</div>
				
				<div class="col-lg-5 col-md-5 col-sm-5">
					<div class="footer_widget wow fadeInRightBig">
						<h2>달달한 음악이 필요한 순간 Honey Music</h2>
						<p>서울시 마포구 백범로 18(노고산동) 미화빌딩 2,3층</p>
						<p>대표이사 : 김연실, 류오연, 문혜지, 박재욱, 오석규, 장현아, 차유경</p>
						<p>사업자등록번호 : 123-45-67899</p>
						<p>통신판매업 신고번호 : 제2017-서울마포-4321호</p>
						<p>문의전화(평일 09:00~18:00) : 1234-7777</p>
						<p>이메일 : honeyinfo@honey.com</p><br>
						<p>© Honey music Company, Inc. ALL RIGHTS RESERVED.</p>
					</div>
				</div>
			</div>
		</div>
		<div class="footer_bottom">
		</div>
	</footer>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/wow.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/slick.min.js"></script>
	<script src="assets/js/jquery.li-scroller.1.0.js"></script>
	<script src="assets/js/jquery.newsTicker.min.js"></script>
	<script src="assets/js/jquery.fancybox.pack.js"></script>
	<script src="assets/js/custom.js"></script>
</body>
</html>