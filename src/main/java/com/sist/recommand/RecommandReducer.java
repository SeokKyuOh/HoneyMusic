package com.sist.recommand;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RecommandReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
   private IntWritable res=new IntWritable();

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		int sum=0;
		String data=key.toString();
		data=data.replace(" ",",");//영화이름에 공백있으면 , 로
		
		for(IntWritable i:values)
		{
			sum+=i.get();// IntWritable => int
		}
		res.set(sum); // int => IntWritable
		key.set(data);
		context.write(key, res);
	}
   
}
