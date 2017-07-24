package com.sist.chart;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.member.dao.MemberMapper;

@Service
public class ChartService {
	@Autowired
	private Daily_ChartMapper dMapper;

	public List<Daily_ChartVO> Daily_ChartData()
	{
		return dMapper.Daily_ChartData();
	}
	public List<Daily_ChartVO> Weekly_ChartData()
	{
		return dMapper.Weekly_ChartData();
	}
	public void musicCountIncrement(int music_id)
	{
		dMapper.musicCountIncrement(music_id);
	}

}
