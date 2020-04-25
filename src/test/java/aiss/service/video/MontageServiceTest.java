package aiss.service.video;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MontageServiceTest {

	
	@Test
	public void testVideoEncode() throws Exception
	{
		String[] images = new String[]
						{
								"temp/photo/test/img0.png",
								"temp/photo/test/img1.png",
								"temp/photo/test/img2.png",
								"temp/photo/test/img3.png",
								"temp/photo/test/img4.png",
								"temp/photo/test/img5.png",
								"temp/photo/test/img6.jpeg",
								"temp/photo/test/img7.jpg",
								"temp/photo/test/img8.jpg",
								"temp/photo/test/img9.jpg"
						};
		MontageService.generateBasicMontage("temp/video/unitTest.mp4", images);
		assertTrue(true);
	}
	
}
