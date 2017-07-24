package com.sist.chartfinder.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MnetManager {
	public List<MusicVO> mnetTop100(){
		List<MusicVO> list=new ArrayList<MusicVO>();
		try {
			Document doc=Jsoup.connect("http://www.mnet.com/chart/top100/").get();
			Elements rank=doc.select("td.MMLItemRank div.MMLIRankNum_Box span.MMLI_RankNum");
			Elements title=doc.select("td.MMLItemTitle div.MMLITitleSong_Box a.MMLI_Song");
			Elements singer=doc.select("td.MMLItemTitle div.MMLITitle_Info a.MMLIInfo_Artist");
			for(int i=0; i<rank.size();i++){}
			for(int i=0; i<rank.size(); i++){		
				String ranking=rank.get(i).text().substring(0,rank.get(i).text().lastIndexOf("위"));
				System.out.println(ranking+" "+title.get(i).text()+" "+singer.get(i).text());
				MusicVO vo=new MusicVO();
				vo.setRank(Integer.parseInt(ranking));
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
