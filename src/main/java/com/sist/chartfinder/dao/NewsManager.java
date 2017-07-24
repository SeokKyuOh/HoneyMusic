package com.sist.chartfinder.dao;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

/*
 * 1. 데이터 수집
 * 2. 분석(맵리듀스,스파크)
 * 3. R
 * 4. 몽고디비
 * 
 * */
@Component
public class NewsManager {
	public List<Item> getNewsAllData(String data){
		List<Item> list=new ArrayList<Item>();
		try {
			URL url=new URL("http://newssearch.naver.com/search.naver?where=rss&query="+URLEncoder.encode(data,"UTF-8"));
			JAXBContext jc=JAXBContext.newInstance(Rss.class);
			Unmarshaller un=jc.createUnmarshaller();
			//Unmarshaller : XML => Object
			//Marshaller : Object => XML
			Rss rss=(Rss)un.unmarshal(url);
			list=rss.getChannel().getItem();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return list;
	}
}
