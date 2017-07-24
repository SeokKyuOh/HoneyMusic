package com.sist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.chart.ChartService;
import com.sist.chart.Daily_ChartVO;
import com.sist.playlist.dao.AlbumVO;
import com.sist.playlist.dao.MusicVO;
import com.sist.search.dao.SearchDAO;

@Controller
public class SearchController {
	@Autowired
	private ChartService service;
	
	@Autowired
	private SearchDAO dao;
	
	List<MusicVO> artist_list;
	List<MusicVO> song_list;
	List<AlbumVO> album_list;
	List<Daily_ChartVO> daily_list;
	
	int artist_list_size;
	int song_list_size;
	int album_list_size;
	
	@RequestMapping("main/search.do")
	public String search_result(String music_search, Model model){
		artist_list = null;
		song_list = null;
		album_list =null;
		
		artist_list_size = 0;
		song_list_size = 0;
		album_list_size = 0;
		
		artist_list = dao.artistData(music_search);
		song_list = dao.songData(music_search);
		album_list = dao.albumData(music_search);
		
		artist_list_size = artist_list.size();
		song_list_size = song_list.size();
		album_list_size = album_list.size();
		
		//움직이는 차트 값 가져오기
		daily_list=service.Daily_ChartData();
		model.addAttribute("daily_list", daily_list);
		
		
		model.addAttribute("artist_list", artist_list);
		model.addAttribute("song_list", song_list);
		model.addAttribute("album_list", album_list);
		model.addAttribute("artist_list_size", artist_list_size);
		model.addAttribute("song_list_size", song_list_size);
		model.addAttribute("album_list_size", album_list_size);
		model.addAttribute("main_jsp", "search/search.jsp");
		return "main/main";
	}
	
	@RequestMapping("main/search_artist.do")
	public String search_artist(Model model){
		daily_list=service.Daily_ChartData();
		model.addAttribute("daily_list", daily_list);
		model.addAttribute("artist_list", artist_list);
		model.addAttribute("artist_list_size", artist_list_size);
		model.addAttribute("main_jsp", "search/search_artist.jsp");
		return "main/main";
	}
	
	@RequestMapping("main/search_song.do")
	public String search_song(Model model){
		daily_list=service.Daily_ChartData();
		model.addAttribute("daily_list", daily_list);
		model.addAttribute("song_list", song_list);
		model.addAttribute("song_list_size", song_list_size);
		model.addAttribute("main_jsp", "search/search_song.jsp");
		return "main/main";
	}
	
	@RequestMapping("main/search_album.do")
	public String search_album(Model model){
		daily_list=service.Daily_ChartData();
		model.addAttribute("daily_list", daily_list);
		model.addAttribute("album_list", album_list);
		model.addAttribute("album_list_size", album_list_size);
		model.addAttribute("main_jsp", "search/search_album.jsp");
		return "main/main";
	}
	
	
}







