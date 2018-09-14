package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path) {
		//returns an object of our loaded image
		//path is location of image
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
			//loads the image in	
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
			//exits out of the game if image is not loaded
		}
		return null;
	}
}
