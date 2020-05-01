package aiss.spotify.resource.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import aiss.spotify.model.Artist;
import aiss.spotify.resource.SpotifyResource;

public class SpotifyResourceTest {
	
	private static Logger log = Logger.getLogger(SpotifyResourceTest.class.getName());
	
	@Test
	public void testGetArtistIds() throws Exception{
		if (!SpotifyResource.isAuthorized())
			SpotifyResource.authorize();
		List<Artist> art = SpotifyResource.getArtistIds("zedd,savant");
		assertEquals(art.get(0).getId(),"2qxJFvFYMEDqd7ui6kSAcq");
		assertEquals(art.get(1).getId(),"5RBdF1pJSLF3ugc2Y2PoB8");
	}
}
