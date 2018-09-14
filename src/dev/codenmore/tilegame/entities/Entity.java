package dev.codenmore.tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;


import dev.codenmore.tilegame.Handler;


public abstract class Entity {

	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected int health;
	public static final int DEFAULT_HEALTH = 10;
	protected boolean active = true; //whether entity is dead or alive
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
	//starting position of our entity
		this.handler = handler;
		this.x = x; //variables in our class equal to the variables we set in
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;

		bounds = new Rectangle(15, 22, 32, 80); // 0 and 0 are starting top left position of player and width and height are of player
		
	}
	
	//Getters and Setters 
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public abstract void tick();
	//updates all variables and move
	
	public abstract void render(Graphics g); 
	//entity draws itself to the screen
	
	public void die() {
		System.out.println("entity died");
	}
		
	
	public void hurt(int amt) {
		health -=amt;
		if(health <= 0) {
			active = false;
			die();
		}
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		//going to test every entity in our game and see if it collides with an entity
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) { //getting the list of entities and looping through each one for collisions through the entity in the Entity class (above code) and the entity in the list
			
			if(e.equals(handler.getWorld().getEntityManager().getPlayer())) {
				continue; //so player doesn't collide with self
			}
			
			if(e.getCollisionBounds(0f,  0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				return true; //0f and 0f return rectangle that is size of our already set bounding box
			}
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		//returns a new rectangle around the area of the entity that is solid
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	
}
