package aiss.resources.youtube.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import aiss.resources.youtube.YoutubeResource;

public class YoutubeResourceTest {

	private static Logger log = Logger.getLogger(YoutubeResourceTest.class.getName());
	
	@Test
	public void testGetVideoId() {
		/*String query1 = "teminite+ascent";
		String query2 = "virtual+riot+bossfight+afterparty";
		String query3 = "syn+28+days+later";
		
		String id1 = YoutubeResource.getVideoId(query1);
		String id2 = YoutubeResource.getVideoId(query2);
		String id3 = YoutubeResource.getVideoId(query3);
		String test1 = "qzv-ySVqZXw";
		String test2 = "Ayj_3EhZfRs";
		String test3 = "jDigbTQ7xAM";
		
		log.log(Level.INFO, "Results at YoutubeResourceTest.testGetVideoId(): ");
		log.log(Level.INFO, "Expected result for Teminite - Ascent id: (" + test1 + ") - Actual result: (" + id1 + ")");
		log.log(Level.INFO, "Expected result for Virtual Riot - Bossfight Afterparty id: (" + test2 + ") - Actual result: (" + id2 + ")");
		log.log(Level.INFO, "Expected result for SYN - 28 days later id: (" + test3 + ") - Actual result: (" + id3 + ")");
		
		assertEquals(id1, test1);
		assertEquals(id2, test2);
		assertEquals(id3, test3);*/
		// TODO: Reenable test when video ids are fixed
	}
	
	@Test
	public void testDownloadVideo() {
		String id1 = "3dm_5qWWDV8";
		String id2 = "Auuqlcom6tM";
		String id3 = "btSlfXILTkU";
		
		String url1 = YoutubeResource.getAudioStreamUrl(id1);
		String url2 = YoutubeResource.getAudioStreamUrl(id2);
		String url3 = YoutubeResource.getAudioStreamUrl(id3);
		
		log.log(Level.INFO, "Results at YoutubeResourceTest.testDownloadVideo(): ");
		log.log(Level.INFO, "Download URL for Muse - Hysteria: (" + url1 + ")");
		log.log(Level.INFO, "Download URL for Disturbed - Immortalized: (" + url2 + ")");
		log.log(Level.INFO, "Download URL for Pentakill - Tear of the Goddess" + url3 + ")");
		
		//Dynamic URLs: Can't compare to sample ones
		assertNotNull(url1);
		assertNotNull(url2);
		assertNotNull(url3);
	}
}
