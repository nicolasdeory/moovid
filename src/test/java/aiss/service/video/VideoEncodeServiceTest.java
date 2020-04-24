package aiss.service.video;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VideoEncodeServiceTest {

	
	@Test
	public void testVideoEncode() throws Exception
	{
		VideoEncodeService.Encode("testvideo.mp4");
		assertTrue(true);
	}
	
}
