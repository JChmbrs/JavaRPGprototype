package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private int speed, index; 
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed; //how fast we switch frames
		this.frames = frames;
		index = 0; //element in the array
		timer = 0;
		lastTime = System.currentTimeMillis();
		
	}
	
	public void tick() {
		//adding the amount of millisecs that have passed since the last tick() method call, and this tick() method call
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer = 0;
			if(index >= frames.length)
				//prevents errors from happening, how much speed increments have passed, goes back to original frame of animation when it goes all the way through the array
				index = 0;
			
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
		
	}
	
}
