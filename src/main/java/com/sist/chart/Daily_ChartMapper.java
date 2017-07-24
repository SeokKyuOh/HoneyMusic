package com.sist.chart;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



public interface Daily_ChartMapper {
@Select("SELECT M.music_id,M.music_number,M.album_id,M.music_name,M.music_artist,A.album_name "
			+",A.album_id,A.album_art,M.MUSIC_COUNT "
			+"FROM music M "
			+"INNER JOIN album A "
			+"ON M.album_id = A.album_id "
			+"order by M.MUSIC_COUNT DESC")	

public List<Daily_ChartVO> Daily_ChartData();
@Select("SELECT M.music_id,M.music_number,M.album_id,M.music_name,M.music_artist,A.album_name "
		+",A.album_id,A.album_art,M.MUSIC_COUNT "
		+"FROM music M "
		+"INNER JOIN album A "
		+"ON M.album_id = A.album_id "
		+"order by M.MUSIC_COUNT DESC")	

public List<Daily_ChartVO> Weekly_ChartData();
@Update("UPDATE music SET "
		 +"music_count=music_count+1 "
		 +"WHERE music_id=#{music_id}")

 public void musicCountIncrement(int music_id);

}
