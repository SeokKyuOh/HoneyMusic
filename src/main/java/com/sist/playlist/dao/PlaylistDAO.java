package com.sist.playlist.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaylistDAO {
	@Autowired
	private PlaylistMapper pMapper;
	
	// playlist_music ���� �� ����Ʈ ���� ��������
	public List<PlaylistMusicVO> getPlaylist(int member_id) {
		// ������ member_id�� ������ ȸ���� playlist_music ���̺��� ������ ��� ��������
		// �÷��̾ ����� ��
		// ��� : �ٹ���Ʈ, � �ش��ϴ� ����
		// �߰� : �� �̸�, ��Ƽ��Ʈ
		// �ϴ� : �� ����Ʈ(���� - ��Ƽ��Ʈ)
		System.out.println("getPlaylist");
		System.out.println(pMapper.getPlaylistId(member_id));
		return pMapper.getPlaylist(member_id);
	}
	
	// �ӽ� player����
	public MusicVO getTempList(int music_id){
		System.out.println("getTempList");
		return pMapper.getTempList(music_id);
	}
	
	// playlist�� ó�� ���鶧
	public void makePlaylist(int member_id){
		System.out.println("makePlaylist");
		pMapper.makePlaylist(member_id);
	}
	
	// �ٹ� ����� Ŭ������ �� music_id ���ϱ�
	public ArrayList<Integer> getMusicId(int album_id){
		System.out.println("getMusicId");
		return pMapper.getMusicId(album_id);
	}
	
	// member_id�� ������ playlist_idã��(���� ��� null�� return)
	public String getPlaylistId(int member_id){
		System.out.println("getPlaylistId");
		return pMapper.getPlaylistId(member_id);
	}
	
	// ��Ʈ���� �̿���� ������ �ִ��� Ȯ��(��ȿ : 1, ��ȿ : 0)
	public int isStreamingValid(int member_id){
		System.out.println("isstreamingValid");
		return pMapper.isStreamingValid(member_id);
	}
	
	// ������ ���� ��� Ƚ�� �ø���
	public void increaseCount(int playlist_music_id){
		System.out.println("id : "+playlist_music_id);
		pMapper.increaseCount(playlist_music_id);
		System.out.println("����");
	}
	
	// music_id Ƚ���� �ø���
	public void increaseMusicCount(int music_id){
		System.out.println("music_count �ø���");
		pMapper.increaseMusicCount(music_id);
	}
	
	// �� �߰�
	public void insertMusic(int playlist_id, int music_id){
		System.out.println("insertMusic");
		System.out.println(playlist_id+", "+music_id);
		
		PlaylistMusicVO vo=new PlaylistMusicVO();
		vo.setMusic_id(music_id);
		vo.setPlaylist_id(playlist_id);
		
		pMapper.insertMusic(vo);
		System.out.println("insert");
	}
	
	/*	�������������� ���� ��õ	*/
	// ���� ���� 3�� ����
	public ArrayList<Integer> getRandomNumber(ArrayList<Integer> randomNum, List<PlaylistMusicVO> vo){
		randomNum.add(ThreadLocalRandom.current().nextInt(0, vo.size()));
      
		for(int i=1; i<3; i++){
			int random=ThreadLocalRandom.current().nextInt(0, vo.size());
	    	 
	    	 boolean exists=false;
	    	 
	    	 // ��ġ�� ���� ����
	    	 for(int j=0; j<randomNum.size(); j++){
	    		 if(randomNum.get(j)==random){
	    			 exists=true;
	    			 break;
	    		 }
	    	 }
	    	 if(!exists){
	    		 randomNum.add(random);
	    	 }
	      }
	      System.out.println("���� ���� : "+randomNum);
	      
	      return randomNum;
	}
	
	// ���� ���� �ѹ�° �ٹ�, � ����
	public List<PlaylistMusicVO> getSendResults(ArrayList<Integer> randomNum, List<PlaylistMusicVO> vo){
		List<PlaylistMusicVO> sendList=new ArrayList<PlaylistMusicVO>();
		
		for(int i=0; i<randomNum.size(); i++){
			sendList.add(vo.get(randomNum.get(i)));
	    }
		return sendList;
	}
	
	// ���� ���� ���� �帣�� ������ �ٹ� ��� ����
	public List<PlaylistMusicVO> getAlbumListFromTopGenre(int member_id){
		System.out.println("getAlbumListFromTopGenre");
		return pMapper.getAlbumListFromTopGenre(member_id);
	}
	
	// ���� ���� ���� ��Ƽ��Ʈ�� ���õ� �뷡 ��� ����
	public List<PlaylistMusicVO> getMusicListFromTopArtist(int member_id){
		System.out.println("getMusicListFromTopArtist");
		return pMapper.getMusicListFromTopArtist(member_id);
	}
	
	// ���� ���� ���� �帣 �̸� ��������
	public FavoriteMusicVO getGenreInfo(int member_id){
		System.out.println("getGenreInfo");
		return pMapper.getGenreInfo(member_id);
	}
	
	// ���� ���� ���� ��Ƽ��Ʈ �̸� ��������
	public FavoriteMusicVO getArtistInfo(int member_id){
		System.out.println("getArtistInfo");
		return pMapper.getArtistInfo(member_id);
	}
}