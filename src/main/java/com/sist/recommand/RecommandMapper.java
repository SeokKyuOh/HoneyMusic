package com.sist.recommand;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.recommand.RecommandDAO;

public class RecommandMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
     
	  private final IntWritable one=
    		      new IntWritable(1);
     private Text result=new Text();
     private RecommandDAO dao=new RecommandDAO();
	  @Override
	  protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		   
		  List<String> list=dao.musicTitleAllData();
		  
		   Pattern[] p=new Pattern[list.size()];
		   for(int i=0;i<p.length;i++)
		   {
			   String str=list.get(i);
			   p[i]=Pattern.compile(str);
		   }
		   Matcher[] m=new Matcher[list.size()];
		   for(int i=0;i<m.length;i++)
		   {
			   m[i]=p[i].matcher(value.toString());
			   while(m[i].find())
			   {
				   result.set(m[i].group());
				   context.write(result, one);
			   }
		   }
	  }
     
}

