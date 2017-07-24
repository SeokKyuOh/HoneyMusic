package com.sist.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.board.dao.QuestionVO;
import com.sist.playlist.dao.MusicVO;
import com.sist.ticket.dao.Buy_downloadVO;
import com.sist.ticket.dao.Buy_streamingVO;

public interface MemberMapper {
	// 비밀번호 확인
	@Select("SELECT member_pwd FROM members WHERE member_nick=#{member_nick}")
	public String memberGetPwd(String member_nick);

	// 로그인 (중복) 체크 결과값 0=없음, 1=있음
	@Select("SELECT COUNT(*) FROM members WHERE member_nick=#{member_nick}")
	public int memberIdCheck(String member_nick);

	/*
	 * //로그인정보-yk
	 * 
	 * @Select(
	 * "SELECT member_name, member_nick FROM members WHERE member_nick=#{member_nick}"
	 * ) public MemberVO memberInfoData(String member_nick);
	 */

	//로그인전체정보-yk
	@Select("SELECT member_id,member_name, member_nick, member_phone, member_email, member_addr, member_nick, member_pwd, TO_CHAR(member_birthdate,'yyyy-MM-dd') as member_birthdate,member_gender,member_regdate,member_cash,member_post FROM members WHERE member_nick=#{member_nick}")
	public MemberVO memberAllData(String member_nick);
	
	//마이페이지 문의내역 요약정보(타이틀, 작성날짜만)-yk
	@Select("SELECT question_title, TO_CHAR(question_regdate,'yyyy-MM-dd') as question_regdate FROM question q WHERE q.member_id=#{member_id}")
	public List<QuestionVO> mypageQuestionSummary(int id);
	
	//마이페이지 구매내역 정보 출력(스트리밍)-yk
	@Select("SELECT s.streaming_name, TO_CHAR(bs.buy_streaming_start, 'yyyy-MM-dd') as buy_streaming_start, TO_CHAR(bs.buy_streaming_end, 'yyyy-MM-dd') as buy_streaming_end FROM buy_streaming bs, streaming s WHERE bs.member_id=#{member_id} and s.streaming_id = bs.streaming_id ORDER BY bs.buy_streaming_start ASC")
	public List<Buy_streamingVO> mypageStreamingInfo(int id);
	
	//마이페이지 구매내역 정보 출력(다운로드)-yk
	@Select("SELECT d.download_name, TO_CHAR(bd.buy_download_start,'yyyy-MM-dd') as buy_download_start, TO_CHAR(bd.buy_download_end,'yyyy-MM-dd') as buy_download_end FROM buy_download bd, download d WHERE bd.member_id=#{member_id} and d.download_id = bd.download_id ORDER BY bd.buy_download_start ASC")
	public List<Buy_downloadVO> mypageDownloadInfo(int id);
	
	//마이페이지 플레이리스트 정보 출력-yk
	@Select("SELECT m.music_name, m.music_artist, p.member_id, pm.playlist_music_id FROM playlist_music pm, music m, playlist p WHERE p.member_id=#{member_id} and pm.music_id=m.music_id and pm.playlist_id=p.playlist_id ORDER BY pm.playlist_music_id")
	public List<MusicVO> mypagePlayList(int id);
	
	//회원 정보 수정(업데이트)-yk
	@Update("UPDATE members SET member_pwd=#{member_pwd}, member_email=#{member_email}, member_name=#{member_name}, member_gender=#{member_gender}, member_phone=#{member_phone}, member_birthdate=#{member_birthdate}, member_post=#{member_post}, member_addr=#{member_addr} WHERE member_id=#{member_id}")
	public void memberUpdate(MemberVO vo);
			
	//마이페이지에서 내 플레이리스트 곡 하나 삭제-yk
	@Delete("DELETE FROM playlist_music WHERE playlist_music_id=#{playlist_music_id}")
	public void myPlaylistDelete(int playlist_music_id);
	
	//마이페이지에서 내 플레이리스트 장르 점유율 출력
	@Select("SELECT g.genre_name, COUNT(*) AS cnt FROM genre g, music m, playlist_music pm, playlist p WHERE p.member_id=#{member_id} "
			+ "AND p.playlist_id=pm.playlist_id AND pm.music_id=m.music_id AND m.genre_id=g.genre_id GROUP BY g.genre_name")
	public List<MusicVO> mypagePlayListGraph(int id);
	
	// 주소입력
	@Select("SELECT zipcode,sido,gugun,dong,NVL(bunji, ' ') as bunji FROM zipcode "
			+ "WHERE dong LIKE '%'||#{dong}||'%'")
	public List<ZipcodeVO> zipcodeListData(String dong);

	// 회원가입 입력
	@Insert("INSERT INTO members VALUES("
				+ "members_seq.nextval, #{member_name}, #{member_phone}, #{member_email}, #{member_addr}, #{member_nick}, #{member_pwd},#{member_birthdate}, #{member_gender}, SYSDATE, 0, #{member_post})")
	public void memberInsert(MemberVO vo);
	
}
