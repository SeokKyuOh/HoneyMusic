package com.sist.search.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.playlist.dao.AlbumVO;
import com.sist.playlist.dao.MusicVO;

@Repository
public class SearchDAO {
	@Autowired
	private SearchMapper sMapper;
	
	public List<MusicVO> artistData(String music_search){
		return sMapper.artistData(music_search);
	}
	
	public List<MusicVO> songData(String music_search){
		return sMapper.songData(music_search);
	}
	
	public List<AlbumVO> albumData(String music_search){
		return sMapper.albumData(music_search);
	}
}
