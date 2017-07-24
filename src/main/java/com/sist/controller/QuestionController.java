package com.sist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.board.dao.QuestionDAO;
import com.sist.board.dao.QuestionVO;
import com.sist.chart.ChartService;
import com.sist.chart.Daily_ChartVO;

@Controller
public class QuestionController {

   @Autowired
   private QuestionDAO dao;
   
   @Autowired                     // 상단 차트순위 때문에 추가 (7/6 오석규)
   private ChartService service;   //상단 차트순위 때문에 추가 (7/6 오석규)
   
   List<Daily_ChartVO> daily_list;   //상단 차트순위 때문에 추가 (7/6 오석규)

   @RequestMapping("main/notice_content.do")
   public String question_Content(int question_id, String nick, int id,Model model) {
      
      daily_list=service.Daily_ChartData();                  // 상단 차트순위 때문에 추가 (7/6 오석규)
      model.addAttribute("daily_list", daily_list);            // 상단 차트순위 때문에 추가 (7/6 오석규)


      QuestionVO vo = dao.questionContent(question_id);

      model.addAttribute("vo", vo);
      model.addAttribute("id", id);
      model.addAttribute("nick", nick);
      model.addAttribute("main_jsp", "notice/notice_content.jsp");

      return "main/main";

   }

   @RequestMapping("main/notice_update.do")
   public String question_Update(QuestionVO vo, String nick, int id, Model model) {
      dao.questionUpdate(vo);
      model.addAttribute("main_jsp", "mypage.jsp");
      return "redirect:/main/mypage.do?nick=" + nick + "&id=" + id;

   }

   @RequestMapping("main/notice_insert.do")

   public String question_insert(Model model, String nick, int id) {
      System.out.println(nick + " : " + id);
      daily_list=service.Daily_ChartData();                  // 상단 차트순위 때문에 추가 (7/6 오석규)
      model.addAttribute("daily_list", daily_list);      // 상단 차트순위 때문에 추가 (7/6 오석규)
      model.addAttribute("nick", nick);
      model.addAttribute("id", id);

      model.addAttribute("main_jsp", "notice/notice_insert.jsp");
      return "main/main";

   }

   @RequestMapping("main/notice_insert_ok.do")
   public String question_insert_ok(QuestionVO vo, String nick, int id) {
      System.out.println(vo.getQuestion_title());
      System.out.println("ok_nick:"+nick+",id:"+id);
      dao.questionInsert(vo);
      System.out.println("insert end");
      return "redirect:/main/mypage.do?nick="+nick +"&id="+id;
   }

   @RequestMapping("main/notice_delete.do")
   public String board_delete(int question_id,String nick, int id, Model model) {
      System.out.println("del" + question_id );
      dao.questionDelete(question_id);
      return "redirect:/main/mypage.do?nick="+nick +"&id="+id;
   }

}

