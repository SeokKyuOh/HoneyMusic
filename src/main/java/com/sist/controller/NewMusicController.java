package com.sist.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.chart.ChartService;
import com.sist.chart.Daily_ChartVO;
import com.sist.newest.NewMusicService;
import com.sist.newest.NewMusicVO;

@Controller
public class NewMusicController {
	@Autowired
	private NewMusicService service;
	
	@Autowired								// 상단 차트순위 때문에 추가 (7/6 오석규)
	private ChartService chartservice;	//상단 차트순위 때문에 추가 (7/6 오석규)
	
	
	@RequestMapping("main/newAlbumChart.do")
	public String newMusic_list(Model model){
		List<NewMusicVO> list = service.NewMusicData();

		model.addAttribute("list", list);
		
		List<Daily_ChartVO> daily_list=chartservice.Daily_ChartData();	// 상단 차트순위 때문에 추가 (7/6 오석규)
		model.addAttribute("daily_list", daily_list);									// 상단 차트순위 때문에 추가 (7/6 오석규)
	
		model.addAttribute("main_jsp", "newest/newAlbumChart.jsp");
		return "main/main";
	}

}


