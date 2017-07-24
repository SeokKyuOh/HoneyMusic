package com.sist.newest;

import java.util.List;

import org.apache.ibatis.annotations.Select;



public interface NewMusicMapper {
	@Select("SELECT M.music_id,M.music_number,M.album_id,M.music_name,M.music_artist,A.album_name,A.album_id,A.album_art,A.album_release "
			+"FROM music M "
			+"INNER JOIN album A "
			+"ON M.album_id = A.album_id "
			+"order by A.album_release DESC")	
public List<NewMusicVO> NewMusicData();

}
