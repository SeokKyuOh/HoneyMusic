package com.sist.playlist.dao;

import java.util.Date;

public class AlbumVO {
	private int album_id;
	private String album_name;
	private String album_type;
	private String album_art;
	private String album_artist;
	private Date album_release;
	private String album_agency;
	private String album_dist;
	
	//join(검색 결과용)
	private String music_name;
		
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
	public Date getAlbum_release() {
		return album_release;
	}
	public void setAlbum_release(Date album_release) {
		this.album_release = album_release;
	}
	public String getAlbum_agency() {
		return album_agency;
	}
	public void setAlbum_agency(String album_agency) {
		this.album_agency = album_agency;
	}
	public String getAlbum_dist() {
		return album_dist;
	}
	public void setAlbum_dist(String album_dist) {
		this.album_dist = album_dist;
	}
	public String getMusic_name() {
		return music_name;
	}
	public void setMusic_name(String music_name) {
		this.music_name = music_name;
	}
	
	
}
