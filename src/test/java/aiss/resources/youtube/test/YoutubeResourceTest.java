package aiss.resources.youtube.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import aiss.resources.youtube.YoutubeResource;

public class YoutubeResourceTest {

	private static final String PATH = "./temp/music/";
	
	@Test
	public void testDownloadVideo() {
		String song = "3dm_5qWWDV8";
		String file = YoutubeResource.downloadVideo(song);
		String test = "Muse - Hysteria [Official Music Video].mp4";
		assertEquals(file, test);
		try {
			Files.delete(Paths.get(PATH + file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testGetVideoId() {
		String query = "teminite+ascent";
		String id = YoutubeResource.getVideoId(query);
		assertEquals(id, "qzv-ySVqZXw");
	}
	
}
