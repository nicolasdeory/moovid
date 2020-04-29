package aiss.service.video;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageUtil {

	public static BufferedImage FitImageToBounds(BufferedImage img, int desiredWidth, int desiredHeight)
	{	
		if (img.getWidth() == desiredWidth && img.getHeight() == desiredHeight)
			return img;
		
		BufferedImage resized = new BufferedImage(desiredWidth, desiredHeight, img.getType());
		
		// Resize the image (Fit bounds mode)

		float factorWidth = (float)desiredWidth / img.getWidth();
		float factorHeight = (float)desiredHeight / img.getHeight(); 
		float factor = factorWidth * img.getHeight() > desiredHeight ? factorHeight : factorWidth;
		
		int finalWidth = (int) (img.getWidth() * factor);
		int finalHeight = (int) (img.getHeight() * factor);
		
		int imageX = (desiredWidth - finalWidth) / 2;
		int imageY = (desiredHeight - finalHeight) / 2;
		Graphics2D g = resized.createGraphics(); // this generates a black background
		g.drawImage(img, imageX, imageY, finalWidth, finalHeight, null);
		g.dispose();
		
		return resized;
		
		
	}
	
}
