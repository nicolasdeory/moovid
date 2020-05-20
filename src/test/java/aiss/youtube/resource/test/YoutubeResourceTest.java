package aiss.youtube.resource.test;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Test;

import aiss.resources.youtube.YoutubeResource;
import aiss.spotify.resource.test.SpotifyResourceTest;

public class YoutubeResourceTest {

	@Test
	public void testDownloadVideo() {
		String song = "3dm_5qWWDV8";
		boolean test = true;
		String file = YoutubeResource.downloadVideo(song);
		System.out.println("Cancion: " + file);
		assertEquals(test,true);
	}
	
}
