package com.sist.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.chart.ChartService;
import com.sist.chart.Daily_ChartVO;
import com.sist.member.dao.MemberVO;
import com.sist.ticket.dao.Buy_downloadVO;
import com.sist.ticket.dao.Buy_streamingVO;
import com.sist.ticket.dao.DownloadDAO;
import com.sist.ticket.dao.DownloadVO;
import com.sist.ticket.dao.StreamingDAO;
import com.sist.ticket.dao.StreamingVO;

@Controller
public class TicketController {
	@Autowired
	private StreamingDAO stDao;
	
	@Autowired
	private DownloadDAO doDao;
	
	@Autowired							// 상단 차트순위 때문에 추가 (7/6 오석규)
	private ChartService service;	//상단 차트순위 때문에 추가 (7/6 오석규)
	
	List<Daily_ChartVO> daily_list;//상단 차트순위 때문에 추가 (7/6 오석규)
	
	@RequestMapping("main/buy_ticket.do")
	public String buy_ticket_page(Model model, HttpSession session){
		
		daily_list=service.Daily_ChartData();						// 상단 차트순위 때문에 추가 (7/6 오석규)
		model.addAttribute("daily_list", daily_list);				// 상단 차트순위 때문에 추가 (7/6 오석규)
		
		List<DownloadVO> dList = doDao.download_list();
		List<StreamingVO> sList = stDao.streaming_list();
		
		for(StreamingVO vo : sList) {
			System.out.println(vo.getStreaming_name()+" " + vo.getStreaming_period()+  " "+ vo.getStreaming_price());
		}
		
		model.addAttribute("st_isvalid", "no");
		model.addAttribute("do_isvalid", "no");
		
		if(session.getAttribute("membervo") != null) {
			MemberVO mvo = (MemberVO) session.getAttribute("membervo");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			
			int st_count = stDao.count_buy_streaming(mvo.getMember_id());
			int do_count = doDao.count_buy_download(mvo.getMember_id());
			
			if(st_count != 0) {
				Buy_streamingVO svo = stDao.select_one_buy_streaming(mvo.getMember_id());
				
				try {
					Date endDate = sdf.parse(svo.getBuy_streaming_end());
					Date nowDate = new Date();
					
					int compare = endDate.compareTo(nowDate);
					
					if(compare >= 0) {//기한남음
						model.addAttribute("st_isvalid", "yes");
					} 
					else {
						model.addAttribute("st_isvalid", "no");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			if(do_count != 0) {
				Buy_downloadVO dvo = doDao.select_one_buy_download(mvo.getMember_id());
				
				try {
					Date endDate = sdf.parse(dvo.getBuy_download_end());
					Date nowDate = new Date();
					
					int compare = endDate.compareTo(nowDate);
					
					if(compare >= 0) {//기한남음
						model.addAttribute("do_isvalid", "yes");
					} 
					else {
						model.addAttribute("do_isvalid", "no");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
		model.addAttribute("dList", dList);
		model.addAttribute("sList", sList);	
		model.addAttribute("main_jsp","buy_ticket/buy_ticket2.jsp");
		
		return "main/main";
	}
	
	@RequestMapping("main/payment.do")
	public String ticket_payment(String id, String type, Model model, HttpSession session) {
		
		daily_list=service.Daily_ChartData();						// 상단 차트순위 때문에 추가 (7/6 오석규)
		model.addAttribute("daily_list", daily_list);				// 상단 차트순위 때문에 추가 (7/6 오석규)
		
		System.out.println(id + ":" + type);
		
		int tid = Integer.parseInt(id);
		System.out.println(id);
	
		if(session.getAttribute("membervo") == null) {
			System.out.println("로그인 창으로 이동");
			//model.addAttribute("main_jsp", "member/login.jsp");
			return "redirect:/main/login.do";
		}
		else {
			if(type.equals("streaming")) {
				StreamingVO sInfo = stDao.select_one_streaming(tid);
				model.addAttribute("id", sInfo.getStreaming_id());
				model.addAttribute("name", sInfo.getStreaming_name());
				model.addAttribute("price", sInfo.getStreaming_price());
			} else if(type.equals("download")) {
				DownloadVO dInfo = doDao.select_one_download(tid);
				model.addAttribute("id", dInfo.getDownload_id());
				model.addAttribute("name", dInfo.getDownload_name());
				model.addAttribute("price", dInfo.getDownload_price());
			}
			
			model.addAttribute("type", type);
			model.addAttribute("main_jsp","buy_ticket/payment.jsp");
			
			return "main/main";
		} 
		
		
	}
	
	@RequestMapping("main/payment_ok.do")
	public String ticket_payment_ok(String id, String type, Model model, HttpSession session) {
		System.out.println(id + ":" + type);
		MemberVO mvo = (MemberVO) session.getAttribute("membervo");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		
		if(type.equals("streaming")) {
			//해당 스트리밍 조회
			StreamingVO sInfo = stDao.select_one_streaming(Integer.parseInt(id));
			
			Buy_streamingVO svo = new Buy_streamingVO();
			
			svo.setStreaming_id(Integer.parseInt(id));
			svo.setMember_id(mvo.getMember_id());
			svo.setBuy_streaming_start(sdf.format(date));

			cal.setTime(date);
			cal.add(Calendar.DATE, sInfo.getStreaming_period() - 1);
		    cal.set(Calendar.HOUR_OF_DAY, 23);
		    cal.set(Calendar.MINUTE, 59);
		    cal.set(Calendar.SECOND, 59);
		    cal.set(Calendar.MILLISECOND, 999);
		    
		    svo.setBuy_streaming_end(sdf.format(cal.getTime()));
		    
		    System.out.println(svo.getStreaming_id() + ", " + svo.getBuy_streaming_start() + ", " + svo.getBuy_streaming_end() );
		    //dao연결
		    
		    stDao.insert_buy_streaming(svo);
			
		} else if(type.equals("download")) {
			DownloadVO dInfo = doDao.select_one_download(Integer.parseInt(id));
			
			Buy_downloadVO dvo = new Buy_downloadVO();
			
			dvo.setDownload_id(Integer.parseInt(id));
			dvo.setMember_id(mvo.getMember_id());
			dvo.setBuy_download_start(sdf.format(date));
			dvo.setDownload_qt(dInfo.getDownload_qt());

			cal.setTime(date);
			cal.add(Calendar.DATE, dInfo.getDownload_period() - 1);
		    cal.set(Calendar.HOUR_OF_DAY, 23);
		    cal.set(Calendar.MINUTE, 59);
		    cal.set(Calendar.SECOND, 59);
		    cal.set(Calendar.MILLISECOND, 999);
		    
		    dvo.setBuy_download_end(sdf.format(cal.getTime()));
		    
		    System.out.println(dvo.getDownload_id() + ", " + dvo.getBuy_download_start() + ", " + dvo.getBuy_download_end() );
		    //dao연결
		    
		    doDao.insert_buy_download(dvo);
		}
		
		return "redirect:/main/mypage.do?nick=" + mvo.getMember_nick() + "&id=" + mvo.getMember_id();
	}
	
	
	
}
