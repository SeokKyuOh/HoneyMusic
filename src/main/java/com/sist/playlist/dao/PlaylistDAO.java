package com.sist.playlist.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaylistDAO {
	@Autowired
	private PlaylistMapper pMapper;
	
	// playlist_music 에서 곡 리스트 정보 가져오기
	public List<PlaylistMusicVO> getPlaylist(int member_id) {
		// 가져온 member_id를 가지고 회원의 playlist_music 테이블을 조인한 결과 가져오기
		// 플레이어에 출력할 값
		// 상단 : 앨범아트, 곡에 해당하는 가사
		// 중간 : 곡 이름, 아티스트
		// 하단 : 곡 리스트(제목 - 아티스트)
		System.out.println("getPlaylist");
		System.out.println(pMapper.getPlaylistId(member_id));
		return pMapper.getPlaylist(member_id);
	}
	
	// 임시 player구동
	public MusicVO getTempList(int music_id){
		System.out.println("getTempList");
		return pMapper.getTempList(music_id);
	}
	
	// playlist를 처음 만들때
	public void makePlaylist(int member_id){
		System.out.println("makePlaylist");
		pMapper.makePlaylist(member_id);
	}
	
	// 앨범 재생을 클릭했을 때 music_id 구하기
	public ArrayList<Integer> getMusicId(int album_id){
		System.out.println("getMusicId");
		return pMapper.getMusicId(album_id);
	}
	
	// member_id를 가지고 playlist_id찾기(없는 경우 null값 return)
	public String getPlaylistId(int member_id){
		System.out.println("getPlaylistId");
		return pMapper.getPlaylistId(member_id);
	}
	
	// 스트리밍 이용권을 가지고 있는지 확인(유효 : 1, 무효 : 0)
	public int isStreamingValid(int member_id){
		System.out.println("isstreamingValid");
		return pMapper.isStreamingValid(member_id);
	}
	
	// 선택한 곡의 재생 횟수 늘리기
	public void increaseCount(int playlist_music_id){
		System.out.println("id : "+playlist_music_id);
		pMapper.increaseCount(playlist_music_id);
		System.out.println("성공");
	}
	
	// music_id 횟수도 늘리기
	public void increaseMusicCount(int music_id){
		System.out.println("music_count 늘리기");
		pMapper.increaseMusicCount(music_id);
	}
	
	// 곡 추가
	public void insertMusic(int playlist_id, int music_id){
		System.out.println("insertMusic");
		System.out.println(playlist_id+", "+music_id);
		
		PlaylistMusicVO vo=new PlaylistMusicVO();
		vo.setMusic_id(music_id);
		vo.setPlaylist_id(playlist_id);
		
		pMapper.insertMusic(vo);
		System.out.println("insert");
	}
	
	/*	마이페이지에서 음악 추천	*/
	// 랜덤 숫자 3개 추출
	public ArrayList<Integer> getRandomNumber(ArrayList<Integer> randomNum, List<PlaylistMusicVO> vo){
		randomNum.add(ThreadLocalRandom.current().nextInt(0, vo.size()));
      
		for(int i=1; i<3; i++){
			int random=ThreadLocalRandom.current().nextInt(0, vo.size());
	    	 
	    	 boolean exists=false;
	    	 
	    	 // 겹치는 숫자 제외
	    	 for(int j=0; j<randomNum.size(); j++){
	    		 if(randomNum.get(j)==random){
	    			 exists=true;
	    			 break;
	    		 }
	    	 }
	    	 if(!exists){
	    		 randomNum.add(random);
	    	 }
	      }
	      System.out.println("랜덤 숫자 : "+randomNum);
	      
	      return randomNum;
	}
	
	// 얻은 랜덤 넘버째 앨범, 곡만 추출
	public List<PlaylistMusicVO> getSendResults(ArrayList<Integer> randomNum, List<PlaylistMusicVO> vo){
		List<PlaylistMusicVO> sendList=new ArrayList<PlaylistMusicVO>();
		
		for(int i=0; i<randomNum.size(); i++){
			sendList.add(vo.get(randomNum.get(i)));
	    }
		return sendList;
	}
	
	// 가장 많이 들은 장르와 동일한 앨범 목록 추출
	public List<PlaylistMusicVO> getAlbumListFromTopGenre(int member_id){
		System.out.println("getAlbumListFromTopGenre");
		return pMapper.getAlbumListFromTopGenre(member_id);
	}
	
	// 가장 많이 들은 아티스트와 관련된 노래 목록 추출
	public List<PlaylistMusicVO> getMusicListFromTopArtist(int member_id){
		System.out.println("getMusicListFromTopArtist");
		return pMapper.getMusicListFromTopArtist(member_id);
	}
	
	// 가장 많이 들은 장르 이름 가져오기
	public FavoriteMusicVO getGenreInfo(int member_id){
		System.out.println("getGenreInfo");
		return pMapper.getGenreInfo(member_id);
	}
	
	// 가장 많이 들은 아티스트 이름 가져오기
	public FavoriteMusicVO getArtistInfo(int member_id){
		System.out.println("getArtistInfo");
		return pMapper.getArtistInfo(member_id);
	}
}
