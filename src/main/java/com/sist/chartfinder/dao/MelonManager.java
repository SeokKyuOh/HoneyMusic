package com.sist.chartfinder.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MelonManager {
	public List<MusicVO> melonTop100(){
		List<MusicVO> list=new ArrayList<MusicVO>();
		try {
			Document doc=Jsoup.connect("http://www.melon.com/chart/index.htm").get();
			Elements rank=doc.select("tr.lst50 span.rank");
			Elements title=doc.select("tr.lst50 strong a");
			Elements singer=doc.select("div div.ellipsis span.checkEllipsis a.play_artist span");
			for(int i=0; i<rank.size();i++){}
			for(int i=0; i<rank.size(); i++){		
				System.out.println(rank.get(i).text()+" "+title.get(i).text()+" "+singer.get(i).text());
				MusicVO vo=new MusicVO();
				vo.setRank(Integer.parseInt(rank.get(i).text().trim()));
				vo.setTitle(title.get(i).text());
				vo.setSinger(singer.get(i).text());
				list.add(vo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
