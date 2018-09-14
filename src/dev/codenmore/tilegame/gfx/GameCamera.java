package dev.codenmore.tilegame.gfx;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.tile.Tile;

public class GameCamera {
	
	private Handler handler;
	private float xOffset, yOffset;

	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;  //xOffset = 10 means it will be pushed to the left 10 pixels (-10)
		this.yOffset = yOffset; //Offset variables are how far off we draw something based on its original value 
	}
	
	public void checkBlankSpace() {//checks if the camera is showing any blank space to us, if there is, get rid of blank space
		//if xOffset is < 0, then we are showing blank space, if > 0, we are not showing blank space
		if(xOffset < 0) {
			xOffset = 0;
		}else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) { //multiply by TILEWIDTH to get it in pixels, handler.getWidth is width of our window
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
			
		}	
		if(yOffset < 0) {
			yOffset = 0;
		}else if(yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		}
		
	}
	
	public void centeronEntity(Entity e) {
		//going to set the x and y offsets of the game camera to be the correct numbers
		//that way the camera is centered around the entity (player in this case)
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace(); //makes sure our calculations here arn't showing any blank space
	}
	
	public void move(float xAmt, float yAmt) {
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace(); //whenever we move the camera, checks the blank space
	}
	
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
