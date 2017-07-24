package com.sist.temp;
import java.util.*;
import java.sql.*;
public class MainClass {

	public static void main(String[] args) {
		Connection conn=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:ORCL";
			conn=DriverManager.getConnection(url,"scott","tiger");
			conn.setAutoCommit(false); //jdbc는 autocommit이 true임. 
			//핵심코딩여기에 =>Around
 			for(int i=1;i<=125; i++){
 				//한 달 중 10~20일의 예약일 존재
 				int count=(int)(Math.random()*11)+10;
 				String sql="UPDATE food SET res_day=? WHERE no=?";
 				PreparedStatement ps=conn.prepareStatement(sql);
 				ps.setString(1, getRand(count));
 				ps.setInt(2, i);
 				ps.executeUpdate();
 				ps.close();
 			}
			conn.commit();
 			System.out.println("저장 완료!");
 			
 			
		}catch(Exception ex){
			try{
				conn.rollback(); //AFTER-Throwing
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		finally{
			try{
				conn.setAutoCommit(true); //AFTER
			}catch(Exception e1){}
		}
		//AFTER-RETURNING   =>15개
	}
	public static String getRand(int count){ //count개수 만큼 랜덤 발생; 중복없는 난수 발생
		String res="";
		int[] com=new int[count];
		int su=0;
		boolean bDash=false;
		for(int i=0; i<count; i++){
			bDash=true;
			while(bDash){
				su=(int)(Math.random()*31)+1; //1~31  (Math.random(): 0.0~0.99
				bDash=false;
				for(int j=0; j<i; j++){
					if(su==com[j]){
						bDash=true;
						break;
					}
				}
			}
			com[i]=su;
		}
		/*
		 * 10 5 6 11 8  ==> 5 6 7 10 11
		 * =  =
		 * 5  10
		 * =    =
		 * 5  10 6
		 * =      =
		 * 5  10  6  11
		 * =           =
		 * 5  10  6  11  8
		 * =========1ROUND
		 * 5 6 10 11 8
		 * =========2
		 * 5 6 8 11 10
		 * =========3
		 * 5 6 8 10 11
		 * =========4
		 * ==>length-1번의 round를 돎.
		 * 
		 * 
		 * 0	0	1	0	0  x=0 y
		 * 0	2	3	4	0
		 * 5	6	7	8	9
		 * 0	10	11	12	0
		 * 0	0	13	0	0
		 * 
		 * int[][] data=new int[5][5];
		 * y-s=>2 y-e=2
		 * y-s=>1 y-e=3
		 * y-s=>0 y-e=4
		 * 
		 * x = 0 2   2
		 * x = 1 1   3
		 * x = 2 0   4  => x+y=2 =>y=2-x, y=x+2
		 * 
		 * */
		for(int i=0; i<com.length-1; i++){ //시간대 이른 순으로 선택정렬
			for(int j=i+1; j<com.length; j++){
				if(com[i]>com[j]){
					int temp=com[i];
					com[i]=com[j];
					com[j]=temp;
				}
			}
		}
		for(int i=0; i<com.length;i++){
			res+=com[i]+",";
		}
		res=res.substring(0,res.lastIndexOf(","));
		//"5,7,8,10,11"
		return res;
	}

}
