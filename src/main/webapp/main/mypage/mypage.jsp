<!-- 마이뮤직 -->
<%@page import="com.sist.member.dao.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript">

$(function(){
    // Build the chart
    Highcharts.chart('container', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ''
        },
         tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        }, 
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: false
                },
                showInLegend: true
            }
        },
        series: [{
            name: '선호도',
            colorByPoint: true,
            data: <%=request.getAttribute("json")%>
        }]
	});
    	    
	$('.del').click(function(){
		var del=$(this);
		var playlist_music_id=$(this).attr("data");
		$.ajax({
			type:"POST", 
	        url: "del_playlist.do",
	        data: {"playlist_music_id":playlist_music_id},
	        success: function(response){
	            if (response.trim()==1) {
	                $(del).parent().parent().remove();
	               
	            } else{
	                alert("삭제할 수 없습니다. 나도 왠지 몰라");
	            }
	        }
	    });
	});
	
	// ajax 처리 시 data 형식 중 배열 값을 넘기기 위한 설정
	$.ajaxSettings.traditional = true;
	
	// 선택된 곡의 music_id 저장
	var checkArr=new Array();
	checkArr.length=0;
	
	$('.bt_play').click(function(){
		 checkArr.length=0;
		 alert("개별 곡 클릭");
		 checkArr.push($(this).attr("music_id"));
		 getValue();
	 });
	
	// playlist에 곡을 추가하기 위한 함수
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
		var sendVal={"member_id":<%=session.getAttribute("member_id")%>, "musics":checkArr};
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
}); 



/* function deleteLine(obj) {
    var tr = $(obj).parent().parent();
    //라인 삭제
    tr.remove();
} */
/* $('.del').click(function(){
		$(this).parent().parent().remove();
	});
*/

</script>
</head>

