package com.sist.member.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.board.dao.QuestionVO;
import com.sist.playlist.dao.MusicVO;
import com.sist.ticket.dao.Buy_downloadVO;
import com.sist.ticket.dao.Buy_streamingVO;

@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mMapper;
	
	public String memberGetPwd(String member_nick){
		return mMapper.memberGetPwd(member_nick);
	}
	
	public int memberIdCheck(String member_nick){
		return mMapper.memberIdCheck(member_nick);
	}
	
	public MemberVO memberAllData(String member_nick){
		return mMapper.memberAllData(member_nick);
	}
	
	public List<ZipcodeVO> zipcodeListData(String dong){
		return mMapper.zipcodeListData(dong);
	}
	
	public void memberInsert(MemberVO vo){
		mMapper.memberInsert(vo);
	}
	
	public List<QuestionVO> mypageQuestionSummary(int id){
		return mMapper.mypageQuestionSummary(id);
	}
	
	public List<Buy_streamingVO> mypageStreamingInfo(int id){
		return mMapper.mypageStreamingInfo(id);
	}
	public List<Buy_downloadVO> mypageDownloadInfo(int id){
		return mMapper.mypageDownloadInfo(id);
	}
	
	public List<MusicVO> mypagePlayList(int id){
		return mMapper.mypagePlayList(id);
	}
	
	public void memberUpdate(MemberVO vo){
		mMapper.memberUpdate(vo);
	}
	
	public void myPlaylistDelete(int playlist_music_id){
		mMapper.myPlaylistDelete(playlist_music_id);
	}
	
	//추가(석규)
	public List<MusicVO> mypagePlayListGraph(int id){
		return mMapper.mypagePlayListGraph(id);
	}
	
}

