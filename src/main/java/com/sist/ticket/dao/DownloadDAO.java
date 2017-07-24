package com.sist.ticket.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DownloadDAO {
	@Autowired
	private DownloadMapper downMapper;
	
	public List<DownloadVO> download_list() {
		List<DownloadVO> list = new ArrayList<DownloadVO>();
		
		list = downMapper.download_list(); 
		
		return list;
	}
	
	public DownloadVO select_one_download(int download_id) {
		return downMapper.select_one_download(download_id);
	}
	
	public void insert_buy_download(Buy_downloadVO vo) {
		downMapper.insert_buy_download(vo);
	}
	
	public Buy_downloadVO select_one_buy_download(int member_id) {
		return downMapper.select_one_buy_download(member_id);
	}
	
	public int count_buy_download(int member_id) {
		return downMapper.count_buy_download(member_id);
	}
}
