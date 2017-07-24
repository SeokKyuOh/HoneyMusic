package com.sist.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.chart.ChartService;
import com.sist.chart.Daily_ChartVO;
import com.sist.chartfinder.dao.MusicVO;
import com.sist.chartfinder.dao.RankDAO;
import com.sist.newest.NewAlbumVO;
import com.sist.newest.NewMusicService;
import com.sist.newest.NewMusicVO;

@Controller
public class NewAlbumController {
	@Autowired
	private RankDAO rdao;
	@Autowired
	private NewMusicService service;
	
	@Autowired							// ��� ��Ʈ���� ������ �߰� (7/6 ������)
	private ChartService chartservice;	//��� ��Ʈ���� ������ �߰� (7/6 ������)
	
	@RequestMapping("main/newAlbum.do")
	public String newAlbum_list(Model model){		
		
		List<NewAlbumVO> list = service.NewAlbumData();
		
		List<Daily_ChartVO> daily_list=chartservice.Daily_ChartData();	// ��� ��Ʈ���� ������ �߰� (7/6 ������)
		model.addAttribute("daily_list", daily_list);							// ��� ��Ʈ���� ������ �߰� (7/6 ������)

		model.addAttribute("list", list);
		model.addAttribute("main_jsp", "newest/newAlbum.jsp");
		return "main/main";
	}
	
	//�������� ���� �߰�(7/7 ������)
	@RequestMapping("main/newMusicVideo.do")
	public String newMusicVideo_list(Model model){
		String [] mv ={
				"twice_signal",
                "minzy_ninano", 
                "ftisland_wind",
                "Lovelyz",
                "hyukoh_tomboy",
                "50cent"
        };
		String [] mvid = {
				"VQtonf1fv_s",
				"nmZGpBIz_Gg",
				"QhPOwcvhGSA",
				"wMCoQaE0LvQ",
				"pC6tPEaAiYU",
				"5qm8PH4xAss"
		};
		
		int mv_size = mv.length;
		model.addAttribute("mv_size", mv_size);
		model.addAttribute("mv", mv);
		model.addAttribute("mvid", mvid);
		model.addAttribute("main_jsp", "newest/newMusicVideo.jsp");
		return "main/main";
	}
	
	@RequestMapping("main/newChartFinder.do")
	public String newChartFinder_list(Model model){
		
		List<MusicVO> list=rdao.getMyRankData();
		List<Integer> gList=rdao.getMusicRating("genie");
		List<Integer> mList=rdao.getMusicRating("melon");
		List<Integer> mnList=rdao.getMusicRating("mnet");
		System.out.println("mnList:"+mnList.get(0));
		String data="[";
		for(int j=0; j<5; j++){
//			JSONObject obj=new JSONObject();
//			obj.put("name", list.get(j).getTitle());
			
			String arr="[";
			arr+=mList.get(j)+","+gList.get(j)+","+mnList.get(j)+","+list.get(j).getRating();
			arr+="]";
			//obj.put("data", arr);
			data+="{name:'"+list.get(j).getTitle()+"',"+"data:"+arr+"},";
		//	jarr.add(obj);
		}
		data=data.substring(0,data.lastIndexOf(","));
		data+="]";

		model.addAttribute("json",data);
		//return "main/newest/newChartFinder";
		model.addAttribute("main_jsp", "newest/newChartFinder.jsp");
		return "main/main";
	}
}
