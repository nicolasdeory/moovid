package aiss.service.video;

public interface FrameGenerator {

	public Frame next();
	public boolean hasNext();
	public int getWidth();
	public int getHeight();
	public int getTotalDuration();
	
}
