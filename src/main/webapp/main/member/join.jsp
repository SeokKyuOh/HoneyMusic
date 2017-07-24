<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Melon:음악이 필요한 순간</title>
<link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel="stylesheet" type='text/css'>
<link rel="stylesheet" type="text/css" href="assets/css/join.css">
<link rel="stylesheet" type="text/css" href="assets/shadow/css/shadowbox.css">
<link rel="stylesheet" type="text/css" href="assets/css/table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="assets/shadow/js/shadowbox.js"></script>
<script type="text/javascript">
Shadowbox.init({
	players:['iframe']
});
$(function(){
	$('#idcheckBtn').click(function(){
		Shadowbox.open({
			content:"idcheck.do",
			player:'iframe',
			title:'아이디 중복 체크',
			width:330,
			height:150
		});
	});
	
	
	//비번 체크
	$('#member_pwd').keyup(function(){
		$('font[name=check]').text('');
	}); //#user_pass.keyup

	$('#member_pwd_check').keyup(function(){
		if($('#member_pwd_check').val()!=$('#member_pwd').val()){
			$('font[name=check]').text('');
			$('font[name=check]').html("비밀번호가 다릅니다.");
		}else{
			$('font[name=check]').text('');
			$('font[name=check]').html("비밀번호가 일치합니다.");
		}
	});
	
	
	$('#postBtn').click(function(){
		Shadowbox.open({
			content:"postfind.do",
			player:'iframe',
			title:'우편번호 검색',
			width:450,
			height:400
		});
	});
	
});
</script>
</head>
<body>
	
	<form method="post" action="join_ok.do" name="join_frm">
	<fieldset>
        <h1>회원가입</h1>       
          <label for="nick">아이디</label>
          <input type="text" name="member_nick" style="width:74%" readonly required>
          <input type="button" value="ID 중복체크" id="idcheckBtn">
                    
          <label for="password">비밀번호</label>
          <input type="password" name="member_pwd" id="member_pwd" required>
          재입력&nbsp;<font name="check" size="2" color="red"></font>
          <input type="password" id="member_pwd_check">
                              
          <label for="email">이메일</label>
          <input type="email" name="member_email" placeholder="guest@melon.com" required>
          
          <label for="name">이름</label>
          <input type="text" name="member_name" required>
          
          <label>성별</label>
          <input type="radio" value="Male" name="member_gender" checked>남자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" value="Female" name="member_gender" >여자
          
          <label for="phone">전화번호</label>
          <select name="member_phone1" id="member_phone1" required>
          	<option>010</option>
          	<option>011</option>
          	<option>016</option>
          	<option>017</option>
          	<option>019</option>
          </select>-
          <input type="tel" name="member_phone2" style="width:25%">-
          <input type="tel" name="member_phone3" i style="width:25%">
          
          <label for="birth">생년월일</label>
          <input type="date" name="member_birthdate" >
          
          <label for="post">우편번호</label>
          <input type="text" name="member_post1" style="width:35%" readonly required>-
          <input type="text" name="member_post2" style="width:35%" readonly required>
          <input type="button" value="주소검색" id="postBtn">
          
          <label for="addr1">주소</label>
          <input type="text" name="member_addr1" placeholder="주소" readonly>
          
          <label for="addr2">상세주소</label>
          <input type="text" name="member_addr2" placeholder="상세주소">
        
        <button type="submit">가입하기</button>
        <button onclick="javascript:history.back()">돌아가기</button>
        
        </fieldset>
        </form>
      	
    </body>
</html>