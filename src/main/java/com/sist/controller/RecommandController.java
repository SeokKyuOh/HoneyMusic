package com.sist.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.annotation.Resource;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.hadoop.mapreduce.JobRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.recommand.MusicVO;
import com.sist.recommand.RManager;
import com.sist.recommand.RecommandManager;

@Controller
public class RecommandController {
 @Autowired
   private Configuration conf; // hadoop연결 
  /* @Autowired
   private NaverBlogManager nbm;
   @Autowired
	private MovieDAO dao;*/
   @Autowired
   @Qualifier("mj")
   private JobRunner jr;
   @Resource(name="rj")
   private JobRunner recomm;
   /*@Autowired
   private MovieManager mgr;*/
   @Autowired
   private RManager rm;
   @Autowired
   private RecommandManager rcm;
   
   
   @RequestMapping("main/recommand.do")
   public String main_recommand(String no,Model model){
	   String keyword="";
	   if(no.equals("1")) keyword="신나는 노래 추천";
	   else if(no.equals("2")) keyword="위로가 필요할 때 노래 추천";
	   else if(no.equals("3")) keyword="비오는 날 노래 추천";
	   else if(no.equals("4")) keyword="드라이브할 때 노래 추천";
	   else if(no.equals("5")) keyword="사랑 노래 추천";
	   else if(no.equals("6")) keyword="새벽에 듣기 좋은 노래 추천";
	   else if(no.equals("7")) keyword="눈오는 날 노래 추천";
	   rcm.naverBlogData(keyword);
	   rcm.naverXmlParse();
	   recommFileDelete();
	   recommCopyFromLocal();
	   
	   try {
		   recomm.call();
	   }catch(Exception ex){
		   ex.getMessage();
	   }
	   recommcopyToLocal();
	   
		List<MusicVO> list = rm.recommandData();
		for(MusicVO vo:list){
			System.out.println("*"+vo.getTitle());
		}
		model.addAttribute("keyword",keyword);
		model.addAttribute("list",list);
	   model.addAttribute("main_jsp", "recommand.jsp");
	   return "main/main";
   }
   
	public String jsonCreate(List<MusicVO> list){
		   String data="";
		   
		   try {
			   JSONArray arr=new JSONArray();
			   for(MusicVO vo:list){
				   JSONObject obj=new JSONObject();
				   obj.put("movieTitle", vo.getTitle());
				   obj.put("movieDirector", vo.getArtist());
				   obj.put("movieImage", vo.getPoster());
				   arr.add(obj);
			   }
			   data=arr.toJSONString();
		   }catch(Exception ex){
			   System.out.println(ex.getMessage());
		   }
		   return data;
	   }
	   
	   public void hadoopFileDelete(){
		   try{
			   FileSystem fs=FileSystem.get(conf);
			   if(fs.exists(new Path("/input_honey/recommand.txt"))){
				   fs.delete(new Path("/input_honey/recommand.txt"),true);
			   }
			   if(fs.exists(new Path("/output_honey"))){
				   fs.delete(new Path("/output_honey"),true);
			   }
			   fs.close();
		   }catch(Exception ex){
			   System.out.println(ex.getMessage());
		   }
	   }
	   
	   public void copyFromLocal() {
		   try{
			   FileSystem fs=FileSystem.get(conf);
			   fs.copyFromLocalFile(new Path("/home/sist/honeymusic/recommand.txt"),new Path("/input_honey/recommand.txt"));
			   fs.close();
		   }catch(Exception ex){
			   System.out.println(ex.getMessage());
		   }
	   }
	   
	   public void copyToLocal(){
		   try{
			   FileSystem fs=FileSystem.get(conf);
			   fs.copyToLocalFile(new Path("/output_honey/part-r-00000"),
					   new Path("/home/sist/honeymusic/result"));
			   fs.close();
		   }catch(Exception ex){
			   System.out.println(ex.getMessage());
		   }
	   }
	   
	   public void recommFileDelete(){
		   try{
			   FileSystem fs=FileSystem.get(conf);
			   if(fs.exists(new Path("/input_honey/recommand.txt"))){
				   fs.delete(new Path("/input_honey/recommand.txt"),true);
			   }
			   if(fs.exists(new Path("/output_honey"))){
				   fs.delete(new Path("/output_honey"),true);
			   }
			   fs.close();
		   }catch(Exception ex)
		   {
			   System.out.println(ex.getMessage());
		   }
	   }
	   
	   public void recommCopyFromLocal(){
		   try{
			   FileSystem fs=FileSystem.get(conf);
			   fs.copyFromLocalFile(new Path("/home/sist/honeymusic/recommand.txt"),
					   new Path("/input_honey/recommand.txt"));
			   fs.close();
		   }catch(Exception ex){
			   System.out.println(ex.getMessage());
		   }
	   }
	   public void recommcopyToLocal(){
		   try {
			   FileSystem fs = FileSystem.get(conf);
			   fs.copyToLocalFile(new Path("/output_honey/part-r-00000"),new Path("/home/sist/honeymusic/recommand_result")); //앞이 하둡 //뒤가 로컬
			   fs.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	   }
	   public void recommMusicList(){
		   try {
			FileReader fr=new FileReader("/home/sist/honeymusic/recommand_result");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		   
	   }
}

