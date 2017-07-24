package com.sist.board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDAO {

	@Autowired
	private QuestionMapper qMapper;

	// 질문리스트
	public List<QuestionVO> questionList(int member_id) {
		return qMapper.questionList(member_id);

	}

	// 질문내용보이기
	public QuestionVO questionContent(int question_id) {

		return qMapper.questionContent(question_id);
	}

	// 글수정
	public void questionUpdate(QuestionVO vo) {
		qMapper.questionUpdate(vo);

	}

	// insert
	public void questionInsert(QuestionVO vo) {
		System.out.println("질문 dao");
		qMapper.questionInsert(vo);
	}

	public void questionDelete(int question_id) {

		qMapper.questionDelete(question_id);
	}
}
