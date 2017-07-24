package com.sist.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface QuestionMapper {
	//공지사항 (자기꺼 쓴 거 볼 수있게)
	@Select("SELECT * FROM question WHERE member_id=#{member_id}")
	public List<QuestionVO> questionList(int member_id);
	
	
	//질문내용보이기
	@Select("SELECT question_id,question_title,question_content "
			+"FROM question WHERE question_id=#{question_id}")
	public QuestionVO questionContent(int question_id);

	
	
	//수정하기
		@Update("UPDATE question SET "
				+"question_title=#{question_title},question_content=#{question_content} "
				+"WHERE question_id=#{question_id}")
		public void questionUpdate(QuestionVO vo);


		  //board_id, 'board_title', 'board_writer', board_content, board_regdate, board_hit, 'board_filename', 'board_filesize', board_filecount, question_id, boardtype_id
		  //글쓰기
	        @Insert("INSERT INTO question VALUES("
	                +"question_seq.nextval,#{question_title},#{question_content},SYSDATE,0,#{question_filename},#{question_filesize},#{question_filecount},#{member_id})")
	               
	           public void questionInsert(QuestionVO vo);
	        //board_id, 'board_title', 'board_writer', board_content, board_regdate, board_hit, 'board_filename', 'board_filesize', board_filecount, question_id, boardtype_id

	        //삭제하기
	        @Delete("DELETE FROM question "
	              + "WHERE question_id=#{question_id}")
	        public void questionDelete(int question_id);
	        
}