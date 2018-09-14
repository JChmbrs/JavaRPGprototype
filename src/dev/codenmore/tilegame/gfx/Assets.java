package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

//houses images, sounds or pieces of music in our game
public class Assets {
	
	private static final int width = 56, height = 112;
	//width and height of 1 grid space in sprite sheet 
	
	public static BufferedImage grass1, grass2, stone, tree;
	public static BufferedImage[] player_down, player_left, player_right, player_up,
								  player_still;
	public static BufferedImage[] btn_start; //button images
	public static BufferedImage[] rocks;
	

	public static void init() {
		//this is going to load everything in for our game, only going to be called once
		//SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet3.png"));
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet3.png"));
		SpriteSheet rocksheet = new SpriteSheet(ImageLoader.loadImage("/textures/RocksSheet.png"));
		//creates SpriteSheet object that houses the spritesheet.png 
		
		grass1 = sheet.crop(width * 3, 0, width, width);
		stone = sheet.crop(width * 10, 0, width, width);
		tree = sheet.crop(width * 11, 0, width, height);
		
		player_down = new BufferedImage[2];
		player_down[0] = sheet.crop(0, 0, width, height);
		player_down[1] = sheet.crop(width, 0, width, height);
		
		player_left = new BufferedImage[2];
		player_left[0] = sheet.crop(width * 5, 0, width, height);
		player_left[1] = sheet.crop(width * 4, 0, width, height);
		
		player_right = new BufferedImage[2];
		player_right[0] = sheet.crop(width * 7, 0, width, height);
		player_right[1] = sheet.crop(width * 8, 0, width, height);
		
		player_up = new BufferedImage[2];
		player_up[0] = sheet.crop(width * 12, 0, width, height);
		player_up[1] = sheet.crop(width * 13, 0, width, height);
		
		player_still = new BufferedImage[4];
		player_still[0] = sheet.crop(width * 2, 0, width, height);
		player_still[1] = sheet.crop(width * 6, 0, width, height);
		player_still[2] = sheet.crop(width * 9, 0, width, height);
		player_still[3] = sheet.crop(width * 14, 0, width, height);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(0, height, 1024, 768);
		btn_start[1] = sheet.crop(0, 880, 1024, 768);
		
		rocks = new BufferedImage[13];
		rocks[0] = rocksheet.crop(0,  0, width, width); //clay
		rocks[1] = rocksheet.crop(width, 0, width, width); //tin
		rocks[2] = rocksheet.crop(width * 2, 0, width, width); //copper
		rocks[3] = rocksheet.crop(width * 3,  0, width, width); //blurite
		rocks[4] = rocksheet.crop(width * 4,  0, width, width); //iron
		rocks[5] = rocksheet.crop(width * 5,  0, width, width); //gem
		rocks[6] = rocksheet.crop(width * 6,  0, width, width); //silver
		rocks[7] = rocksheet.crop(width * 7,  0, width, width); //coal
		rocks[8] = rocksheet.crop(width * 8,  0, width, width); //gold
		rocks[9] = rocksheet.crop(width * 9,  0, width, width); //granite
		rocks[10] = rocksheet.crop(width * 10,  0, width, width); //mith
		rocks[11] = rocksheet.crop(width * 11,  0, width, width); //addy
		rocks[12] = rocksheet.crop(width * 12,  0, width, width); //rune
		
		


	}
}