<body>
	<section id="contentSection">
	<div class="row">
		<div class="col-lg-8 col-md-8 col-sm-8" height=500>
			<div class="left_content">
				<div class="single_page">
					<ol class="breadcrumb">
						<li><a href="../index.html">Home</a></li>
						<li><a href="#">마이뮤직</a></li>
						<!-- <li class="active">내정보?</li> -->
					</ol>
					<div class="panel panel-default">
					  <div class="panel-body" >
					  <div class="col-sm-6 col-md-4" >
                       <!--  <img src="http://placehold.it/380x500" alt="" class="img-rounded img-responsive" /> -->
                    </div>


					    <h4>${vo.member_nick }</h4>
					    <small style="display: block; line-height:1.428571429; color: #999;">
					    	<cite title="San Francisco, USA">${vo.member_addr } 
					    		<i class="glyphicon glyphicon-map-marker"></i>
					    	</cite></small><br>
                        <p>
                        	<i class="glyphicon glyphicon-user"></i>&nbsp;${vo.member_name }
                            <br /><br>
                            <i class="glyphicon glyphicon-envelope"></i>&nbsp;${vo.member_email }
                            <br /><br>
                            <i class="glyphicon glyphicon-gift"></i>&nbsp;${vo.member_birthdate }</p><br>
                            <a href="info_update.do?nick=${vo.member_nick }" ><input type=button value="내 정보 수정" class="btn btn-theme"></a>
					  </div>
					</div>
					<div class="panel panel-default">
					  <div class="panel-body" >
					    <h3>내 구매 정보</h3>
                        <p><br><br>
                                <!--보유중인 이용권이 없습니다  -->
                                <table class="table table-hover" valign=center>
									<thead>
										<tr>
											<th>구매한 이용권</th>
											<th>시작일</th>
											<th>종료일</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="bsvo" items="${bsvo }">
											<tr>
												<td style="width: 60px">${bsvo.streaming_name }</td>
												<td style="width: 30px">${bsvo.buy_streaming_start }</td>
												<td style="width: 30px">${bsvo.buy_streaming_end }</td>
											</tr>
										</c:forEach>
										<c:forEach var="bdvo" items="${bdvo }">
											<tr>
												<td style="width: 60px">${bdvo.download_name }</td>
												<td style="width: 30px">${bdvo.buy_download_start }</td>
												<td style="width: 30px">${bdvo.buy_download_end }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
                        	<br />
                        </p><br>
					  </div>
					</div>
					
					<!-- 음악 추천 -->
					<div class="panel panel-default">
					  <div class="panel-body" >
					    <h3>For U</h3>
                        <p><br><br>
                        	<div>
                        	<br><br>
                        		많이 들은 <c:out value="${genre }"></c:out> 장르를 좋아하신다면 이런 앨범 어떠세요?<br><br>
								<ul>
									<c:forEach var="albumVO" items="${recommendAlbumVO }">
									<li>
										<div style="float: left; width: 50%; padding: 10px;">
											<a href="albumInfo.do?album_id=${albumVO.album_id }">
											<img
												width="130"
												height="130"
												src="http://211.238.142.109:8080/album_img/${albumVO.album_art }.jpg"/>
												</a>
											<div style="float: left;width: 50%; padding: 10px; ">
												<dl>
													<dt>${albumVO.album_name }</dt>
													<dd>
														<div>
																<span>${albumVO.album_artist }</span>
														</div>
													</dd>
												</dl>
											</div>
						
										</div>
										<!-- //class="wrap_album04" -->
									</li>
									</c:forEach>
								</ul>
							</div>
                                <%-- 
                                <table class="table table-hover" valign=center>
									<thead>
										<tr>
											<th></th>
											<th>앨범 이름</th>
											<th>앨범 아티스트</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="albumVO" items="${recommendAlbumVO }">
											<tr>
												<td style="width: 60px">
													<img id="image" src="http://211.238.142.109:8080/album_img/${albumVO.album_art}.jpg" width=30 height=30>
												</td>
												<td style="width: 30px">${albumVO.album_name }</td>
												<td style="width: 30px">${albumVO.album_artist }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								 --%>
								<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
								<div style="width:100%">
								많이 들은 <c:out value="${artist }"></c:out>의 또 다른 노래들
								</div>
								<br><br>
								<table class="table table-hover" valign=center>
									<thead>
										<tr>
											<th></th>
											<th>곡명</th>
											<th>아티스트</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="musicVO" items="${recommendMusicVO }">
											<tr>
												<td style="width: 60px">
													<a href="albumInfo.do?album_id=${musicVO.album_id }">
													<img
														src="http://211.238.142.109:8080/album_img/${musicVO.album_art }.jpg"
														width=40 height=40> 
													</a>	
														<input type="image" class="bt_play" music_id="${musicVO.music_id} "
															src="<c:url value="/resources/img/play.png"/>"
															style="width: 20px; height: 20px">
												</td>
												<td style="width: 30px">${musicVO.music_name }</td>
												<td style="width: 30px">${musicVO.music_artist }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
                        	<br>
                        </p><br>
					  </div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-lg-4 col-md-4 col-sm-4">
        <aside class="right_content">
          <div class="single_sidebar" style="OVERFLOW:auto; width:100%; height:330px;">
            <h2><span>나의 플레이리스트</span></h2>
            <table class="table table-hover" valign=center id="mytable">
			    <thead>
			      <tr>
			        <th>곡명</th>
			        <th>아티스트</th>
			        <th></th>
			      </tr>
			    </thead>
			    <tbody id="tbody">
			    
			    	<c:forEach var="mvo" items="${mvo }">
			    	<%-- <form method="post" action="del_playlist.do?playlist_music_id=${playlist_music_id }" id="delPlaylist"> --%>
					      <tr>
					        <td style="width:50%">${mvo.music_name }</td>
					        <td style="width:40%">${mvo.music_artist }</td>
					        <!-- <td style="width:10%"><a href=""><i class="glyphicon glyphicon-remove" id="del"></i></a></td> -->
					        <td style="width:10%">
					        	<input type="button" value="X" class="del" data="${mvo.playlist_music_id }"  style="background-color: transparent;border:none">
					        		<!-- onclick="deleteLine(this)" -->
					        </td>
					      </tr>
				     <!--  </form> -->
			      </c:forEach>
			    </tbody>
			  </table>
          </div>
        </aside>
      </div>
      <div class="col-lg-4 col-md-4 col-sm-4">
        <aside class="right_content">
          <div class="single_sidebar" style="OVERFLOW:auto; width:100%; height:200px;">
            <h2><span>내 문의 내역</span></h2>
            <table class="table table-hover">
			   <thead>
			   	  
	                <tr>
				        <th style="width:70%">내 문의글</th>
				        <th style="width:30%">작성일</th>
				      </tr>
			     
			    </thead>
			    <tbody>
				    <c:forEach var="q" items="${qvo }">
				      <tr>
				        <td>
				        	<a href="notice_content.do?question_id=${q.question_id }&nick=${vo.member_nick}&id=${vo.member_id}"> ${q.question_title }</a>
				        
				       </td>
				        <td><fmt:formatDate value="${q.question_regdate }" pattern="yyyy-MM-dd"/></td>
				      </tr>
				    </c:forEach>
			    </tbody>
			  </table>
			  
          </div>
          
         <div>
       		  <a href="notice_insert.do?nick=${vo.member_nick }&id=${vo.member_id}" >
			  <input type=button value="글쓰기" class="btn btn-theme" align="right"></a>
			 
          </div>
      
          
        </aside>
      </div>
      
      <!-- 그래프 추가 -->
      <div class="col-lg-4 col-md-4 col-sm-4">
        <aside class="right_content">
          <div class="single_sidebar" style="OVERFLOW:auto; width:100%; height:500px;">
            <h2><span>나의 선호 음악</span></h2>
            <div id="container" style="width:100%; height: 300px; margin: 0 auto">
  
            </div>
          </div>
        </aside>
      </div>

	</div>
	</section>
</body>
</html>




