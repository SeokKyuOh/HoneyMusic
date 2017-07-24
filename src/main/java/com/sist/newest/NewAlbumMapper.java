package com.sist.newest;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface NewAlbumMapper {
	@Select("SELECT A.album_id, A.album_art, A.album_name, A.album_artist, "
		   +"A.album_release,A.album_type, M.album_id, "
		   +"M.music_name, M.music_title, (SELECT COUNT(album_id) FROM music " 
		   +"where music.ALBUM_ID =A.album_id group by  A.album_id, A.album_art, A.album_name) COUNT "
		   +"from album A INNER JOIN music M ON M.album_id = A.album_id "
		   +"where m.music_title='y' order by A.ALBUM_RELEASE DESC")
public List<NewAlbumVO> NewAlbumData();	

}
