package com.sist.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.board.dao.QuestionDAO;
import com.sist.board.dao.QuestionVO;
import com.sist.chart.ChartService;
import com.sist.chart.Daily_ChartVO;
import com.sist.genre.GenreService;
import com.sist.genre.GenreVO;
import com.sist.member.dao.MemberDAO;
import com.sist.member.dao.MemberVO;
import com.sist.playlist.dao.MusicVO;
import com.sist.playlist.dao.PlaylistDAO;
import com.sist.playlist.dao.PlaylistMusicVO;
import com.sist.ticket.dao.Buy_downloadVO;
import com.sist.ticket.dao.Buy_streamingVO;

@Controller
public class MainController {
	@Autowired
	private ChartService service;

	@Autowired // 메인 앨범 이미지 때문에 추가 (7/6 오석규)
	private GenreService genreservice; // 메인 앨범 이미지 때문에 추가 (7/6 오석규)

	@Autowired
	MemberDAO dao;

	@Autowired
	QuestionDAO qdao;
	
	@Autowired
	PlaylistDAO pdao;

	List<Daily_ChartVO> daily_list; // 상단 차트순위 때문에 추가 (7/6 오석규)

	@RequestMapping("main/main.do")
	public String main_page(Model model) {
		// 실시간 차트 불러오기
		daily_list = service.Daily_ChartData();
		List<GenreVO> album_main = genreservice.genreAlbumData(1); // 메인 앨범 이미지 때문에 추가 (7/6 오석규)

		// 뮤직 비디오 불러오기
		String[] mv = { "twice_signal", "minzy_ninano", "ftisland_wind", "Lovelyz", "hyukoh_tomboy", "50cent" };
		String[] mvid = { "VQtonf1fv_s", "nmZGpBIz_Gg", "QhPOwcvhGSA", "wMCoQaE0LvQ", "pC6tPEaAiYU", "5qm8PH4xAss" };
		model.addAttribute("mv", mv);
		model.addAttribute("mvid", mvid);
		model.addAttribute("daily_list", daily_list);
		model.addAttribute("album_main", album_main); // 메인 앨범 이미지 때문에 추가 (7/6 오석규)
		model.addAttribute("main_jsp", "default.jsp");
		return "main/main";
	}

	/*
	 * @RequestMapping("main/buy_ticket.do") public String buy_ticket_page(Model
	 * model){ model.addAttribute("main_jsp","buy_ticket/buy_ticket.jsp");
	 * return "main/main"; }
	 */

	@RequestMapping("main/mypage.do")
	public String mypage_page(Model model, String nick, int id) {
		model.addAttribute("main_jsp", "mypage/mypage.jsp");
		MemberVO vo = dao.memberAllData(nick);

		List<QuestionVO> qvo = qdao.questionList(id);

		List<Buy_streamingVO> bsvo = dao.mypageStreamingInfo(id);
		List<Buy_downloadVO> bdvo = dao.mypageDownloadInfo(id);
		List<MusicVO> mvo = dao.mypagePlayList(id);

		// 이용권-날짜 형식 변경(시분초 제거)
		for (Buy_streamingVO vo2 : bsvo) {
			vo2.setBuy_streaming_start(vo2.getBuy_streaming_start().substring(0, 10));
			vo2.setBuy_streaming_end(vo2.getBuy_streaming_end().substring(0, 10));
		}
		for (Buy_downloadVO vo2 : bdvo) {
			vo2.setBuy_download_start(vo2.getBuy_download_start().substring(0, 10));
			vo2.setBuy_download_end(vo2.getBuy_download_end().substring(0, 10));
		}
		model.addAttribute("mvo", mvo);
		model.addAttribute("vo", vo);
		model.addAttribute("qvo", qvo);
		model.addAttribute("bsvo", bsvo);
		model.addAttribute("bdvo", bdvo);

		daily_list = service.Daily_ChartData(); // 상단 차트순위 때문에 추가 (7/6 오석규)
		model.addAttribute("daily_list", daily_list); // 상단 차트순위 때문에 추가 (7/6오석규)

		// 추가 (7/21 석규)
		List<MusicVO> mylistgraph = dao.mypagePlayListGraph(id);
		model.addAttribute("mylistgraph", mylistgraph);

		String data = "[";
		for (int j = 0; j < mylistgraph.size(); j++) {
			data += "{name:'" + mylistgraph.get(j).getGenre_name() + "'," + "y:" +mylistgraph.get(j).getCnt() + "},";
		}	
		data = data.substring(0, data.lastIndexOf(","));
		data += "]";
		System.out.println(data);
		model.addAttribute("json", data);
		
		
	    /*	마이페이지에서 음악 추천 정보 띄우기	*/
	    List<PlaylistMusicVO> albumVO=pdao.getAlbumListFromTopGenre(id);
	    List<PlaylistMusicVO> musicVO=pdao.getMusicListFromTopArtist(id);
	      
	    // 랜덤으로 3개의 앨범, 3개의 곡씩 추출
	    // 3개의 랜덤 숫자 만들기
	    ArrayList<Integer> albumNum=new ArrayList<Integer>();
	    ArrayList<Integer> musicNum=new ArrayList<Integer>();
	    
	    albumNum=pdao.getRandomNumber(albumNum, albumVO);
	    musicNum=pdao.getRandomNumber(musicNum, musicVO);
	    
	    List<PlaylistMusicVO> recommendAlbumVO=new ArrayList<PlaylistMusicVO>();
	    List<PlaylistMusicVO> recommendMusicVO=new ArrayList<PlaylistMusicVO>();
	    
	    // 추출한 앨범, 곡만 보내기
	    recommendAlbumVO=pdao.getSendResults(albumNum, albumVO);
	    recommendMusicVO=pdao.getSendResults(musicNum, musicVO);
	      
	    /*for(int i=0; i<musicNum; i++){
	   	    System.out.println("보낼 앨범 : "+recommendAlbumVO.get(i).getAlbum_name());
	        System.out.println("보낼 곡 : "+recommendMusicVO.get(i).getMusic_name());
	    }*/
	     
	    // 추출한 장르, 아티스트 보내기
	    model.addAttribute("genre", pdao.getGenreInfo(id).getGenre_name());
	    model.addAttribute("artist", pdao.getArtistInfo(id).getMusic_artist());
	    model.addAttribute("recommendAlbumVO", recommendAlbumVO);
	    model.addAttribute("recommendMusicVO", recommendMusicVO);


		return "main/main";
	}

	@RequestMapping("main/login.do")
	public String login_page(Model model) {
		return "main/member/login";
	}

}
