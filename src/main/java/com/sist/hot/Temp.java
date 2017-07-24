package com.sist.hot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Temp {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		Date date = new Date();

		System.out.println(date.toString());
		
		String s = sdf.format(date);
		
		System.out.println(s.substring(11, 16));
		
		String s1 = "09:10:55";
		String s2 = "09:17:55";
		
		System.out.println(s1.compareTo(s2)); //s2가 더 크면 부정값
		
	}

}
