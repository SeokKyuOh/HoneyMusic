package com.sist.chartfinder.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import au.com.bytecode.opencsv.CSVReader;

public class ChartFinderDAO {
	@Autowired
	private MongoTemplate mt;
	
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	private String type;
	
	public ChartFinderDAO(String type){
		this.type=type;
		mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.101",27017)));
		db=mc.getDB("mydb");
		dbc=db.getCollection(type);
	}
	public void dropTable(){
		dbc.drop();
	}
	
	public void musicInsert(MusicVO vo){
		int no=0;
		DBCursor cursor=dbc.find();
		while(cursor.hasNext()){
			BasicDBObject obj=(BasicDBObject)cursor.next();
			int i=obj.getInt("no");
			if(no<i) no=i;
		}
		cursor.close();
		BasicDBObject obj=new BasicDBObject();
		obj.put("no", no+1);
		obj.put("rank", vo.getRank());
		obj.put("title", vo.getTitle());
		obj.put("singer", vo.getSinger());
		
		dbc.insert(obj);
	}
	
	public List<MusicVO> getMusicData(){
		List<MusicVO> list=new ArrayList<MusicVO>();
		DBCursor cursor=dbc.find();
		String csv="";
		while(cursor.hasNext()){
			BasicDBObject obj=(BasicDBObject)cursor.next();
			MusicVO vo=new MusicVO();
			vo.setRank(obj.getInt("rank"));
			vo.setTitle(obj.getString("title"));
			vo.setSinger(obj.getString("singer"));
			list.add(vo);
			csv+=vo.getRank()+","+vo.getTitle()+"\n";
		}
		cursor.close();
		csv=csv.substring(0,csv.lastIndexOf("\n"));
		try {
			FileWriter fw=new FileWriter("./honey_data/"+type+".csv"); //music-data폴더는 미리 만들어져있어야함
			fw.write(csv);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void myRankInsert(){
		
		try {
			dbc.drop();
			FileReader fr = new FileReader("./honey_data/genie-melon-mnet/myrank.csv");
		
			String data="";
			int i=0;
			while((i=fr.read())!=-1){
				data+=String.valueOf((char)i);
			}
			fr.close();
			String[] temp=data.split("\n");
			for(String s:temp){
				CSVReader csv=new CSVReader(new StringReader(s));
				String[] ss=csv.readNext();
				BasicDBObject obj=new BasicDBObject();
				obj.put("title", ss[0]);
				obj.put("rating", (150-(Integer.parseInt(ss[1].trim())+Integer.parseInt(ss[2].trim())+Integer.parseInt(ss[3].trim())))/3);
				dbc.insert(obj);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
