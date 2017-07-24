<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Melon:음악이 필요한 순간</title>
        <link href='https://fonts.googleapis.com/css?family=Nunito:400,300' rel="stylesheet" type='text/css'>
        <link rel="stylesheet" type="text/css" href="assets/css/join.css">
    </head>
    <body>

      <form method="post" action="login_ok.do" name="login_frm">
      
        <h1>로그인</h1>
        
          <input type="text" id="nick" name="member_nick"  placeholder="아이디" required>
          <input type="password" id="password" name="member_pwd" placeholder="비밀번호" required>
          <h5 style="text-align: center"> <a href="#">아이디찾기</a>   |   <a href="#">비밀번호찾기</a>   |   <a href="join.do">회원가입</a></h1>
          

        <button type="submit">로그인</button>
      </form>
      
    </body>
</html>