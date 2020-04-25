package aiss.service.video;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import io.humble.video.Codec;
import io.humble.video.Encoder;
import io.humble.video.MediaPacket;
import io.humble.video.MediaPicture;
import io.humble.video.Muxer;
import io.humble.video.MuxerFormat;
import io.humble.video.PixelFormat;
import io.humble.video.Rational;
import io.humble.video.awt.MediaPictureConverter;
import io.humble.video.awt.MediaPictureConverterFactory;

public class VideoEncodeService 
{

	private static Logger log = Logger.getLogger(VideoEncodeService.class.getName());
	
	static final Integer defaultFps = 30;
	static final Rational framerate = Rational.make(1, defaultFps);
	
	public static void Encode(String filePath, int videoWidth, int videoHeight, FrameGenerator frames) throws IOException, InterruptedException
	{
		log.info("Encoding new video");
		long startTime = System.nanoTime(); // stopwatch
		// Create H264 Muxer
		final Muxer muxer = Muxer.make(filePath, null, "mp4"); // h264?
		
		final MuxerFormat format = muxer.getFormat();
		final Codec codec;
		codec = Codec.findEncodingCodec(format.getDefaultVideoCodecId());
		
		// Create Encoder
		Encoder encoder = Encoder.make(codec);
		
		// Set Width, Height and Pixel Format
		encoder.setWidth(videoWidth);
		encoder.setHeight(videoHeight); // Hardcoded?
		final PixelFormat.Type pixelFormat = PixelFormat.Type.PIX_FMT_YUV420P;
		encoder.setPixelFormat(pixelFormat);
		encoder.setTimeBase(framerate);
		
		// Check if format needs global header
		if (format.getFlag(MuxerFormat.Flag.GLOBAL_HEADER))
			encoder.setFlag(Encoder.Flag.FLAG_GLOBAL_HEADER, true);
		
		// Open encoder and muxer
		encoder.open(null, null);
		muxer.addNewStream(encoder);
		muxer.open(null, null);
		
		// Create converter from RGB to YCrCb format
		MediaPictureConverter converter = null;
		final MediaPicture picture = MediaPicture.make(encoder.getWidth(), encoder.getHeight(), pixelFormat);
		picture.setTimeBase(framerate);
		
		// Example Video Generation
		
		final MediaPacket packet = MediaPacket.make();
		
		// Write Example Video
		int frameCounter = 0;
		while(frames.hasNext())
		{
			// Get next frame
			Frame frame = frames.next();
			BufferedImage frameImg = frame.getData();
			// Convert from RGB to YUV420P
			if (converter == null)
				converter = MediaPictureConverterFactory.createConverter(frameImg, picture);
			
			for (int i = 0; i < frame.getDuration(); i++)
			{
				converter.toPicture(picture, frameImg, frameCounter);
				// Encode frame
				do 
				{
					encoder.encode(packet, picture);
					if (packet.isComplete())
						muxer.write(packet, false);
				} while (packet.isComplete());
				frameCounter++;
				if (frameCounter % 30 == 0)
				{
					log.info("Encoding: " + frameCounter + " / " + frames.getTotalDuration());
				}
			}
			
		}
		
		// Flush encoder
		do
		{
			encoder.encode(packet, null);
			if (packet.isComplete())
				muxer.write(packet, false);
		} while (packet.isComplete());
		
		// Clean-up
		muxer.close();
		
		long endTime = System.nanoTime();
		log.info("Encoded " + filePath + " in " + (endTime - startTime)/1000000000. + " seconds");
	}
	
	private static void fillBufferedImageData(BufferedImage img, int[] data, int w, int h) 
	{
		if (w * h != data.length) throw new IllegalArgumentException("w*h must be equal to the length of the array");
		for (int y = 0; y < 1080; y++)
		{
			for (int x = 0; x < 1920; x++)
			{
				int idx = y*1920 + x;
				img.setRGB(x, y, data[idx]);
			}
		}
	}
	
}
