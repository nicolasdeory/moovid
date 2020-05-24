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
	public void getArtistIdsTest() throws Exception{
		if (!SpotifyResource.isAuthorized())
			SpotifyResource.authorize();
		List<Artist> art = SpotifyResource.getArtistIds("Zedd,Savant");
		assertNotEquals(art.get(0),null);
		assertNotEquals(art.get(1),null);
		
		String id1 = art.get(0).getId();
		String id2 = art.get(1).getId();
		String test1 = "2qxJFvFYMEDqd7ui6kSAcq";
		String test2 = "5RBdF1pJSLF3ugc2Y2PoB8";
		
		log.log(Level.INFO, "Results at SpotifyResourceTest.getArtistIdsTest(): ");
		log.log(Level.INFO, "Expected artist id for Zedd: (" + test1 + ") - Actual result: (" + id1 + ")");
		log.log(Level.INFO, "Expected artist id for Savant: (" + test2 + ") - Actual result: (" + id2 + ")");
		
		assertEquals(id1, test1);
		assertEquals(id2, test2);
	}
	
	@Test
	public void getRecommendationsTest() throws Exception{
		if (!SpotifyResource.isAuthorized())
			SpotifyResource.authorize();
		
		List<Artist> art = SpotifyResource.getArtistIds("Virtual Riot");
		List<String> genres = new ArrayList<String>();
		genres.add("edm");
		genres.add("drum-and-bass");
		String json = SpotifyResource.getRecommendations(art, genres, "none",
				"highenergy", "yes", "none", "sad");
		List<Song> songs = SpotifyResource.getSongsFromJson(json);
		assertNotEquals(songs, new ArrayList<Song>());
		assertNotEquals(songs.get(0), null);
		
		log.log(Level.INFO, "Results at SpotifyResourceTest.getRecommendationsTest(): ");
		for (Song song : songs)
			log.log(Level.INFO, "Recommended song: " + song);
	}
	
	@Test
	public void testGetBPM() throws Exception{
		if (!SpotifyResource.isAuthorized())
			SpotifyResource.authorize();

		String id1 = "2E7W1X4maFFcjHrVrFA7Vs";
		String id2 = "5hheGdf1cb4rK0FNiedCfK";
		String id3 = "3jagGO7eHHuaD53ibehkux";
		
		Double test1 = 146.591;
		Double test2 = 92.027;
		Double test3 = 92.717;
		
		Double bpm1 = SpotifyResource.getBPM(id1);
		Double bpm2 = SpotifyResource.getBPM(id2);
		Double bpm3 = SpotifyResource.getBPM(id3);
		
		log.log(Level.INFO, "Results at SpotifyResourceTest.getBPMTest(): ");
		log.log(Level.INFO, "Expected BPM for Ozzy Osbourne - Bark at the Moon: (" + test1 + ")\nActual result: (" + bpm1 + ")");
		log.log(Level.INFO, "Expected BPM for Slipknot - The Devil in I: (" + test2 + ")\nActual result: (" + bpm2 + ")");
		log.log(Level.INFO, "Expected BPM for Mastodon - Blood and Thunder: (" + test3 + ")\nActual result: (" + bpm3 + ")");
		assertEquals(bpm1, test1);
		assertEquals(bpm2, test2);
		assertEquals(bpm3, test3);
	}
}
