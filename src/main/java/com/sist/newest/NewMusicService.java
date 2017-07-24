package com.sist.newest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.chart.Daily_ChartMapper;
import com.sist.chart.Daily_ChartVO;


@Service
public class NewMusicService {
	@Autowired
	private NewMusicMapper nMapper;

	@Autowired 
	private NewAlbumMapper aMapper;
	
	public List<NewMusicVO> NewMusicData()
	{
		return nMapper.NewMusicData();
	}
	
	@Autowired 
	public List<NewAlbumVO> NewAlbumData()
	{
		return aMapper.NewAlbumData();
	}
	

}
