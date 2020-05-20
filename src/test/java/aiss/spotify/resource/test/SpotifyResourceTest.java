package aiss.spotify.resource.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
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
		assertEquals(art.get(0).getId(),"2qxJFvFYMEDqd7ui6kSAcq");
		assertEquals(art.get(1).getId(),"5RBdF1pJSLF3ugc2Y2PoB8");
	}
	/*
	@Test
	public void testGetRecommendations() throws Exception{
		if (!SpotifyResource.isAuthorized())
			SpotifyResource.authorize();
		List<Artist> art = SpotifyResource.getArtistIds("Virtual Riot");
		String danceability = "false";
		String energy = "highenergy";
		String tempo = "none";
		String valence = "sad";
		String json = SpotifyResource.getRecommendations(art, danceability,
				energy, tempo, valence);
		Song song = SpotifyResource.getSongFromJson(json);
		assertEquals(song.toString(), "Virtual Riot - Continue");
	}
	*/
}
