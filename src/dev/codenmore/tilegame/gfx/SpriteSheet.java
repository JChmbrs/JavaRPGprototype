package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	//stores a buffered image

	private BufferedImage sheet;
	//buffered image object called sheet
	
	public SpriteSheet(BufferedImage sheet) {
		//constructor that takes in a bufferedimage object as parameter (which is our spritesheet)
		this.sheet = sheet;
		//now our sprite sheet class has access to the spritesheet image that we passed into it
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		//crops individual images from sprite sheet
		return sheet.getSubimage(x, y, width, height);
		//will return one buffered image of what ever area we specify 
				
	}
}


