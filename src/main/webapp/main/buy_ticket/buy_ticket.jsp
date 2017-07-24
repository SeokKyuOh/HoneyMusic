<!-- 이용권구매 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>이용권구매</title>
<script type="text/javascript">
function sendBuy() {
 location.href="payment.jsp";
}
</script>
</head>
<body> 
 <div class="container">
    <h2 align="center">이용권 구매</h2>
    <div class="panel-body">
     <img src="../images/attention.png" width="50px" height="50px">
     <font style="font-size: 9;font-weight: bold; font-family: 나눔고딕;color: gray">이용권은 결제한 당일부터 이용기간 마감일까지 유효한 이용권입니다.</font>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">스트리밍</div>
      <div class="panel-body">
       <img src="../images/30.JPG">
       <span style="width: 50px">스트리밍 15</span>
       <span style="width: 100px">가격 : 8000원</span>
       <div class="btn-group" role="group" align="right" style="float:right">
        <button type="button" class="btn btn-primary" style="top: 50px" onclick="sendBuy()">구매</button>
      </div>
      </div>
      <div class="panel-body">
       <img src="../images/30.JPG">
       <span style="width: 50px">스트리밍 30</span>
       <span style="width: 100px">가격 : 10000원</span>
       <div class="btn-group" role="group" align="right" style="float: right"> 
        <button type="button" class="btn btn-primary" style="top: 50px">구매</button>
      </div>
      </div>
    </div>
    <hr>
    <div class="panel panel-default">
      <div class="panel-heading">다운로드</div>
      <div class="panel-body">
       <img src="../images/30.JPG">
       <span style="width: 50px">다운로드 15</span>
       <span style="width: 100px">가격 : 8000원</span>
       <div class="btn-group" role="group" align="right" style="float: right">
        <button type="button" class="btn btn-primary" style="top: 50px">구매</button>
      </div>
      </div>
      <div class="panel-body">
       <img src="../images/50.JPG">
       <span style="width: 50px">다운로드 30</span>
       <span style="width: 100px">가격 : 12000원</span>
       <div class="btn-group" role="group" align="right" style="float: right">
        <button type="button" class="btn btn-primary" style="top: 50px">구매</button>
      </div>
      </div>
    </div>
 </div>
</body>
</html>