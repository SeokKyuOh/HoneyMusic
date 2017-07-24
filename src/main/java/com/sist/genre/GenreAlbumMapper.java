package com.sist.genre;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface GenreAlbumMapper {
	@Select("SELECT A.album_id, A.album_art, A.album_name, A.album_artist, "
		   +"A.album_release,A.album_type, M.album_id, "
		   +"M.music_name, M.music_title,g.GENRE_NAME,g.GENRE_ID, (SELECT COUNT(album_id) FROM music " 
		   +"where music.ALBUM_ID =A.album_id group by  A.album_id, A.album_art, A.album_name) COUNT "
			+"FROM MUSIC m, ALBUM a, GENRE g "
			+"WHERE m.ALBUM_ID=a.ALBUM_ID "
			+"and g.GENRE_ID = m.GENRE_ID "
			+"and g.GENRE_ID=#{genre_id}"
			+"and m.music_title='y' order by A.ALBUM_RELEASE DESC")
	public List<GenreVO> GenreAlbumList(int genre_id);	
}



