package com.sist.recommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.sist.chartfinder.dao.Item;
import com.sist.chartfinder.dao.Rss;

@Component
public class RecommandManager {
	public static void main(String[] arg){
		RecommandManager n = new RecommandManager();
		n.naverBlogData("드라이브할 때 듣는 노래");
		System.out.println("저장완료");
		n.naverXmlParse();
	}
	
	 public void naverBlogData(String title) {
	     String clientId = "WpWx3bPQyiUqXmr28qXD";
	     String clientSecret = "xaydiHjEfs";
	     try {
	         String text = URLEncoder.encode(title, "UTF-8");
	         String apiURL = "https://openapi.naver.com/v1/search/blog.xml?display=100&start=4&query="+ text; 
	         URL url = new URL(apiURL);
	         HttpURLConnection con = (HttpURLConnection)url.openConnection();
	         con.setRequestMethod("GET");
	         con.setRequestProperty("X-Naver-Client-Id", clientId);
	         con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	         int responseCode = con.getResponseCode();
	         BufferedReader br;
	         if(responseCode==200) { 
	             br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
	         } else {  
	             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	         }
	         String inputLine;
	         StringBuffer response = new StringBuffer();
	         while ((inputLine = br.readLine()) != null) {
	             response.append(inputLine);
	         }
	         br.close();
	         FileWriter fw = new FileWriter("/home/sist/honeymusic/recommand.xml"); 
	         fw.write(response.toString());
	         fw.close();
	     } catch (Exception e) {
	         System.out.println(e);
	     }   
	 }
	 
	 public void naverXmlParse(){
		 try {
			File file = new File("/home/sist/honeymusic/recommand.xml");
			JAXBContext jc= JAXBContext.newInstance(Rss.class);
			Unmarshaller un = jc.createUnmarshaller();
			Rss rss=(Rss)un.unmarshal(file);
			List<Item> list = rss.getChannel().getItem();
			String data = "";
			for(Item i:list){
				data += i.getDescription()+"\n";
				
			}
			data=data.substring(0,data.lastIndexOf("\n"));
			data=data.replaceAll("[^가-힣 ]", "");
			FileWriter fw = new FileWriter("/home/sist/honeymusic/recommand.txt");
			fw.write(data);
			fw.close();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	 }
}
