
package com.sist.recommand;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
@Component
public class MnetManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MnetManager mm = new MnetManager();
		List<MusicVO> vo=mm.mnetRankData();
		
		RecommandDAO dao=new RecommandDAO();
		for(MusicVO mvo:vo){
		dao.musicInsert(mvo);
		}
	}

	public List<MusicVO> mnetRankData() {
		List<MusicVO> mList = new ArrayList<MusicVO>();
		SimpleDateFormat date=new SimpleDateFormat("YYYYMMDD");
		try {
			int rank = 1;
			for (int j = 1; j <= 2; j++) {
				Document doc = Jsoup.connect("http://www.mnet.com/chart/KPOP/rock/"+date+"?pNum="+ Integer.toString(j)).get();
				Elements title = doc.select("a.MMLI_Song");
				Elements pElem = doc.select("div.MMLITitle_Album a img");//앨범이미지?
				Elements artElem = doc.select("a.MMLIInfo_Artist:eq(0)");
				Elements alElem = doc.select("a.MMLIInfo_Album");
				
				for (int i = 0; i < 50; i++) {
					MusicVO vo = new MusicVO();
					
					Element t = title.get(i);
					Element pos = pElem.get(i);
					String poster = pos.attr("src");
					Element art = artElem.get(i);
					Element al = alElem.get(i);
					
					vo.setRank(rank);
					vo.setTitle(t.text().trim());
					vo.setPoster(poster.trim());
					vo.setArtist(art.text().trim());
					vo.setAlbumname(al.text().trim());
					
					vo.setIncrement("-");
					
					int index =vo.getTitle().indexOf("(");
					String song="";
					   if(index != -1) {
						   song = vo.getTitle().substring(0, vo.getTitle().indexOf("("));
						   song=song.trim();   
						   vo.setTitle(song);
					   }
					
					System.out.println(vo.getRank() + "위 " + vo.getTitle() + " - " + vo.getArtist());
					System.out.println("   " + vo.getPoster());
					System.out.println("   " + vo.getAlbumname());
					System.out.println("   "+vo.getIncrement());
					rank++;
					mList.add(vo);
				}
			}

		} catch (Exception ex) {
			System.out.println("mnetRankData " + ex.getMessage());
		}
		return mList;
	}

}