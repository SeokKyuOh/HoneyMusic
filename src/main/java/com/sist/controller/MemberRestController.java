package com.sist.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {

@RequestMapping("main/mypage_into.do")
public String mypage_into(String nick, String id){
	//System.out.println("1");
	//System.out.println("nick:"+nick+",id:"+id);
	String data="";
	if(nick.equals("")){
		data="<script>"
				+"alert(\"로그인 후 이용해주세요\");"
				+"location.href=\"login.do\";"
				+"</script>";
	}
	else{
		data="<script>"
				+"location.href=\"mypage.do?nick="+nick+"&id="+id+"\";"
				+"</script>";
	}
	return data;
}
}
