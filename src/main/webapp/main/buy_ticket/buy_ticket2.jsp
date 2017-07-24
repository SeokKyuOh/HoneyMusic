<!-- 이용권구매 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>이용권구매</title>
<script type="text/javascript">
function sendBuy(id, type) {
	alert("type : " + type + ", id: " + id);
	location.href="payment.do?id="+id+"&type="+type;
}
</script>
</head>
<body> 
	<section id="contentSection">
	<div class="container" style="width: 80%">
  		<h2 align="center">이용권 구매</h2>
  		<div class="panel-body">
  			<img src="images/attention.png" width="50px" height="50px">
  			<font style="font-size: 9;font-weight: bold; font-family: 나눔고딕;color: gray">이용권은 결제한 당일부터 이용기간 마감일까지 유효한 이용권입니다.</font>
  		</div>
  		<div class="panel panel-default">
    		<div class="panel-heading">스트리밍</div>
	  		<c:forEach var="vo" items="${sList }">
    		<div class="panel-body">
    			<img src="images/${vo.streaming_name }.png" width="200px">
    			<span style="width: 50px;">${vo.streaming_name }</span>
    			<span style="width: 100px">가격 : ${vo.streaming_price }원</span>
    			<div class="btn-group" role="group" align="right" style="float:right">
    				<c:if test="${st_isvalid == 'no'}">
    				<button type="button" class="btn btn-primary active" style="background-color: #B5D7BB;border-color: #B5D7BB; box-shadow:none" onclick="sendBuy(${vo.streaming_id},'streaming')">구매</button>
    				</c:if>
    				<c:if test="${st_isvalid == 'yes'}">
    				<button type="button" class="btn btn-primary disabled" style="background-color: #B5D7BB;border-color: #B5D7BB; box-shadow:none" onclick="sendBuy(${vo.streaming_id},'streaming')">구매</button>
    				</c:if>
  				</div>
    		</div>
  		</c:forEach>
  		</div>
  		<hr>
  		<div class="panel panel-default">
    		<div class="panel-heading">다운로드</div>
    		<c:forEach var="vo" items="${dList }">
    		<div class="panel-body">
    			<img src="images/${vo.download_name }.png" width="200px">
    			<span style="width: 50px">${vo.download_name }</span>
    			<span style="width: 100px">가격 : ${vo.download_price }원</span>
    			<div class="btn-group" role="group" align="right" style="float: right">
    				<c:if test="${do_isvalid == 'no'}">
    				<button type="button" class="btn btn-primary active" style="background-color: #B5D7BB;border-color: #B5D7BB; box-shadow:none" onclick="sendBuy(${vo.download_id},'download')">구매</button>
    				</c:if>
    				<c:if test="${do_isvalid == 'yes'}">
    				<button type="button" class="btn btn-primary disabled" style="background-color: #B5D7BB;border-color: #B5D7BB; box-shadow:none" onclick="sendBuy(${vo.download_id},'download')">구매</button>
    				</c:if>
  				</div>
    		</div>
    		</c:forEach>
  		</div>
	</div>
	</section>
</body>
</html>