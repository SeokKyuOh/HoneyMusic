package com.sist.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.member.dao.MemberDAO;
import com.sist.member.dao.MemberVO;
import com.sist.member.dao.ZipcodeVO;

@Controller
public class MemberController {
	@Autowired
	private MemberDAO dao;
	
	//로그인
	@RequestMapping("main/login_ok.do")
	public String member_login(String member_nick, String member_pwd, HttpSession session, Model model){
		int idCount=dao.memberIdCheck(member_nick);
		String res="";
		if(idCount==0){
			res="NOID";
		}
		else{
			String pwd_db=dao.memberGetPwd(member_nick);
			if(member_pwd.equals(pwd_db)){
				res="OK";
				MemberVO vo=dao.memberAllData(member_nick);
				session.setAttribute("membervo", vo);
				/*session.setAttribute("nick", vo.getMember_nick());
				session.setAttribute("name", vo.getMember_name());
				session.setAttribute("id", vo.getMember_id());
				session.setAttribute("phone", vo.getMember_phone());
				session.setAttribute("email", vo.getMember_email());
				session.setAttribute("addr", vo.getMember_addr());
				session.setAttribute("pwd", vo.getMember_pwd());
				session.setAttribute("birthdate", vo.getMember_birthdate());
				session.setAttribute("gender", vo.getMember_gender());
				session.setAttribute("regdate", vo.getMember_regdate());
				session.setAttribute("cash", vo.getMember_cash());
				session.setAttribute("post", vo.getMember_post());*/
				
			}else{
				res="NOPWD";
			}
		}
		model.addAttribute("res", res);
		return "main/member/login_ok";
	}
	
	//로그아웃
	@RequestMapping("main/logout.do")
	public String member_logout(HttpSession session){
		session.invalidate();
		return "redirect:/main/main.do"; 
	}
	
	//회원정보 수정창 띄우기
			@RequestMapping("main/info_update.do")
			public String member_info_update(String nick, Model model){
				MemberVO vo=dao.memberAllData(nick);
				String addr1=vo.getMember_addr().substring(0, vo.getMember_addr().indexOf("-"));
				String addr2=vo.getMember_addr().substring(vo.getMember_addr().indexOf("-")+1, vo.getMember_addr().length());

				vo.setMember_addr1(addr1);
				vo.setMember_addr2(addr2);
				String[] post=vo.getMember_post().split("-");
				String[] phone=vo.getMember_phone().split("-");
				
				vo.setMember_post1(post[0]);
				vo.setMember_post2(post[1]);
				vo.setMember_phone1(phone[0]);
				vo.setMember_phone2(phone[1]);
				vo.setMember_phone3(phone[2]);
				
				//날짜 형식 변경
				/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String dt=sdf.format(vo.getMember_birthdate()); //date->string
				SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
				
				try {
					Date birth=sdf2.parse(dt);//string->date
					vo.setMember_birthdate(birth);
					System.out.println(vo.getMember_birthdate());
				} catch (ParseException e) {
					e.printStackTrace();
				} */
				
				//말고 생일만 스트링으로 따로보내기 (나중에 스트링으로 vo 일괄변경해도되고..)
				//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				//vo.setMember_birthdate(sdf.format(vo.getMember_birthdate())); //date->string
				//model.addAttribute("birthdate",birthdate);
				model.addAttribute("vo",vo);
				return "main/mypage/info_update";
			}
			
			//회원정보 수정(업데이트) 완료
			@RequestMapping("main/info_update_ok.do")
			public String member_info_update_ok(MemberVO vo){
				//mypage 페이지에 수정된 정보 넘기기. 세션에는?
				System.out.println("11");
				//db 업데이트
				vo.setMember_phone(vo.getMember_phone1()+"-"+vo.getMember_phone2()+"-"+vo.getMember_phone3());
				vo.setMember_post(vo.getMember_post1()+"-"+vo.getMember_post2());
				vo.setMember_addr(vo.getMember_addr1()+"-"+vo.getMember_addr2());
				dao.memberUpdate(vo);
				System.out.println("id:"+vo.getMember_id()+",name:"+vo.getMember_name());
				//MemberVO mvo=dao.memberAllData(vo.getMember_nick());
				//session.setAttribute("membervo", mvo);
				return "redirect:/main/mypage.do?nick="+vo.getMember_nick()+"&id="+vo.getMember_id();
			}

	
	//회원가입 연결
	@RequestMapping("main/join.do")
	public String join_page(Model model){
		model.addAttribute("main_jsp", "member/join.jsp");		
		return "main/member/join";
	}
	
	//회원가입 완료
	@RequestMapping("main/join_ok.do")
	public String member_join_ok(@ModelAttribute("vo") MemberVO vo, Model model){
		vo.setMember_phone(vo.getMember_phone1()+"-"+vo.getMember_phone2()+"-"+vo.getMember_phone3());
		vo.setMember_post(vo.getMember_post1()+"-"+vo.getMember_post2());
		vo.setMember_addr(vo.getMember_addr1()+"-"+vo.getMember_addr2());
		dao.memberInsert(vo);
		/*System.out.println("member_name : "+vo.getMember_name());
		System.out.println("member_phone : "+vo.getMember_phone());
		System.out.println("member_emai : "+vo.getMember_email());
		System.out.println("member_addr : "+vo.getMember_addr());
		System.out.println("member_nick : "+vo.getMember_nick());
		System.out.println("member_pwd : "+vo.getMember_pwd());
		System.out.println("member_birthdate : "+vo.getMember_birthdate());
		System.out.println("member_gender : "+vo.getMember_gender());
		System.out.println("member_post : "+vo.getMember_post());*/
		model.addAttribute("main_jsp", "member/join_ok.jsp");
		return "main/main";
	}
	
	//아이디 체크
	@RequestMapping("main/idcheck.do")
	public String member_idcheck(){
		return "main/member/idcheck";
	}
	
	//아이디 체크 확인
	@RequestMapping("main/idcheck_result.do")
	public String member_idcheck_result(String member_nick, Model model){
		int count=dao.memberIdCheck(member_nick);
		model.addAttribute("count", count);
		model.addAttribute("member_nick", member_nick);
		return "main/member/idcheck_result";
	}
	
	//주소찾기
	@RequestMapping("main/postfind.do")
	public String member_postfind(){
		return "main/member/postfind";
	}
	
	//주소찾으면 입력하기
	@RequestMapping("main/postfind_result.do")
	public String member_postfind_result(String dong, Model model){
		System.out.println(dong);
		List<ZipcodeVO> list=dao.zipcodeListData(dong);
		model.addAttribute("list", list);
		model.addAttribute("count", list.size());
		return "main/member/postfind_result";
	}

	//마이페이지 플레이리스트삭제-디비에서
		@RequestMapping("main/del_playlist.do")
		public String deletePlaylist(int playlist_music_id, Model model){
			int res=0;
			dao.myPlaylistDelete(playlist_music_id);
			res=1;
			System.out.println("res:"+res);
			model.addAttribute("res", res);
			return "main/mypage/delete_playlist_result";
		}
	
}
