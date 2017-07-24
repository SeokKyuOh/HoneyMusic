package com.sist.chartfinder.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RankDAO {
	@Autowired
	private MongoTemplate mt;
	
	public List<MusicVO> getMyRankData(){
		List<MusicVO> list=new ArrayList<MusicVO>();
		Query query=new Query();
		query.with(new Sort(Sort.Direction.DESC, "rating")); //rating을 desc로 정렬.순위 결정하려고. 
		list=(List<MusicVO>)mt.find(query, MusicVO.class,"myrank");
		int i=1;
		for(MusicVO vo:list){
			vo.setRank(i);
			i++;
		}
		return list;
	}
	public List<Integer> getMusicRating(String table){
		List<Integer> list=new ArrayList<Integer>();
		String[] title=new String[5];
		List<MusicVO> mList=getMyRankData();
		int i=0;
		for(MusicVO vo:mList){
			if(i<5){
				title[i]=vo.getTitle();
				i++;
			}
		}
		for(int j=0;j<title.length;j++){
			String temp=title[j].substring(0, 3);
			BasicQuery query=new BasicQuery("{title:{$regex:'^"+temp.trim()+"'}}");
			MusicVO vo=(MusicVO)mt.findOne(query, MusicVO.class,table);
			list.add(50-vo.getRank());
		}
		return list;
	}
	
	public static void myCreateCSV3(){
		try {
			FileReader fr=new FileReader("./honey_data/genie-melon-mnet/part-00000");
			String data="";
			int i=0;
			while((i=fr.read())!=-1){
				data+=String.valueOf((char)i);
			}
			fr.close();
			data=data.replace("(", "");
			data=data.replace(")", "");
			FileWriter fw=new FileWriter("./honey_data/genie-melon-mnet/myrank.csv");
			fw.write(data);
			fw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
