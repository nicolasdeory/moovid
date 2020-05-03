package aiss.controller.demo;

import java.util.List;

import aiss.model.spotify.Artist;
import aiss.model.spotify.Song;

public class SpotifyDemo {
	private List<Artist> artists;
	private Song song;
	
	public SpotifyDemo(List<Artist> artists, Song song) {
		super();
		this.artists = artists;
		this.song = song;
	}
	public List<Artist> getArtists() {
		return artists;
	}
	public Song getSong() {
		return song;
	}
	
	
}
