package dev.codenmore.tilegame.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {
	//Holds all of the core components of the user interface objects
	//Buttons, sliders menu wise
	
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	
	
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(520, 392, 71, 67); //bounds in our image that you have to click to play the game
		
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick();
	
	public void onMouseMove(MouseEvent e) {
		//if mouse is over our bounded menu object
		if(bounds.contains(e.getX(), e.getY()))
			hovering = true;
		else
			hovering = false;
		
	}
	
	public void onMouseRelease(MouseEvent e) {
		if(hovering)
			onClick();
	}
	
	
	//Getters and Setters
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


	public boolean isHovering() {
		return hovering;
	}


	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
}
