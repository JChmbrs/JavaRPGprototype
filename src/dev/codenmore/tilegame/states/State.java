package dev.codenmore.tilegame.states;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;

public abstract class State {
	
	private static State currentState = null;
	//State object called currentState initialized to nothing
	//going to hold what current state we want to tick() and render() in our game
	
	public static void setState(State state) {
		//sets the current state 
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	//CLASS
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	//takes in the 'paint brush' that way states can use the brush directly
}
