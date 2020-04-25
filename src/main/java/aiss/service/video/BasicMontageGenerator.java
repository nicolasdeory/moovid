package aiss.service.video;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class BasicMontageGenerator implements FrameGenerator 
{
	private static Logger log = Logger.getLogger(BasicMontageGenerator.class.getName());
	
	private final String[] images;
	private int i = 0;
	private int width;
	private int height;
	
	public static BasicMontageGenerator of(String[] imagePaths, int videoWidth, int videoHeight)
	{
		return new BasicMontageGenerator(imagePaths, videoWidth, videoHeight);
	}
	
	private BasicMontageGenerator(String[] imagePaths, int videoWidth, int videoHeight)
	{
		this.images = imagePaths;
		this.width = videoWidth;
		this.height = videoHeight;
	}
	
	@Override
	public Frame next()
	{
		if (i >= images.length)
			throw new IllegalStateException("No more frames to generate");
		BufferedImage img;
		try
		{
			img = ImageIO.read(new File(images[i]));
		} catch (IOException e) 
		{
			log.warning("Image " + images[i] + " not found");
			img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_3BYTE_BGR);
		}
		
		BufferedImage imgNoTransparency = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2d = imgNoTransparency.createGraphics();
		g2d.drawImage(img, 0, 0, null);
		g2d.dispose();
		
		BufferedImage resizedImg = ImageUtil.FitImageToBounds(imgNoTransparency, this.width, this.height);
		i++;
		return Frame.of(resizedImg, 60); // 2 seconds @ 30fps
	}

	@Override
	public boolean hasNext()
	{
		return i < images.length;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getTotalDuration() {
		return images.length * 60;
	}
	
	
	

}
