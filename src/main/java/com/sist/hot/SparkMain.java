package com.sist.hot;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;

import scala.Tuple2;
import twitter4j.Status;

public class SparkMain {
	private Configuration hconf;
	private JobConf jobConf;
	
	public static void main(String[] args) {
		SparkMain sm=new SparkMain();
		
		SparkConf sconf;
		JavaStreamingContext jsc;
		sconf=new SparkConf().setAppName("twitter-hot").setMaster("local[2]");
		jsc=new JavaStreamingContext(sconf,new Duration(10000));

		sm.hconf=new Configuration();
		sm.hconf.set("fs.default.name", "hdfs://namenode:9000");
		sm.jobConf=new JobConf();
		
		/*String[] filter={
				"5PJj6Be1fCoYY6k65LrwXWpRJ",
				"cDsPR6tdAhMAZAGl07Smk7rTZJ0YJ4xgFGf1BOM8QC7k2BpvN0",
				"867997047298719744-h0doNgkuIIBsqRTXEH4E2EWgyC4VMk9",
				"dXavSJ74BflaAfPzCJ0iduyZOkpZkk33WAAWhEa4q9n8J"
					
			};*/
		
		String[] filter = {"u9x6jI6FEbrKqBNJ3fyuWCXV8", "tnXRzn7YDavUguRq0wYsh0RYhYF9uoIG7IpoMOzR4Dod7rGNLf", 
				"867996878465400832-eCNrtENyeqV55J8TCumkdECoGjCinuK", "ecQjnJctxuyRjyaCex3pEsAF5QPUXveJn3DbPz8yRtsfc"};
		
			System.setProperty("twitter4j.oauth.consumerKey", filter[0]);
			System.setProperty("twitter4j.oauth.consumerSecret", filter[1]);
			System.setProperty("twitter4j.oauth.accessToken", filter[2]);
			System.setProperty("twitter4j.oauth.accessTokenSecret", filter[3]);
			
			final String[] data={
					"지코",
					"아이유",
					"레드벨벳",
					"트와이스",
					"엑소"
			};
			JavaReceiverInputDStream<Status> twitterStream=TwitterUtils.createStream(jsc,data);
			JavaDStream<String> status=twitterStream.map(new Function<Status, String>() {
				@Override
				public String call(Status s) throws Exception {
					return s.getText();
				}
			});
			final Pattern[] p = new Pattern[data.length];
			for(int i=0;i<p.length;i++){
				p[i]=Pattern.compile(data[i]);
				
			}
			final Matcher[] m = new Matcher[data.length];
			JavaDStream<String> words=status.flatMap(new FlatMapFunction<String, String>() {
					List<String> list= new ArrayList<String>();
				@Override
				public Iterable<String> call(String s) throws Exception {
					for(int i=0;i<m.length;i++){
						m[i]=p[i].matcher(s);
						while(m[i].find()){
							System.out.println(m[i].group());
							list.add(m[i].group().replace(" ","$"));
							
						}
						
					}
					return list;
				} 
			});
			
			JavaPairDStream<String,Integer> counts = words.mapToPair(new PairFunction<String, String, Integer>() {

				@Override
				public Tuple2<String, Integer> call(String s) throws Exception {
		
					return new Tuple2<String, Integer>(s, 1);
				}
			});
			JavaPairDStream<String,Integer> reduce=counts.reduceByKey(new Function2<Integer, Integer, Integer>() {
				
				@Override
				public Integer call(Integer sum, Integer i) throws Exception {
					return sum+i; 
				}
			});
			
			sm.hadoopGetFile(reduce);
			jsc.start();
			jsc.awaitTermination();
		}
		

	public void hadoopGetFile(JavaPairDStream<String, Integer> jps){
		try {
			jps.saveAsHadoopFiles("hdfs://namenode:9000/usr/hadoop/melon/hot","",Text.class,IntWritable.class ,TextOutputFormat.class,jobConf);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
