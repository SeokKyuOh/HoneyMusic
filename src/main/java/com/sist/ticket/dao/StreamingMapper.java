package com.sist.ticket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface StreamingMapper {
	@Select("select * from streaming")
	public List<StreamingVO> streaming_list();
	
	@SelectKey(keyProperty="buy_streaming_id", resultType=int.class, before=true, statement="SELECT NVL(MAX(buy_streaming_id)+1,1) as buy_streaming_id FROM buy_streaming")
	@Insert("insert into buy_streaming values(#{buy_streaming_id}, TO_DATE(#{buy_streaming_start}, 'yyyy/mm/dd hh24:mi:ss'), TO_DATE(#{buy_streaming_end}, 'yyyy/mm/dd hh24:mi:ss'), sysdate, #{streaming_id}, #{member_id})")
	public void insert_buy_streaming(Buy_streamingVO vo);
	
	@Select("select * from streaming where streaming_id = #{streaming_id}")
	public StreamingVO select_one_streaming(int streaming_id);
	
	@Select("select count(*) from buy_streaming where member_id = #{member_id}")
	public int count_buy_streaming(int member_id);
	
	@Select("select buy_streaming_id, streaming_id, member_id, TO_CHAR(buy_streaming_start, 'YYYY/MM/DD') as buy_streaming_start, TO_CHAR(buy_streaming_end, 'YYYY/MM/DD') as buy_streaming_end, buy_streaming_date "
			+ "from buy_streaming where member_id = #{member_id}")
	public Buy_streamingVO select_one_buy_streaming(int member_id);
}
