package com.sist.recommand;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.sist.recommand.MusicVO;
@Repository
public class RecommandDAO {
	@Autowired
	private MongoTemplate mt;
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	private String type;
	
	public RecommandDAO(){
		this.type=type;
		mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.101",27017)));
		db=mc.getDB("mydb");
		dbc=db.getCollection("honeymusic");
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
		obj.put("artist", vo.getArtist());
		obj.put("albumname", vo.getAlbumname());
		
		dbc.insert(obj);
	}
	
	public List<String> musicTitleAllData(){
	   List<String> list=new ArrayList<String>();
	   try {
		   DBCursor cursor=dbc.find();
		   while(cursor.hasNext()) {
			   BasicDBObject obj=(BasicDBObject)cursor.next();
			   String data=obj.getString("title");
			   data=data.replace("?", "");
			   int index = data.indexOf("(");
			   
			   if(index != -1) {
				   data = data.substring(0, data.indexOf("("));
				   data=data.trim();
				   System.out.println(data);		   
			   }
			   
			   if(data.trim().length()>1 && !data.equals("노래")){
			     list.add(data.trim());
			   }
			  
			  
			   	
		   }
	   }catch(Exception ex){
		   System.out.println(ex.getMessage());
	   }
	   return list;
	}
	
	public MusicVO musicRecommandData(String title)
	   {
		MusicVO list = new MusicVO();
		   BasicQuery query=new BasicQuery("{title:'"+title+"'}");
		   list=mt.findOne(query,MusicVO.class,"honeymusic");
		   return list;
	   }
}
