package com.sist.board.dao;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardVO {
	private int board_id;
	private String board_title;
	private String board_writer;
	private String board_content;
	private Date board_regdate;
	private int board_hit;
	private String board_filename;
	private String board_filesize;
	private int board_filecount;
	private int question_id;
	private int boardType_id;
	private List<MultipartFile> upload;

	public List<MultipartFile> getUpload() {
		return upload;
	}

	public void setUpload(List<MultipartFile> upload) {
		this.upload = upload;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public Date getBoard_regdate() {
		return board_regdate;
	}

	public void setBoard_regdate(Date board_regdate) {
		this.board_regdate = board_regdate;
	}

	public int getBoard_hit() {
		return board_hit;
	}

	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}

	public String getBoard_filename() {
		return board_filename;
	}

	public void setBoard_filename(String board_filename) {
		this.board_filename = board_filename;
	}

	public String getBoard_filesize() {
		return board_filesize;
	}

	public void setBoard_filesize(String board_filesize) {
		this.board_filesize = board_filesize;
	}

	public int getBoard_filecount() {
		return board_filecount;
	}

	public void setBoard_filecount(int board_filecount) {
		this.board_filecount = board_filecount;
	}

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public int getBoardType_id() {
		return boardType_id;
	}

	public void setBoardType_id(int boardType_id) {
		this.boardType_id = boardType_id;
	}

}
