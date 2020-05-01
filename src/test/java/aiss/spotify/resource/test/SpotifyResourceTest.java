package aiss.spotify.resource.test;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import aiss.spotify.model.Artist;
import aiss.spotify.resource.SpotifyResource;

public class SpotifyResourceTest {
	
	private static Logger log = Logger.getLogger(SpotifyResourceTest.class.getName());
	
	@Test
	public void testGetName() throws Exception{
		if (!SpotifyResource.isAuthorized())
			SpotifyResource.authorize();
		List<Artist> art = SpotifyResource.testGetArtist();
		log.info("List of artists: " + art);
	}
}
