package com.sist.chartfinder.dao;

import java.io.File;
import java.io.StringReader;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.springframework.stereotype.Repository;

import au.com.bytecode.opencsv.CSVReader;
import scala.Tuple2;

public class MusicManager {
	public static void main(String[] args) {
	
		ChartFinderDAO dao=new ChartFinderDAO("genie");
		GenieManager g=new GenieManager();
		List<MusicVO> gList=g.genieTop100();
		dao.dropTable();
		for(MusicVO vo:gList){
			dao.musicInsert(vo);
		}
		dao.getMusicData();
		
		
		dao=new ChartFinderDAO("melon");
		MelonManager m=new MelonManager();
		List<MusicVO> mList=m.melonTop100();
		dao.dropTable();
		for(MusicVO vo:mList){
			dao.musicInsert(vo);
		}
		dao.getMusicData();
		
		
		dao=new ChartFinderDAO("mnet");
		MnetManager mn=new MnetManager();
		List<MusicVO> mnList=mn.mnetTop100();
		dao.dropTable();
		for(MusicVO vo:mnList){
			dao.musicInsert(vo);
		}
		dao.getMusicData();
	
		
		
		
		File dir=new File("./honey_data/genie-melon-mnet");
		if(dir.exists()){
			File[] files=dir.listFiles();
			for(File f:files){
				f.delete();
			}
			dir.delete(); //rm -rf
		}
		SparkConf conf=new SparkConf().setAppName("Music").setMaster("local");
		JavaSparkContext sc=new JavaSparkContext(conf);
		JavaRDD<String> genie=sc.textFile("./honey_data/genie.csv");
		JavaPairRDD<String, String> geniePair=genie.mapToPair(new PairFunction<String, String, String>() {

			@Override
			public Tuple2<String, String> call(String s) throws Exception {
				CSVReader csv=new CSVReader(new StringReader(s)); //한 줄씩 읽어옴
				try{
					String[] d=csv.readNext();
					return new Tuple2<String, String>(d[1],d[0]); //rank-title 
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
				return new Tuple2<String, String>("-1","1");
			}
		});
		JavaRDD<String> melon=sc.textFile("./honey_data/melon.csv");
		JavaPairRDD<String, String> melonPair=melon.mapToPair(new PairFunction<String, String, String>() {

			@Override
			public Tuple2<String, String> call(String s) throws Exception {
				CSVReader csv=new CSVReader(new StringReader(s)); //한 줄씩 읽어옴
				try{
					String[] d=csv.readNext();
					return new Tuple2<String, String>(d[1],d[0]); //rank-title 
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
				return new Tuple2<String, String>("-1","1");
			}
		});
		JavaRDD<String> mnet=sc.textFile("./honey_data/mnet.csv");
		JavaPairRDD<String, String> mnetPair=mnet.mapToPair(new PairFunction<String, String, String>() {

			@Override
			public Tuple2<String, String> call(String s) throws Exception {
				CSVReader csv=new CSVReader(new StringReader(s)); //한 줄씩 읽어옴
				try{
					String[] d=csv.readNext();
					return new Tuple2<String, String>(d[1],d[0]); //rank-title 
				}catch(Exception ex){
					System.out.println(ex.getMessage());
				}
				return new Tuple2<String, String>("-1","1");
			}
		});
		
		JavaPairRDD<String, Tuple2<String, String>> joinData=geniePair.join(melonPair);
		JavaPairRDD<String,Tuple2<Tuple2<String,String>,String>> join3Data=joinData.join(mnetPair);
		join3Data.saveAsTextFile("./honey_data/genie-melon-mnet");
		
		RankDAO.myCreateCSV3();
		dao=new ChartFinderDAO("myrank");
		dao.myRankInsert();
		
	}
}
