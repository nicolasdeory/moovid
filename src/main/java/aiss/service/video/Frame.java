package aiss.service.video;

import java.awt.image.BufferedImage;

public class Frame {

	private BufferedImage data;
	private int duration;
	
	/**
	 * Returns the image data
	 */
	public BufferedImage getData() {
		return data;
	}
	public void setData(BufferedImage data) {
		this.data = data;
	}
	/**
	 * Returns the duration in frames
	 */
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public static Frame of(BufferedImage img, int duration)
	{
		return new Frame(img, duration);
	}
	
	private Frame(BufferedImage img, int duration) 
	{
		this.data = img;
		this.duration = duration;
	}
}
