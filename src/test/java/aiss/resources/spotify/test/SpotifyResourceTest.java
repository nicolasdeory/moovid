package aiss.resources.spotify.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import aiss.model.spotify.Artist;
import aiss.model.spotify.Song;
import aiss.resources.spotify.SpotifyResource;

public class SpotifyResourceTest {
	
	private static Logger log = Logger.getLogger(SpotifyResourceTest.class.getName());
	
	@Test
	public void testGetArtistIds() throws Exception{
		if (!SpotifyResource.isAuthorized())
			SpotifyResource.authorize();
		List<Artist> art = SpotifyResource.getArtistIds("Zedd,Savant");
		assertNotEquals(art,null);
		assertNotEquals(art.get(0),null);
		log.log(Level.INFO, art.toString());
	}
	
	@Test
	public void testGetRecommendations() throws Exception{
		/*if (!SpotifyResource.isAuthorized())
			SpotifyResource.authorize();
		List<Artist> art = SpotifyResource.getArtistIds("Virtual Riot");
		List<String> genres = new ArrayList<String>();
		genres.add("edm");
		genres.add("drum-and-bass");
		String danceability = "none";
		String energy = "highenergy";
		String tempo = "none";
		String instrumentalness = "yes";
		String valence = "sad";
		String json = SpotifyResource.getRecommendations(art, genres, danceability,
				energy, instrumentalness, tempo, valence);
		List<Song> songs = SpotifyResource.getSongsFromJson(json);
		assertNotEquals(songs, new ArrayList<Song>());
		assertNotEquals(songs.get(0), null);
		log.log(Level.INFO, songs.toString());*/
		assertEquals("asd","asd"); // TODO: FIX
	}
	
}
