<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="shadow/css/shadowbox.css">
<link rel="stylesheet" type="text/css" href="style/table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="shadow/js/shadowbox.js"></script>
<!-- <script type="text/javascript">
	var i = 0;
	var u = 0;
	Shadowbox.init({
		players:['iframe']
	
	});
	$(function() {
		var offset = $(".mytable").offset();
		$('html, body').animate({
			scrollTop : offset.top
		}, 100);
		$('.mytable').fadeIn("slow");
		$('.modify').click(function() {
			var no = $(this).attr("value");
			if (u == 0) {
				$('#u' + no).show();
				u = 1;
			} else {
				$('#u' + no).hide();
				u = 0;
			}

			var offset = $("#reply_view").offset();
			$('html, body').animate({
				scrollTop : offset.top
			}, 100);
			$('#reply_view').fadeIn("slow");
		});
		$('.insert').click(function() {
			var no = $(this).attr("value");
			if (i == 0) {
				$('#i' + no).show();
				i = 1;
			} else {
				$('#i' + no).hide();
				i = 0;
			}
			var offset = $("#reply_view").offset();
			$('html, body').animate({
				scrollTop : offset.top
			}, 'slow');

		});
		$('#del').click(function(){
			var no=$('#del').attr("data1");
			var page=$('#del').attr("data2");
			Shadowbox.open({
				content:'board_delete.do?no='+no+'&page='+page,
				player:'iframe',
				title:'삭제',
				width:300,
				height:150
			});
		});
		
	});
</script> -->
</head>
<body>
<section id="contentSection">
	<center>
		<h3>내용보기</h3>
		<table id="table_content" width=90% class="mytable">
			<tr>
				<th width=20%>번호</th>
				<td width=30% align=center>${vo.board_id }</td>
				<th width=20%>작성일</th>
				<td width=30% align=center>
				<fmt:formatDate value="${vo.board_regdate }" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th width=20%>이름</th>
				<td width=30% align=center>${vo.board_writer }</td>
				<th width=20%>조회수</th>
				<td width=30% align=center>${vo.board_hit}</td>
			</tr>
 			<c:if test="${vo.board_filecount>0 }">
				<tr>
					<th width=20%>첨부파일</th>
					<td colspan="3" align=left><c:forEach var="fvo"
							items="${vo.board_fileList }">
							<a href="board_download.do?fn=${fvo.board_filename}">${fvo.board_filename }</a>
          (${fvo.board_filesize }Bytes)<br>
						</c:forEach></td>
				</tr>
			</c:if>
			<tr>
				<th width=20%>제목</th>
				<td colspan="3" align=left>${vo.board_title }</td>
			</tr>
			<tr>
				<td colspan="4" align="left" valign="top" height="100"><pre>${vo.board_content }</pre></td>
			</tr>
		</table>
		<table id="table_content" width=90%>
			<tr>
				<td align=right><a href="board_update.do?board_id=${vo.board_id }"></a>&nbsp; 
					<a></a>&nbsp; 
					<a href="board_list.do?boardType_id=1">목록</a>
				</td>
			</tr>
		</table>
<%--  		<div id="reply_view">
			<table id="table_content" width=600>
				<c:forEach var="rvo" items="${rList }">
					<tr>
						<td align=left width=70%>
							<c:if test="${rvo.group_tab>0 }">
								<c:forEach var="i" begin="1" end="${rvo.group_tab}">
					            	 &nbsp;&nbsp;
					       		</c:forEach>
								<img src="images/icon_reply.gif">
							</c:if> ${rvo.name }&nbsp;(${rvo.strDay })<br> 
							<c:if test="${rvo.group_tab>0 }">
								<c:forEach var="i" begin="1" end="${rvo.group_tab}">
					           	  &nbsp;&nbsp;
					           </c:forEach>
							</c:if> ${rvo.msg }</td>
						<td align=right width=30%>
						<c:if test="${sessionScope.id==rvo.id && sessionScope.id!=null}">
         					└<a href="#" class="modify" value="${rvo.no }">수정</a>&nbsp;
        					 └<a href="reply_delete.do?no=${rvo.no }&bno=${vo.no }&page=${page}" >삭제</a>&nbsp;
        				 </c:if> 
        				 <c:if test="${sessionScope.id!=null}">
         					└<a href="#" class="insert" value="${rvo.no }">댓글</a>
						 </c:if></td>
					</tr>
					<tr style="display: none" id="i${rvo.no }">
						<td colspan="2">
							<form method="post" action="reply_re_insert.do">
								<div style="float: left; height: 45px">
									<input type="hidden" name="bno" value="${vo.no }">
									<input type="hidden" name="page" value="${page }">
									<input type="hidden" name="pno" value="${rvo.no }">
									<textarea rows="3" cols="60" name=msg></textarea>
								</div>
								<div style="float: left">
									<input type="submit" value="댓글쓰기" style="height: 45px">
								</div>
							</form>
						</td>
					</tr>
					<tr id="u${rvo.no }" style="display: none">
						<td colspan="2">
							<form method="post" action="reply_update.do">
								<div style="float: left; height: 45px">
									<input type="hidden" name="bno" value="${vo.no }"><!-- 컨텐트로 다시 돌아오기 위해 필요한거 -->
									<input type="hidden" name="page" value="${page }"><!-- 얘도 -->
									<input type="hidden" name="no" value="${rvo.no }">
									<textarea rows="3" cols="60" name=msg>${rvo.msg }</textarea>
								</div>
								<div style="float: left">
									<input type="submit" value="수정하기" style="height: 45px">
								</div>
							</form>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${sessionScope.id!=null }">
					<tr>
						<td colspan="2">
							<form method="post" action="reply_new_insert.do">
								<div style="float: left; height: 45px">
									<input type="hidden" name="bno" value="${vo.no }"> 
									<input type="hidden" name="page" value="${page }">
									<textarea rows="3" cols="60" name=msg></textarea>
								</div>
								<div style="float: left">
									<input type="submit" value="댓글쓰기" style="height: 45px">
								</div>
							</form>
						</td>
					</tr>
				</c:if>
			</table>
		</div> 
 --%>	</center>
 </section>
</body>
</html>









