package gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Loads in the specified image and returns it.
 */
public class ImageLoader
{
    public ImageLoader() {

    }

    public BufferedImage loadImage(String name) {
	BufferedImage img = null;
	try {
	    File file = new File(name);
	    img = ImageIO.read(file);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return img;
    }
}
