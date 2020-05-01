package aiss.spotify.model;

public class Song {

	private String artist;
	private String name;
	public Song(String artist, String name) {
		super();
		this.artist = artist;
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.artist + "%20" + this.name;
	}
	
}
