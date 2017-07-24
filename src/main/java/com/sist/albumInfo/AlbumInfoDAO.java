package com.sist.albumInfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumInfoDAO {
	@Autowired
	private AlbumInfoMapper aiMapper;
	
	public AlbumInfoVO albumInfo(int album_id){
		return aiMapper.AlbumInfo(album_id);
		
	}
	public List<AlbumMusicVO> albumMusic(int album_id){
		return aiMapper.AlbumMusic(album_id);
		
	}
}
