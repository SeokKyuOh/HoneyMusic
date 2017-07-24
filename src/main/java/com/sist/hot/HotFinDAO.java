package com.sist.hot;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Repository
public class HotFinDAO {
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	
	public HotFinDAO() {
		try {
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.114", 27017)));
			db=mc.getDB("mydb");
			dbc=db.getCollection("hotFin");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void hotFinInsert(HotFinVO vo) {
		try{
			
			BasicDBObject obj=new BasicDBObject();
			
			obj.put("keyword",vo.getKeyword());
			obj.put("count", vo.getCount());	
			obj.put("time", vo.getTime());
			
			dbc.insert(obj);
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			
		}
	}
	
	public List<HotFinVO> hotfinAllData(String keyword) {
		List<HotFinVO> list = new ArrayList<HotFinVO>();
		
		try {
			BasicDBObject where =new BasicDBObject();
			where.put("keyword",keyword);
			DBCursor cursor = dbc.find(where).sort(new BasicDBObject("time", 1));
			
			while(cursor.hasNext()) {
				BasicDBObject obj=(BasicDBObject)cursor.next();
				
				HotFinVO vo = new HotFinVO();
				
				vo.setKeyword(obj.getString("keyword"));
				vo.setCount(obj.getInt("count"));
				vo.setTime(obj.getString("time"));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
}
