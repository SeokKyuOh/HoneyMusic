package com.sist.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.member.dao.MemberVO;
import com.sist.playlist.dao.MusicVO;
import com.sist.playlist.dao.PlaylistDAO;
import com.sist.playlist.dao.PlaylistMusicVO;

@Controller
public class PlayerController {

	@Autowired
	private PlaylistDAO playlistDAO;
	
	// 플레이어 구동(이용권 있을 때)
	@RequestMapping("main/player.do")
	public String getPlaylist(HttpSession session, Model model) {
		MemberVO vo=(MemberVO)session.getAttribute("membervo");
		int member_id=vo.getMember_id();
		
		System.out.println("member_id"+member_id);
		
		// Playlist, Playlist_music, Album, Music join
		List<PlaylistMusicVO> playlist = playlistDAO.getPlaylist(member_id);
		
		model.addAttribute("playlist", playlist);
		return "/main/player/player";
		
	}
	
	// 재생 버튼을 누르거나 곡을 담을 때 가장 먼저 실행됨!!!
	// 곡 개별 선택 시 실행되는 부분
	// playlist_music에 곡을 담기 위해 playlist_id가져오기
	// String member_id, ArrayList<Integer> musics, Model model
	// 이용권 여부와 회원 여부를 보내 ajax 측에서 조건 따질 수 있게 구현
	@RequestMapping("main/player_playlist_id.do")
	public String getPlaylistId(@RequestParam("member_id") int member_id, @RequestParam("musics") ArrayList<Integer> musics, Model model){
		//System.out.println("member_id : "+member_id+"\n");
		//System.out.println("musics : "+musics+"\n");
		String playlist_id=playlistDAO.getPlaylistId(member_id);
		int playlistId=Integer.parseInt(playlist_id);
		
		int isPlayerValid = playlistDAO.isStreamingValid(member_id);
		
		// 회원인 경우
		if (member_id != 0) {
					
			//String playlist_id=playlistDAO.getPlaylistId(member_id);
			//int playlistId=Integer.parseInt(playlist_id);
			System.out.println("playlist_id : "+playlist_id);
			
			// 이용권 여부
			//int isPlayerValid = playlistDAO.isStreamingValid(member_id);
			System.out.println("isPlayerValid : "+isPlayerValid);
			
			// 만약 playlist_id가 없고, 이용권이 유효한 경우 
			if(playlist_id==null && isPlayerValid>0){
				// playlist column 생성
				playlistDAO.makePlaylist(member_id);
				
				// playlist_id와 music_id list 넘기기
				model.addAttribute("musics", musics);
				model.addAttribute("playlist_id", playlistId);
				
				// 이용권 여부를 위한 isPlayerValie도 넘기기
				model.addAttribute("isPlayerValid", isPlayerValid);
				return "redirect:/main/player_insert.do";
			}
			
			// 만약 playlist_id가 있고 이용권이 유효한 경우
			// playlist_id와 music_id list를 넘기며 playlist_music에 곡 정보 저장
			else if(playlist_id!=null && isPlayerValid>0){
				System.out.println("만약 playlist_id가 있고 이용권이 유효한 경우");
				System.out.println(musics);
				model.addAttribute("isPlayerValid", isPlayerValid);
				model.addAttribute("musics", musics);
				model.addAttribute("playlist_id", playlistId);
				
				return "redirect:/main/player_insert.do";
			}
			
			// 만약 playlist_id가 없고 이용권이 무효한 경우
			else if(playlist_id==null && isPlayerValid==0){
				// 이용권이 무효하므로 playlist column을 생성할 필요가 없음!
				// 그냥 못듣게 하자...이용권 있을때만 들어라
				model.addAttribute("musics", musics);
				model.addAttribute("isPlayerValid", isPlayerValid);
				return "redirect:/main/player_temp.do";
				
				// 이용권 구매 유도
				//return "main/player/buy_ticket";
			}
			
			// 만약 playlist_id가 있고 이용권이 무효한 경우
			else{
				// 그냥 못듣게 하자...이용권 있을때만 들어라
				model.addAttribute("musics", musics);
				model.addAttribute("isPlayerValid", isPlayerValid);
				
				// 이용권 구매 유도
				return "redirect:/main/player_temp.do";
				//return "main/player/buy_ticket";
			}
		}
		
		// 회원이 아닌 경우 
		else{
			System.out.println("회원이 아닌 경우");
			System.out.println(musics);
			model.addAttribute("musics", musics);
			model.addAttribute("isPlayerValid", isPlayerValid);
			
			// 회원가입 유도
			return "redirect:/main/player_temp.do";
		}
	}
	
	// album_id를 이용해 music_id가져오기(앨범 재생)
	@RequestMapping("main/player_album.do")
	public String getMusicId(@RequestParam("member_id") int member_id, int album_id, Model model){
		ArrayList<Integer> musics=playlistDAO.getMusicId(album_id);
		model.addAttribute("musics", musics);
		return "redirect:main/player_playlist_id.do";
	}
	

	// music_id를 이용해 temp player 만들기
	// 플레이어 구동(회원이 아니거나 이용권 없을 때) -> music_id만 받아 출력하기(join은 player.do와 동일하게!)
	// -> 다 필요없고 그냥 회원가입  / 이용권 사라고 하자...
	@RequestMapping("main/player_temp.do")
	public String openTempPlayer(@RequestParam("musics") ArrayList<Integer> musics, Model model){
		List<MusicVO> playlist=new ArrayList<MusicVO>();
		int music_id=0;
		for(int i=0; i<musics.size(); i++){
			music_id=musics.get(i);
			MusicVO vo=playlistDAO.getTempList(music_id);
			playlist.add(vo);
		}
		model.addAttribute("playlist", playlist);
		return "main/player/buy_ticket";
	}
	
	/*
	 * @RequestMapping("main/player.do") public String getPlayer(Model model){
	 * return "main/player/player"; }
	 */
	// 이용권이 없을 때 출력되는 페이지(이용권 구매 유도)

	// playlist 테이블에 column 추가(최초의 playlist_music을 만드는 경우)
	
	
	// 재생 횟수 늘리기
	@RequestMapping("main/player_count.do")
	public String increaseCount(int playlist_music_id, int music_id) {
		//int id = Integer.parseInt(playlist_music_id);
		System.out.println("playlist_music id:" + playlist_music_id);
		System.out.println("music_id:" + music_id);
		playlistDAO.increaseCount(playlist_music_id);
		
		// music_count도 늘리기
		playlistDAO.increaseMusicCount(music_id);

		return "main/player/player";
	}
	
	// playlist_music에 곡 추가 후 재생
	@RequestMapping("main/player_insert.do")
	public String insertMusic(int playlist_id, @RequestParam("musics") ArrayList<Integer> musics){
		try{
		for(int i=0; i<musics.size(); i++){
			System.out.println("musics : "+musics.get(i)+"\n");
		}
		int music_id;
		for(int i=0; i<musics.size(); i++){
			music_id=musics.get(i);
			playlistDAO.insertMusic(playlist_id, music_id);
		}
		
		System.out.println("나왔어");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return "main/player/player_index";
	}

	// 추후 구현 예정
	// 플레이리스트에서 곡 지우고 다시 출력
	@RequestMapping("main/player_delete.do")
	public String deleteMusic(int playlist_music_id) {
		return "main/player/player";
	}
	
	
	
	
}
