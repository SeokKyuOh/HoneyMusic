package com.sist.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;
import com.sist.hot.HotDAO;
import com.sist.hot.HotFinDAO;
import com.sist.hot.HotFinVO;
import com.sist.hot.HotVO;

@Controller
public class HotController {
	@Autowired
	private Configuration conf;
	
	@Autowired
	private HotDAO dao;
	
	@Autowired
	private HotFinDAO hfdao;
	
	String[] data={
			"지코",
			"아이유",
			"레드벨벳",
			"트와이스",
			"엑소"
	};
	
	@RequestMapping("main/graph.do")
	public String main_graph(Model model) {
		hadoopFileRead();
		
		Date now  = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		
		String nowDate = sdf.format(now);
		
		nowDate = nowDate.substring(11, 16);
		
		System.out.println("nowDate = " + nowDate);
		
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
			System.out.println(nowDate + " ^^^ " + fdate[i]);
			if(nowDate.compareTo(fdate[i]) == 0) {
				chk = true;
				res = i;
			}
		}
		System.out.println(chk);

		
		if(chk) {
			dao.musicAllData();
		}

		JSONArray arr = new JSONArray();
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		
		for(int j = 0; j < data.length; j++) {
			JSONObject obj=new JSONObject();
			
			List<HotFinVO> list = hfdao.hotfinAllData(data[j]);
			
			sb.append("{");
			sb.append("name:'" + data[j] + "',");
			sb.append("data:[");
			
			int size = list.size();
			int i = 0;
			
			for(HotFinVO vo : list) {
				if(i < size -1) {
					sb.append(vo.getCount() + ",");	
				}
				else {
					sb.append(vo.getCount());					
				}
				i++;
			}
			
			if(j < data.length-1) {
				sb.append("]},");				
			}
			else {
				sb.append("]}");
			}
			
		}
		sb.append("]");
		
		System.out.println(sb.toString());
		System.out.println(hour);
		
		model.addAttribute("json",sb.toString());
		model.addAttribute("hour", hour);
		
		
		return "main/graph";
		
	}

	public void hadoopFileRead(){
		try {
			FileSystem fs= FileSystem.get(conf);
			FileStatus[] status=fs.listStatus(new Path("/usr/hadoop/melon"));
			for(FileStatus sss:status){//자동으로 만들어진 파일
				
				String temp=sss.getPath().getName();
				if(!temp.startsWith("hot")){ //music으로 된 폴더가 아니면 지운다
					continue;
					
				}
				
				FileStatus[] status2 = fs.listStatus(new Path("/usr/hadoop/melon/"+sss.getPath().getName()));//폴더갖고오기
				for(FileStatus ss:status2){
					//part-00000존재
					String name= ss.getPath().getName();//폴더
					
					//success파일왜뻄 
					if(!name.equals("_SUCCESS")){
						FSDataInputStream is=fs.open(new Path("/usr/hadoop/melon/"+sss.getPath().getName()+"/"+ss.getPath().getName()));
						
						BufferedReader br=new BufferedReader(new InputStreamReader(is));
						while(true){
							String line=br.readLine();
							if(line==null) break;
							StringTokenizer st = new StringTokenizer(line); //제목에 공백 대신 $ 준 이유 
							HotVO vo = new HotVO();
							vo.setKeyword(st.nextToken().trim().replace("$"," "));
							vo.setCount(Integer.parseInt(st.nextToken().trim()));
							
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
							Date date = new Date();
							
							String s = sdf.format(date);
							
							vo.setTime(s);
							
							dao.hotInsert(vo);
								
						}
						br.close();
					}
				}
				fs.delete(new Path("/usr/hadoop/melon/"+sss.getPath().getName()),true);//나중에 삭제
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@PostConstruct //메모리 할당 하자마자 (init-method들어가는 부분) 객체생성하자마자 호출할수 있는 
	public void init(){
		hadoopFileRead(); 
	}
}
