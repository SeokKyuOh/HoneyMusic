package com.sist.genre;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class GenreService {
	@Autowired
	private GenreMapper gMapper;
	@Autowired
	private GenreAlbumMapper gaMapper;
	
	public List<GenreVO> genreListData(int genre_id)
	{
		return gMapper.GenreData(genre_id);
	}
	public List<GenreVO> genreAlbumData(int genre_id)
	{
		return gMapper.GenreAlbum(genre_id);
	}
	public List<GenreVO> genreAlbumList(int genre_id)
	{
		return gaMapper.GenreAlbumList(genre_id);
	}
}
