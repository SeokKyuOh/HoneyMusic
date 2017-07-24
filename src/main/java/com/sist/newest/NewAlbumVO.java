package com.sist.newest;

import java.util.Date;

public class NewAlbumVO {
	private int album_id;
	private String album_name;
	private Date album_release;
	private String album_type;
	private String album_art;
	private String album_artist;
	private String music_title;
	private String music_name;
	private int count;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public Date getAlbum_release() {
		return album_release;
	}
	public void setAlbum_release(Date album_release) {
		this.album_release = album_release;
	}
	public String getAlbum_type() {
		return album_type;
	}
	public void setAlbum_type(String album_type) {
		this.album_type = album_type;
	}
	public String getAlbum_art() {
		return album_art;
	}
	public void setAlbum_art(String album_art) {
		this.album_art = album_art;
	}
	public String getAlbum_artist() {
		return album_artist;
	}
	public void setAlbum_artist(String album_artist) {
		this.album_artist = album_artist;
	}
	public String getMusic_title() {
		return music_title;
	}
	public void setMusic_title(String music_title) {
		this.music_title = music_title;
	}
	public String getMusic_name() {
		return music_name;
	}
	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
	
	
}
