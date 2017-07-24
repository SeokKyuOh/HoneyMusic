package com.sist.ticket.dao;

import java.util.Date;

public class Buy_streamingVO {
	private int buy_streaming_id;
	private String buy_streaming_start;
	private String buy_streaming_end;
	private Date buy_streaming_date;
	private int streaming_id;
	private int member_id;
	
	//join
	private String streaming_name;
	
	public String getStreaming_name() {
		return streaming_name;
	}

	public void setStreaming_name(String streaming_name) {
		this.streaming_name = streaming_name;
	}

	public int getBuy_streaming_id() {
		return buy_streaming_id;
	}

	public void setBuy_streaming_id(int buy_streaming_id) {
		this.buy_streaming_id = buy_streaming_id;
	}

	public String getBuy_streaming_start() {
		return buy_streaming_start;
	}

	public void setBuy_streaming_start(String buy_streaming_start) {
		this.buy_streaming_start = buy_streaming_start;
	}

	public String getBuy_streaming_end() {
		return buy_streaming_end;
	}

	public void setBuy_streaming_end(String buy_streaming_end) {
		this.buy_streaming_end = buy_streaming_end;
	}

	public Date getBuy_streaming_date() {
		return buy_streaming_date;
	}

	public void setBuy_streaming_date(Date buy_streaming_date) {
		this.buy_streaming_date = buy_streaming_date;
	}

	public int getStreaming_id() {
		return streaming_id;
	}

	public void setStreaming_id(int streaming_id) {
		this.streaming_id = streaming_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

}
