<!-- 공지사항 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<title>공지</title>
</head>
<body>
	<c:out value="${nick }"></c:out>
	<c:out value="${id}"></c:out>
   <section id="contentSection">
    <div class="row">
      <div class="col-lg-8 col-md-8 col-sm-8">
        <div class="left_content">
          <div class="contact_area">
            <h2>글쓰기</h2>
            <p>공지 내용구성</p>
            <form method=post action="notice_insert_ok.do" class="contact_form">
            <!-- enctype="multipart/form-data" -->
             <input type="hidden" name="boardType_id" value="2"> 
             <input type="hidden" name="question_filename" value="오연"> 
             <input type="hidden" name="question_filesize" value="23"> 
             <input type="hidden" name="question_filecount" value="12"> 
             <input type="hidden" name="member_id" value="1"> 
             <input type=hidden name=nick value="${ nick}">
             <input type=hidden name=id value=" ${id }"> 
              <input class="form-control" type="text" name="question_id" placeholder="Nick*">
              <input class="form-control" type="text" name="question_title" placeholder="Title*">
              <textarea class="form-control" cols="30" rows="10" name="question_content" placeholder="Message*"></textarea>
                      <input type=submit value="등록하기" class="btn btn-theme">
                  
                  <input type="button" value="취소하기" class="btn btn-theme" onClick="javascript:history.back()">
          
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
</body>
</html>