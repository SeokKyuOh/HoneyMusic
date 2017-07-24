package com.sist.playlist.dao;

public class PlaylistMusicVO {
	private int playlist_music_id;
	private int playlist_id;
	private int playlist_count;
	private int music_number;
	private int music_id;
	
	// join
	private int member_id;
	private String album_id;
	private String album_name;
	private String album_art;
	private String album_artist;
	private String music_name;
	private String music_artist;
	private String music_lyrics;
	
	
	public int getMusic_id() {
		return music_id;
	}
	public void setMusic_id(int music_id) {
		this.music_id = music_id;
	}
	public int getPlaylist_music_id() {
		return playlist_music_id;
	}
	public void setPlaylist_music_id(int playlist_music_id) {
		this.playlist_music_id = playlist_music_id;
	}
	public int getPlaylist_id() {
		return playlist_id;
	}
	public void setPlaylist_id(int playlist_id) {
		this.playlist_id = playlist_id;
	}
	public int getPlaylist_count() {
		return playlist_count;
	}
	public void setPlaylist_count(int playlist_count) {
		this.playlist_count = playlist_count;
	}
	public int getMusic_number() {
		return music_number;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public String getAlbum_art() {
		return album_art;
	}
	public void setAlbum_art(String album_art) {
		this.album_art = album_art;
	}
	public String getMusic_name() {
		return music_name;
	}
	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
	public String getMusic_artist() {
		return music_artist;
	}
	public void setMusic_artist(String music_artist) {
		this.music_artist = music_artist;
	}
	public String getMusic_lyrics() {
		return music_lyrics;
	}
	public void setMusic_lyrics(String music_lyrics) {
		this.music_lyrics = music_lyrics;
	}
	public String getAlbum_artist() {
		return album_artist;
	}
	public void setAlbum_artist(String album_artist) {
		this.album_artist = album_artist;
	}
	public String getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(String album_id) {
		this.album_id = album_id;
	}
	
	
}
