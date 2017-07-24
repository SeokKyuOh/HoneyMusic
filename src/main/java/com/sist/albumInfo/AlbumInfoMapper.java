package com.sist.albumInfo;





import java.util.List;

import org.apache.ibatis.annotations.Select;



public interface AlbumInfoMapper {
	@Select("SELECT m.ALBUM_ID,m.GENRE_ID,a.ALBUM_ID,a.ALBUM_NAME,a.ALBUM_RELEASE " 
			+",a.ALBUM_ART,a.ALBUM_AGENCY,a.ALBUM_DIST,a.ALBUM_TYPE,a.ALBUM_ARTIST,g.GENRE_NAME,g.GENRE_ID "
			+"FROM MUSIC m, ALBUM a, GENRE g "
			+"WHERE m.ALBUM_ID=a.ALBUM_ID "
			+"and g.GENRE_ID = m.GENRE_ID "
			+"and m.music_title='y' "
			+"and a.ALBUM_ID =#{album_id} "
			+"group by m.ALBUM_ID,m.GENRE_ID,a.ALBUM_ID,a.ALBUM_NAME,a.ALBUM_RELEASE " 
			+",a.ALBUM_ART,a.ALBUM_AGENCY,a.ALBUM_DIST,a.ALBUM_TYPE,a.ALBUM_ARTIST,g.GENRE_NAME,g.GENRE_ID")
	public AlbumInfoVO AlbumInfo(int album_id); 
	
	@Select("SELECT m.MUSIC_ARTIST,m.MUSIC_NAME,m.MUSIC_ID,m.ALBUM_ID "
	+"FROM MUSIC m, ALBUM a "
	+"WHERE m.ALBUM_ID=a.ALBUM_ID "
	+"and a.ALBUM_ID =#{album_id}")
	public List<AlbumMusicVO> AlbumMusic(int album_id);
	
}
