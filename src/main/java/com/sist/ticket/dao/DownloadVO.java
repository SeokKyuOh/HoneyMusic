package com.sist.ticket.dao;

public class DownloadVO {
	private int download_id;
	private String download_name;
	private int download_qt;
	private int download_period;
	private int download_price;

	public int getDownload_id() {
		return download_id;
	}

	public void setDownload_id(int download_id) {
		this.download_id = download_id;
	}

	public String getDownload_name() {
		return download_name;
	}

	public void setDownload_name(String download_name) {
		this.download_name = download_name;
	}

	public int getDownload_qt() {
		return download_qt;
	}

	public void setDownload_qt(int download_qt) {
		this.download_qt = download_qt;
	}

	public int getDownload_period() {
		return download_period;
	}

	public void setDownload_period(int download_period) {
		this.download_period = download_period;
	}

	public int getDownload_price() {
		return download_price;
	}

	public void setDownload_price(int download_price) {
		this.download_price = download_price;
	}

}
