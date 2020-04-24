package aiss.service.video;

import java.awt.image.BufferedImage;

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

	static final Integer defaultFps = 30;
	static final Rational framerate = Rational.make(1, defaultFps);
	
	static final Integer videoWidth = 1920;
	static final Integer videoHeight = 1080;
	
	
	public static void Encode(String path/*, BufferedImage[] frames*/)
	{
		
		// Create H264 Muxer
		final Muxer muxer = Muxer.make(path, null, "h264"); // h264?
		
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
		
		// Open encoder
		encoder.open(null, null);
		
		// Create converter from RGB to YCrCb format
		MediaPictureConverter converter = null;
		final MediaPicture picture = MediaPicture.make(encoder.getWidth(), encoder.getHeight(), pixelFormat);
		picture.setTimeBase(framerate);
		
		// Example Video Generation
		int duration = 1; // 1s
		int framesToWrite = (int) (duration / framerate.getDouble());
		
		final MediaPacket packet = MediaPacket.make();
		
		// Generate red color array
		int[] rgbArray = new int[videoWidth*videoHeight];
		for (int y = 0; y < videoHeight; y++)
		{
			for (int x = 0; x < videoWidth; x++)
			{
				int idx = y*videoWidth + x;
				rgbArray[idx] = 0xff0000;
			}
		}
		
		// Write Example Video
		for (int i = 0; i < framesToWrite; i++) 
		{
			BufferedImage frame = new BufferedImage(videoWidth, videoHeight, BufferedImage.TYPE_INT_RGB);
			fillBufferedImageData(frame, rgbArray, videoWidth, videoHeight);
			
			// Convert from RGB to YUV420P
			if (converter == null)
				converter = MediaPictureConverterFactory.createConverter(frame, picture);
			converter.toPicture(picture, frame, i);
			
			// Encode frame
			do 
			{
				encoder.encode(packet, picture);
				if (packet.isComplete())
					muxer.write(packet, false);
			} while (packet.isComplete());

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
