package aiss.service.video;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class RedVideoGenerator implements FrameGenerator {

	
	private final BufferedImage redImage;
	private int i = 0;
	
	private RedVideoGenerator()
	{
		this.redImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = redImage.getGraphics();
		g.setColor(Color.RED);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.dispose();
	}
	
	@Override
	public Frame next() 
	{
		return Frame.of(redImage, 30 * 5);
		
	}

	@Override
	public boolean hasNext() {
		return i > 0;
	}
	
	/**
	 * Returns a FrameGenerator that generates a red image for 5 seconds
	 */
	public static RedVideoGenerator of() 
	{
		return new RedVideoGenerator();
	}

	@Override
	public int getWidth() {
		return 1920;
	}

	@Override
	public int getHeight() {
		return 1080;
	}

	@Override
	public int getTotalDuration() {
		return 30 * 5;
	}

}
