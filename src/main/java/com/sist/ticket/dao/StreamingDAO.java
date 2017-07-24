package com.sist.ticket.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StreamingDAO {
	@Autowired
	private StreamingMapper sMapper;
	
	public List<StreamingVO> streaming_list() {
		List<StreamingVO> list = new ArrayList<StreamingVO>();
		
		list = sMapper.streaming_list(); 
		
		return list;
	}
	
	public StreamingVO select_one_streaming(int streaming_id) {
		return sMapper.select_one_streaming(streaming_id);
	}
	
	public void insert_buy_streaming(Buy_streamingVO vo) {
		sMapper.insert_buy_streaming(vo);
	}
	
	public Buy_streamingVO select_one_buy_streaming(int member_id) {
		Buy_streamingVO vo = sMapper.select_one_buy_streaming(member_id);
		return vo;
	}
	
	public int count_buy_streaming(int member_id) {
		return sMapper.count_buy_streaming(member_id);
	}

}
