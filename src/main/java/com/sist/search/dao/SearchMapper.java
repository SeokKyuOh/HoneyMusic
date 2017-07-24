package com.sist.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.playlist.dao.AlbumVO;
import com.sist.playlist.dao.MusicVO;

public interface SearchMapper {
	//아티스트 검색
	@Select("SELECT m.music_name, m.music_artist, a.album_name, m.music_id, a.album_art "
			+ "FROM music m, album a "
			+ "WHERE m.album_id = a.album_id and m.music_artist LIKE '%'||#{search}||'%'")
	public List<MusicVO> artistData(String music_search);
		
	//곡 검색
	@Select("SELECT m.music_name, m.music_artist, a.album_name, m.music_id, a.album_art "
			+ "FROM music m, album a WHERE m.album_id = a.album_id and m.music_name LIKE '%'||#{search}||'%'")
	public List<MusicVO> songData(String music_search);
	
	//앨범 검색
	@Select("SELECT DISTINCT a.album_name, a.album_release, a.album_type, a.album_art, a.album_artist "
			+ "FROM music m, album a "
			+ "WHERE m.album_id = a.album_id AND (a.album_name LIKE '%'||#{search}||'%' OR a.album_artist LIKE '%'||#{search}||'%' OR m.music_name LIKE '%'||#{search}||'%')")
	public List<AlbumVO> albumData(String music_search);
	
}
