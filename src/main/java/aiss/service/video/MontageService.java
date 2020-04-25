package aiss.service.video;

import java.io.IOException;
import java.util.logging.Logger;

public class MontageService {

	private static Logger log = Logger.getLogger(MontageService.class.getName());
	
	static final Integer videoWidth = 1920;
	static final Integer videoHeight = 1080;
	
	// Photos / Videos should be stored in temp/photo/userID/..
	// Output videos should be stored in temp/video/userID/..
	public static void generateBasicMontage(String filePath, String[] imagePaths) throws IOException, InterruptedException
	{
		VideoEncodeService.Encode(filePath, videoWidth, videoHeight, BasicMontageGenerator.of(imagePaths, videoWidth, videoHeight));
	}
	
}
