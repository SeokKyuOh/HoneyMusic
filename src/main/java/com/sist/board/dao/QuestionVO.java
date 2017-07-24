package com.sist.board.dao;

import java.util.Date;

public class QuestionVO {
	private int question_id;
	private String  question_title;
	private String  question_content;
	private Date  question_regdate;
	private int  question_hit;
	private String  question_filename;
	private String  question_filesize;
	private int  question_filecount;
	private int member_id;
	
	
	
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getQuestion_content() {
		return question_content;
	}
	public void setQuestion_content(String question_content) {
		this.question_content = question_content;
	}
	public Date getQuestion_regdate() {
		return question_regdate;
	}
	public void setQuestion_regdate(Date question_regdate) {
		this.question_regdate = question_regdate;
	}
	public int getQuestion_hit() {
		return question_hit;
	}
	public void setQuestion_hit(int question_hit) {
		this.question_hit = question_hit;
	}
	public String getQuestion_filename() {
		return question_filename;
	}
	public void setQuestion_filename(String question_filename) {
		this.question_filename = question_filename;
	}
	public String getQuestion_filesize() {
		return question_filesize;
	}
	public void setQuestion_filesize(String question_filesize) {
		this.question_filesize = question_filesize;
	}
	public int getQuestion_filecount() {
		return question_filecount;
	}
	public void setQuestion_filecount(int question_filecount) {
		this.question_filecount = question_filecount;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	
	
}
