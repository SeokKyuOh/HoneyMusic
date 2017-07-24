package com.sist.board.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository
public class BoardDAO {
	@Autowired
	private BoardMapper boardMapper;
	
	//먼저 공지사항 볼 수있는 게시판
	//boardType_id 가 1인 것으로 가져오기 
	public List<BoardVO> boardList(int boardType_id){
		return boardMapper.boardList(boardType_id);
		
	}
	//업데이트
	public BoardVO boardContent(int board_id){
		boardMapper.boardHitIncrement(board_id);
		return boardMapper.boardContent(board_id);
	}
	
	public void boardUpdate(int board_id){
		boardMapper.boardUpdate(board_id);
		
	}
	public BoardVO boardUpdateData(int board_id){
		return boardMapper.boardContent(board_id);
	}
	

	public BoardVO boardGetFileInfo(int board_id){
		return boardMapper.boardGetFileInfo(board_id);
	}
	//insert
	public void boardInsert(BoardVO vo){
		boardMapper.boardInsert(vo);
	}
	
	

	//delete
	public void boardDelete(int board_id){
		
		boardMapper.boardDelete(board_id);
	}
}
