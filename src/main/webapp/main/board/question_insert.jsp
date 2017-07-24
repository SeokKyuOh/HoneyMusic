<!-- 이용권구매 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이용권구매</title>

<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script><!-- 라이브러리 로드 -->
<script type="text/javascript">



$(document).ready(function(){

	$(".accordion h3:first").addClass("active");
	$(".accordion p:not(:first)").hide();


	$(".accordion h3").click(function(){
	
	  $(this).next("p").slideToggle("slow")
	  .siblings("p:visible").slideUp("slow");
	  $(this).toggleClass("active");
	  $(this).siblings("h3").removeClass("active");
	
	});

});


</script>

</head>
<body>
	<section id="contentSection">
	<tr>
		<td>
			<a href="board_insert.do">새글</a>
		</td>
	</tr>
	<table class="table table-hover">

		<thead>
			<tr>
				<th width=15%>번호</th>
				<th width=15%>작성자</th>
				<th width=30%>제목</th>
				<th width=20%>등록일</th>
				<th width=20%>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.board_id }</td>
				<td>${vo.board_writer }</td>
				<td>
					<a href="board_content.do?board_id=${vo.board_id }">${vo.board_title }</a>
				
				</td>
				<td>
					<fmt:formatDate value="${vo.board_regdate }" pattern="yyyy-MM-dd"/>
				</td>
				<td>${vo.board_hit }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</section>

</body>
</html>