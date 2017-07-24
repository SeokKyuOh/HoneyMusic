package com.sist.genre;

import java.util.List;

import org.apache.ibatis.annotations.Select;



public interface GenreMapper {
	@Select("SELECT m.MUSIC_ID,m.MUSIC_NAME,m.MUSIC_ARTIST,m.ALBUM_ID,m.GENRE_ID,a.ALBUM_ID,a.ALBUM_NAME,a.ALBUM_ART,g.GENRE_NAME,g.GENRE_ID "
			+"FROM MUSIC m, ALBUM a, GENRE g "
			+"WHERE m.ALBUM_ID=a.ALBUM_ID "
			+"and g.GENRE_ID = m.GENRE_ID "
			+"and g.GENRE_ID=#{genre_id}")
	public List<GenreVO> GenreData(int genre_id);	
	@Select("SELECT DISTINCT m.MUSIC_ARTIST,m.ALBUM_ID,m.GENRE_ID,a.ALBUM_ID,a.ALBUM_NAME,a.ALBUM_ART,g.GENRE_ID "
			+"FROM MUSIC m, ALBUM a, GENRE g "
			+"WHERE m.ALBUM_ID=a.ALBUM_ID "
			+"and g.GENRE_ID = m.GENRE_ID "
			+"and g.GENRE_ID=#{genre_id}")
	public List<GenreVO> GenreAlbum(int genre_id);
}
