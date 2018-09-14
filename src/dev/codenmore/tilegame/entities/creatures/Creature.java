package dev.codenmore.tilegame.entities.creatures;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.Entity;
import dev.codenmore.tilegame.tile.Tile;

public abstract class Creature extends Entity {
//abstract because we want to specify a specific type of creature
	
	//final variables cannot be changed 
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 56;
	public static final int DEFAULT_CREATURE_HEIGHT = 112; 
	
	protected float speed;
	protected float xMove, yMove; 
	
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		//this super method leads to the Entity classes constructor 
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move() {
		if(!checkEntityCollisions(xMove, 0f)) {
			moveX(); //checking with entityCollisions to see where we are going to be moving
		}
		
		if(!checkEntityCollisions(0f, yMove)) {
			moveY();
		}
	}  
	
	public void moveX() {
		if(xMove > 0) { //Moving right
			
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH; //Gives you the x coordinate of tile your trying to move into, (/tile) gives it in tile coordinates
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && 
			   !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) { //equation gives us the y position of the upper right of our bounding box. If no solid tile here. && gives lower left of our box
				x += xMove;
			}else {//if there is a collision with a tile moving to the right
				x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1; //moves as far as we can towards the tile without going through it, - 1 allows you to still move up and down along tile
			}
			
		}else if(xMove < 0) { //Moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH; //Gives you the x coordinate of tile your trying to move into, (/tile) gives it in tile coordinates
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && 
			   !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) { //equation gives us the y position of the upper right of our bounding box. If no solid tile here. && gives lower left of our box
				x += xMove;
			}else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0) { //Up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && //left side of bounding rectangle
			   !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){ //right side of bounding rectangle 
				y += yMove; //if there isn't a collision here we move up y
			}else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}
			
		}else if(yMove > 0) { //Down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) && //left side of bounding rectangle
			   !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)){ //right side of bounding rectangle 
				y += yMove; //if there isn't a collision here we move up y
			}else {
				y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
			}
			
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		//takes some tile x and y coordinate and if its solid return true if not return false
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	//Getters and Setters

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	

}
