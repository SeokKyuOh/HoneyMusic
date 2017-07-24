package com.sist.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.chart.ChartService;
import com.sist.chart.Daily_ChartVO;



@Controller
public class ChartController {
	   @Autowired
	   private ChartService service;
	   
	   List<Daily_ChartVO> daily_list;		// 상단 차트순위 때문에 추가 (7/6 오석규)
	   
	   @RequestMapping("main/daily_chart.do")
	   public String dayliyChart_list(Model model){
	
		   List<Daily_ChartVO> list=service.Daily_ChartData();
		   model.addAttribute("list", list);
		   
		   daily_list=service.Daily_ChartData();						// 상단 차트순위 때문에 추가 (7/6 오석규)
		   model.addAttribute("daily_list", daily_list);				// 상단 차트순위 때문에 추가 (7/6 오석규)
		   
		   model.addAttribute("main_jsp", "chart/daliy_Chart.jsp");
		   return "main/main";
	   }
	   @RequestMapping("main/weekly_chart.do")
	   public String weeklyChart_list(Model model){
	
		   List<Daily_ChartVO> list=service.Weekly_ChartData(); 
		   model.addAttribute("list", list);
		   
		   daily_list=service.Daily_ChartData();						// 상단 차트순위 때문에 추가 (7/6 오석규)
		   model.addAttribute("daily_list", daily_list);				// 상단 차트순위 때문에 추가 (7/6 오석규)
		   
		   model.addAttribute("main_jsp", "chart/weekly_Chart.jsp");
		   return "main/main";
	   }
	   @RequestMapping("main/music_countIncrement.do")
	   public String musci_countIncrement(int music_id,Model model)
	   {
		   service.musicCountIncrement(music_id);
		   
		   daily_list=service.Daily_ChartData();						// 상단 차트순위 때문에 추가 (7/6 오석규)
		   model.addAttribute("daily_list", daily_list);				// 상단 차트순위 때문에 추가 (7/6 오석규)
		   
		   model.addAttribute("main_jsp", "chart/music_countIncrement.jsp");
		   return "main/main";
	   }

}

