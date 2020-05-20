package aiss.youtube.resource.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.logging.Logger;

import org.junit.Test;

import com.github.kiulian.downloader.YoutubeException;

import aiss.resources.youtube.YoutubeResource;
import aiss.spotify.resource.test.SpotifyResourceTest;
import ws.schild.jave.EncoderException;

public class YoutubeResourceTest {

	private static Logger log = Logger.getLogger(SpotifyResourceTest.class.getName());
	
	@Test
	public void testDownloadVideo() {
		String song = "3dm_5qWWDV8";
		boolean test = true;
		try {
			String file = YoutubeResource.downloadVideo(song);
		} catch (YoutubeException | IOException e) {
			e.printStackTrace();
			test = false;
		}
		assertEquals(test,true);
	}
	
	@Test
	public void testConvertMP3() {
		String song = "Teminite - Ascent.webm";
		boolean test = true;
		try {
			YoutubeResource.convertMP3(song);
		} catch (IllegalArgumentException | EncoderException e) {
			e.printStackTrace();
			test = false;
		}
		assertEquals(test,true);
	}
}
