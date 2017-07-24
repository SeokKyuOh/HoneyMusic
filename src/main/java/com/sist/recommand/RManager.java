package com.sist.recommand;

import java.util.ArrayList;
import java.util.List;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RManager {
	@Autowired
	private RecommandDAO dao;
	
	public List<MusicVO> recommandData(){
		List<MusicVO> list = new ArrayList<MusicVO>();
		
		try {
			RConnection rc = new RConnection();
			rc.voidEval("data<-read.table(\"/home/sist/honeymusic/recommand_result\")");
			rc.voidEval("data1<-data[order(data$V2,decreasing=T),c(\"V1\",\"V2\")]");
			REXP p = rc.eval("data1$V1");
			String[] title=p.asStrings();
			for(int i=0;i<title.length;i++){
				MusicVO vo = dao.musicRecommandData(title[i].replace(","," "));
				list.add(vo);
			}
			
			rc.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
}
