package com.sist.temp;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainClass2 {

	public static void main(String[] args) {
		String driver="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@211.238.142.109:1521:ORCL";
		String user="melon";
		String password="melon";
		Connection conn=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		List<AlbumVO> albumList=new ArrayList<AlbumVO>();
		List<MusicVO> musicList=new ArrayList<MusicVO>();
		AlbumVO albumVO=new AlbumVO();
		int album_count=9;
		int music_count=91;
		//String[] musicArray={"2041518","1985547","1884049","1996499","1980751","1986591","1978620","1982785"};
		String[] musicArray={"1833071","1996210","389459","1994205","497695","1833106","1985562","1983711","1994177","1979886","1982991"};
		try {
			for(int j=0; j<musicArray.length; j++){
				Class.forName(driver);
				conn=DriverManager.getConnection(url,user,password);
				
				String albumURL="http://www.mnet.com/album/"+musicArray[j];
				Document doc=Jsoup.connect(albumURL).get(); 
				String albumNum=albumURL.substring(albumURL.lastIndexOf("/")+1,albumURL.length()); //�ٹ���ȣ
				Elements elem=doc.select("div.a_info_cont dd p");
				Element alName=doc.select("div.album_info li.top_left p.ml0").first();
				String albumName=alName.text();
				for(int i=0; i<elem.size(); i++){
					System.out.println(elem.get(i).select("span.right").text()); //�ٹ�����
				}
				
				System.out.println("�ٹ�����:"+albumName);
				albumVO.setAlbum_id(album_count);
				albumVO.setAlbum_artist(elem.get(0).select("span.right").text());
				albumVO.setAlbum_release(elem.get(1).select("span.right").text());
				String albumType=elem.get(2).select("span.right").text();
				albumType=albumType.substring(0, albumType.indexOf("|"));
				albumVO.setAlbum_type(albumType);
				albumVO.setAlbum_agency(elem.get(4).select("span.right").text());
				albumVO.setAlbum_dist(elem.get(5).select("span.right").text());
				albumVO.setAlbum_name(albumName);
				
				//Element albumart=doc.select("div.con_slider_list ul li img").first();//�ٹ���Ʈ
				Element albumart=doc.select("div.a_info_cont dt.pic_art img").first();//�ٹ���Ʈ
				String art=albumart.attr("src");
				System.out.println("�ٹ���:"+art);
				downloadImg(art);
				albumVO.setAlbum_art(albumNum);
				String sql="insert into album values(?,?,?,?,?,?,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, albumVO.getAlbum_id());
				pstmt.setString(2, albumVO.getAlbum_name());
				pstmt.setString(3, albumVO.getAlbum_release());
				pstmt.setString(4, albumVO.getAlbum_type());
				pstmt.setString(5, albumVO.getAlbum_art());
				pstmt.setString(6, albumVO.getAlbum_agency());
				pstmt.setString(7, albumVO.getAlbum_dist());
				pstmt.setString(8, albumVO.getAlbum_artist());
				pstmt.executeUpdate();
				
				//���⼭���� �ش� �ٹ��� �ش��ϴ� ��� ����
				//Elements musics=doc.select("tbody tr td.MMLItemTitle div.MMLITitleSong_Box a.MMLI_Song");
				Elements artists=doc.select("tbody tr td.MMLItemArtist a");
				Elements song_elem=doc.select("td.MMLItemCheck input"); //�ش� �ٹ��� ���� ��� ����
				Elements isTitle=doc.select("tbody tr td.MMLItemTitle div.MMLITitleSong_Box");
			
				System.out.println("");
				for(int i=0; i<song_elem.size(); i++){
					MusicVO musicVO=new MusicVO();
					
					//String musicName=musics.get(i).text();
					//System.out.println("�뷡�̸�:"+musicName);
					
					String title=isTitle.get(i).text().substring(isTitle.get(i).text().length()-3, isTitle.get(i).text().length());
					String istitle="";
					if(title.equals("Ÿ��Ʋ")) {
						System.out.println("Ÿ��Ʋ!");
						istitle="y";
					}
					else{
						System.out.println("Ÿ��Ʋ �ƴ�!");
						istitle="n";
					}
					
					String artistsName=artists.get(i).text();
					System.out.println("��Ƽ��Ʈ:"+artistsName);
					
					String song_num=song_elem.get(i).attr("value");
					System.out.println("�뷡��ȣ:"+song_num);
					Document doc_s=Jsoup.connect("http://www.mnet.com/track/"+song_num).get();
					Element songname=doc_s.select("div.fix div.music_info_view li.top_left p.ml0").first(); //song name
					String songName=songname.text();
					System.out.println("�뷡�̸�:"+songName);
					
					Elements gelem=doc_s.select("div.music_info_cont dd.con ul.conw li.left_con p.right"); //genre
					String genre=gelem.get(1).text();
					String fgenre=genre.substring(0, genre.indexOf(">")-1);
					int genreInt=0;
					
					if(fgenre.equals("����")) {
						fgenre=genre.substring(genre.indexOf(">")+2,genre.length());
					}
					if(fgenre.equals("��")) genreInt=1;
					else if(fgenre.equals("�߶�� ")) genreInt=2;
					else if(fgenre.equals("��/�ҿ�") || fgenre.equals("��") || fgenre.equals("�ҿ�")) genreInt=3;
					else if(fgenre.equals("Ŭ����")) genreInt=4;
					else if(fgenre.equals("��/����") || fgenre.equals("����") || fgenre.equals("��")) genreInt=5;
					else if(fgenre.equals("��/��Ż") || fgenre.equals("��") || fgenre.equals("��") || fgenre.equals("��Ż")) genreInt=6;
					else if(fgenre.equals("OST")) genreInt=7;
					else  genreInt=8;
					System.out.println("�帣:"+fgenre);
					
					Element ly_elem=doc_s.select("div.line_info ul.con").first();
					String lyrics;
					if(ly_elem==null || ly_elem.text().equals("")){
						System.out.println("����no");
						lyrics="�������";
					}else{
						System.out.println("����:"+ly_elem.text()); 
						lyrics=ly_elem.text();
					}
								
					musicVO.setMusic_id(music_count);
					musicVO.setMusic_title(istitle);
					musicVO.setMusic_name(songName);
					musicVO.setMusic_artist(artistsName);
					musicVO.setMusic_number(song_num);
					musicVO.setMusic_lyrics(lyrics);
					musicVO.setGenre_id(genreInt);
					musicVO.setAlbum_id(albumVO.getAlbum_id());
					musicList.add(musicVO);
					sql="insert into music values(?,?,?,?,?,?,?,?,0)";
					pstmt2=conn.prepareStatement(sql);
					pstmt2.setInt(1, musicVO.getMusic_id());
					pstmt2.setString(2, musicVO.getMusic_name());
					pstmt2.setString(3, musicVO.getMusic_artist());
					pstmt2.setString(4, musicVO.getMusic_number());
					pstmt2.setString(5, musicVO.getMusic_title());
					pstmt2.setString(6, musicVO.getMusic_lyrics());
					pstmt2.setInt(7, musicVO.getGenre_id());
					pstmt2.setInt(8, musicVO.getAlbum_id());
					pstmt2.executeUpdate();
					music_count++;
					System.out.println("");
				}
				album_count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
				if(pstmt2!=null) pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void downloadImg(String urlAddr){
		Image image = null;
        try {
            URL url = new URL(urlAddr);
            BufferedImage img = ImageIO.read(url);
            urlAddr=urlAddr.substring(urlAddr.lastIndexOf("/")+1, urlAddr.length());
            File file=new File("C:\\album_img\\"+urlAddr);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
         e.printStackTrace();
        }
	}
}
