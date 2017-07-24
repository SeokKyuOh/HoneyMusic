package com.sist.ticket.dao;

import java.sql.Date;

public class Buy_downloadVO {
	private int buy_download_id;
	private String buy_download_start;
	private String buy_download_end;
	private Date buy_download_date;
	private int download_qt;
	private int download_id;
	private int member_id;
	
	//join
	private String download_name;

	public String getDownload_name() {
		return download_name;
	}

	public void setDownload_name(String download_name) {
		this.download_name = download_name;
	}

	public int getBuy_download_id() {
		return buy_download_id;
	}

	public void setBuy_download_id(int buy_download_id) {
		this.buy_download_id = buy_download_id;
	}

	public String getBuy_download_start() {
		return buy_download_start;
	}

	public void setBuy_download_start(String buy_download_start) {
		this.buy_download_start = buy_download_start;
	}

	public String getBuy_download_end() {
		return buy_download_end;
	}

	public void setBuy_download_end(String buy_download_end) {
		this.buy_download_end = buy_download_end;
	}

	public Date getBuy_download_date() {
		return buy_download_date;
	}

	public void setBuy_download_date(Date buy_download_date) {
		this.buy_download_date = buy_download_date;
	}

	public int getDownload_qt() {
		return download_qt;
	}

	public void setDownload_qt(int download_qt) {
		this.download_qt = download_qt;
	}

	public int getDownload_id() {
		return download_id;
	}

	public void setDownload_id(int download_id) {
		this.download_id = download_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

}
