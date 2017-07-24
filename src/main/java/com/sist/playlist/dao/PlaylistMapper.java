package com.sist.playlist.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface PlaylistMapper {
	// Playlist�� Playlist_music ���̺� inner join
	// �÷��̸���Ʈ�� ���� ȸ�� id, music_id�� �������� ���� �۾� -> �÷��̸���Ʈ ���
	// �ٹ���Ʈ ��������
	// Ŭ���� ���� music_id�� �̿��Ͽ� music�� album ���̺� inner join
	// playlist, playlist_music, music, album�� �Ѳ����� join!
	/*
		select playlist_music_id, p.playlist_id, m.music_id, 
		p.member_id,
		m.music_name, m.music_artist, m.music_lyrics,
		a.album_name, a.album_art
		from playlist_music full outer join PLAYLIST p on playlist_music.playlist_id=p.playlist_id 
		inner join MUSIC m on playlist_music.music_id=m.music_id inner join ALBUM a on m.album_id=a.album_id;
	*/
	// ȸ���� �÷��̸���Ʈ ��� ��������
/*
	@Results( //Results�� Select �ٷ� ���� ������ ������ �ǹ�
			{
			@Result(property="playlist_music_id", column="playlist_music_id"),
			@Result(property="playlist_id", column="playlist_id"),
			@Result(property="music_number", column="music_number"),
			@Result(property="member_id", column="member_id"),
			@Result(property="music_name", column="music_name"),
			@Result(property="music_artist", column="music_artist"),
			@Result(property="music_lyrics", column="music_lyrics"),
			@Result(property="album_name", column="album_name")
			}
	)

*/
	// ȸ���� �÷��̸���Ʈ ��� ��������(�̿���� �ִ� ���)
	@Select("select playlist_music_id, p.playlist_id, m.music_number,"
			+"p.member_id,"
			+"m.music_name, m.music_artist, m.music_lyrics, m.music_id, "
			+"a.album_name, a.album_art "
			+"from playlist_music full outer join PLAYLIST p on playlist_music.playlist_id=p.playlist_id "
			+"inner join MUSIC m on playlist_music.music_id=m.music_id "
			+"inner join ALBUM a on m.album_id=a.album_id "
			+"where p.member_id=#{member_id} order by playlist_music.playlist_music_id")
	public List<PlaylistMusicVO> getPlaylist(int member_id);
	
	// �ӽ� �÷��̾� ����(��ȸ�� or ȸ�������� �̿���� ���� ���)
	@Select("select music_number, album_art, music_lyrics, music_name, music_artist from music inner join ALBUM a on a.ALBUM_ID = music.ALBUM_ID and music_id=#{music_id}")
	public MusicVO getTempList(int music_id);
	
	// playlist �÷� �����
	@SelectKey(keyProperty="playlist_id", resultType=int.class, before=true, statement="select nvl(max(playlist_id)+1, 1) as playlist_id from playlist")
	@Insert("insert into playlist(playlist_id, member_id) values(#{playlist_id}, #{member_id})")
	public void makePlaylist(int member_id);
	
	// �ٹ� ����� Ŭ������ �� album_id�� ������ music_id ���ϱ�
	@Select("select music_id from music where album_id=#{album_id} order by music_id")
	public ArrayList<Integer> getMusicId(int album_id);
	
	// member_id�� ������ playlist_idã��(���� ��� null�� return)
	@Select("select playlist_id from playlist where member_id=#{member_id}")
	public String getPlaylistId(int member_id);
	
	// ��ȸ�� or �̿���� ���� ��� playlist_music�� ���� ���� �������� �ʰ� music_id�� ������!
	// 1�� �̸���� ���� or ���� ��� Ƚ�� ���� �ɱ�(session�� �� �־����)
	// ���� ��¥(��� ��ư�� ���� �� �ð�)�� �̿�� ���������� ���Ͽ� ��ȿ��� 1, ��ȿ�ϸ� 0 return
	@Select("select count(*) from buy_streaming "
			+ "where buy_streaming_end >= sysdate and member_id=#{member_id}")
	public int isStreamingValid(int member_id);
	
	// �� Ŭ���ø��� ���Ƚ�� �ø���
	@Update("update playlist_music set playlist_count=playlist_count+1 where playlist_music_id=#{playlist_music_id}")
	public void increaseCount(int playlist_music_id);
	
	// music_count�� ���� �ø���!
	@Update("update music set music_count=music_count+1 where music_id=#{music_id}")
	public void increaseMusicCount(int music_id);
	
	// playlist_music_seq.nextval
	// �� ���� �� playlist_music�� ����
	@SelectKey(keyProperty="playlist_music_id", resultType=int.class, before=true, statement="select nvl(max(playlist_music_id)+1, 1) as playlist_music_id from playlist_music")
	@Insert("insert into PLAYLIST_MUSIC (PLAYLIST_MUSIC_ID, PLAYLIST_COUNT, PLAYLIST_ID, MUSIC_ID) VALUES (#{playlist_music_id}, 0, #{playlist_id}, #{music_id})")
	public void insertMusic(PlaylistMusicVO vo);
	
	/*	�������������� ���� ��õ	*/
	// ���� ���� ���� �帣�� ������ �ٹ� ��� ����
	@Select("select album.album_id, album_art, album_name, album_artist from album, music where genre_id="
			+"(select genre_id from (select count(result.genre_name), result.genre_name, result.genre_id from (select music.music_name, music.music_artist, g.genre_id, g.genre_name, p.playlist_count from MUSIC "
			+"inner join PLAYLIST_MUSIC p on p.MUSIC_ID=music.music_id "
			+"inner join ALBUM a on a.ALBUM_ID=music.album_id "
			+"inner join GENRE g on g.GENRE_ID = MUSIC.GENRE_ID inner join PLAYLIST p1 on p1.PLAYLIST_ID = p.PLAYLIST_ID where p1.MEMBER_ID=#{member_id} "
			+"order by p.playlist_count desc) result where rownum=1 group by genre_name, genre_id)) "
			+"group by album.album_id, album_art, album_name, album_name, album_artist")
	public List<PlaylistMusicVO> getAlbumListFromTopGenre(int member_id);
	
	// ���� ���� ���� ��Ƽ��Ʈ�� ���õ� �뷡 ��� ����
	@Select("select album_art, music_name, music_artist from music inner join album on album_artist=music_artist "
			+"where music_artist=(select music_artist from (select count(result.genre_name), result.music_artist from (select music.music_name, music.music_artist, g.genre_name, p.playlist_count from MUSIC "
			+"inner join PLAYLIST_MUSIC p on p.MUSIC_ID=music.music_id "
			+"inner join ALBUM a on a.ALBUM_ID=music.album_id "
			+"inner join GENRE g on g.GENRE_ID = MUSIC.GENRE_ID inner join PLAYLIST p1 on p1.PLAYLIST_ID = p.PLAYLIST_ID "
			+"where p1.MEMBER_ID=#{member_id} order by p.playlist_count desc) result where rownum=1 group by music_artist)) group by album_art, music_name, music_artist")
	public List<PlaylistMusicVO> getMusicListFromTopArtist(int member_id);
	
	// ���� ���� ���� �帣 �̸� ��������
	@Select("select result.genre_name, result.genre_id from (select music.music_name, music.music_artist, g.genre_id, g.genre_name, p.playlist_count from MUSIC " 
			+"inner join PLAYLIST_MUSIC p on p.MUSIC_ID=music.music_id "
			+"inner join ALBUM a on a.ALBUM_ID=music.album_id "
			+"inner join GENRE g on g.GENRE_ID = MUSIC.GENRE_ID inner join PLAYLIST p1 on p1.PLAYLIST_ID = p.PLAYLIST_ID where p1.MEMBER_ID=#{member_id} order by p.playlist_count desc) result where rownum=1 group by genre_name, genre_id")
	public FavoriteMusicVO getGenreInfo(int member_id);
	
	// ���� ���� ���� ��Ƽ��Ʈ �̸� ��������
	@Select("select result.music_artist from (select music.music_name, music.music_artist, g.genre_id, g.genre_name, p.playlist_count from MUSIC "
			+"inner join PLAYLIST_MUSIC p on p.MUSIC_ID=music.music_id "
			+"inner join ALBUM a on a.ALBUM_ID=music.album_id "
			+"inner join GENRE g on g.GENRE_ID = MUSIC.GENRE_ID inner join PLAYLIST p1 on p1.PLAYLIST_ID = p.PLAYLIST_ID where p1.MEMBER_ID=#{member_id} order by p.playlist_count desc) result where rownum=1 group by music_artist")
	public FavoriteMusicVO getArtistInfo(int member_id);
}
