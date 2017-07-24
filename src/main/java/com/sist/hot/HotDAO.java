package com.sist.hot;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.math3.genetics.NPointCrossover;
import org.apache.spark.scheduler.HostTaskLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Repository
public class HotDAO {
	private MongoClient mc;
	private DB db;
	private DBCollection dbc;
	
	@Autowired
	private HotFinDAO dao;
	
	int[] chkInsert = new int[30];
	
	public HotDAO() {
		try {
			mc=new MongoClient(new ServerAddress(new InetSocketAddress("211.238.142.114", 27017)));
			db=mc.getDB("mydb");
			dbc=db.getCollection("hot");
			
			for(int i = 0; i < chkInsert.length; i++) {
				chkInsert[i] = 0;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void hotInsert(HotVO vo) {
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
	
	public void musicAllData(){
		/*
		 * date 비 교안하려면 fdate, res를 매개변수로 전달 받아야한다. 
		 */
		String[] data={
				"지코",
				"아이유",
				"레드벨벳",
				"트와이스",
				"엑소"
		};
		
		List<HotVO> list = new ArrayList<HotVO>();
		
		//HotVO hotList = new HotVO();

		try {
			Date now  = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			
			String nowDate = sdf.format(now);
			
			nowDate = nowDate.substring(11, 16);
			
			System.out.println("nowDate" + nowDate);
			
			String hour = nowDate.substring(0, 2);
			
			String[] fdate = new String[30];
			
			for (int i = 0; i < fdate.length; i++) {
				if(i > 4) {
					fdate[i] = hour + ":" + (i * 2);
				}
				else {
					fdate[i] = hour + ":0" + (i * 2);
				}
				
				
				System.out.println(fdate[i]);
			}
			
			boolean chk = false;
			int res = -1;
			
			for(int i = 0; i < fdate.length; i++){
				if(nowDate.compareTo(fdate[i]) == 0) {
					chk = true;
					res = i;
				}
			}
			System.out.println(chk);
			
			if(chk) {
				DBCursor cursor=dbc.find();
				
				
				while(cursor.hasNext()) {
					BasicDBObject obj=(BasicDBObject)cursor.next();
					
					String dbDate = obj.getString("time");
					
					dbDate = dbDate.substring(11, 16);
					
					if(res > 0) {
						if(fdate[res].compareTo(dbDate) >= 0 && fdate[res-1].compareTo(dbDate) < 0) {
							HotVO vo = new HotVO();

							vo.setKeyword(obj.getString("keyword"));
							vo.setCount(obj.getInt("count"));
							vo.setTime(obj.getString("time"));
							
							list.add(vo);
						} 
					} else if(res == 0) {
						String s = (Integer.parseInt(hour) - 1) + ":55";
						
						if(fdate[res].compareTo(dbDate) >= 0 && s.compareTo(dbDate) < 0) {
							HotVO vo = new HotVO();

							vo.setKeyword(obj.getString("keyword"));
							vo.setCount(obj.getInt("count"));
							vo.setTime(obj.getString("time"));
							
							list.add(vo);
						} 
					}
				}
			}
			
			BasicDBObject o = new BasicDBObject();
			dbc.remove(o);
			

			int[] sum = {0,0,0,0,0};
			
			for (HotVO vo : list) {
				if(vo.getKeyword().equals("지코")) {
					sum[0] += vo.getCount();
					System.out.println("지코 = " + sum[0]);
				} else if(vo.getKeyword().equals("아이유")) {
					sum[1] += vo.getCount();
					System.out.println("아이유 = " + sum[1]);
				} else if(vo.getKeyword().equals("레드벨벳")) {
					sum[2] += vo.getCount();
					System.out.println("레드벨벳 = " + sum[2]);
				} else if(vo.getKeyword().equals("트와이스")) {
					sum[3] += vo.getCount();
					System.out.println("트와이스 = " + sum[3]);
				}  else if(vo.getKeyword().equals("엑소")) {
					sum[4] += vo.getCount();
					System.out.println("엑소 = " + sum[4]);
				}
			}
			
			/*int[] chkInsert = new int[30];
			
			for(int i = 0; i < chkInsert.length; i++) {
				chkInsert[i] = 0;
			}*/
			
			boolean[] checkArr = new boolean[chkInsert.length];
			
			for(int i = 0; i < checkArr.length; i++) {
				checkArr[i] = false;
			}
			
			for(int i = 0; i < chkInsert.length; i++) {
				System.out.println(chkInsert[i]);
				if(chkInsert[i] == 1) {
					checkArr[i] = true;
				}
			}
			
			int isAllTrue = 0;
			for(int i = 0; i < checkArr.length; i++) {
				if(checkArr[i] == false) {
					isAllTrue = 1;
					break;
				}
			}
			
			if(isAllTrue == 0) {
				for(int i = 0; i < chkInsert.length; i++) {
					chkInsert[i] = 0;
				}
			}
			
			if(chkInsert[res] == 0) {
				chkInsert[res] = 1;
				for(int i = 0; i < data.length; i++) {
					HotFinVO vo = new HotFinVO();
					
					vo.setKeyword(data[i]);
					vo.setCount(sum[i]);
					vo.setTime(fdate[res]);
					
					
					dao.hotFinInsert(vo);
				}				
			}
			

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		//return hotList;
		
	}
}
