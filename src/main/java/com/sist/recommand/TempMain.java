package com.sist.recommand;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TempMain {

	public static void main(String[] args) {
		TempMain m=new TempMain();
		m.recommMusicList();
		 
	}
	public void recommMusicList(){
		   try {
			FileReader fr=new FileReader("/home/sist/honeymusic/recommand_result");
			String name="";
			List<String> music=new ArrayList<String>();
			String count;
			while(true){
				int data=fr.read();
				if(data==-1) break;
				//System.out.println(name);
				//System.out.println();
				char ch=(char)data;
				if(ch==' '){
					music.add(name);
				}else{
					name+=ch;
				}
			}
			for(String s:music){
				System.out.println(s);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	   }

}
