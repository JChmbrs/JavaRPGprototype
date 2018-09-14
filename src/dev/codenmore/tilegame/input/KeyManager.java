package dev.codenmore.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener  {
//Keylistener is built in java class
	
	private boolean[] keys;
	public boolean up, down, left, right;
	public boolean aUp, aDown, aLeft, aRight;
	
	
	
	public KeyManager() {
		keys = new boolean[256]; //puts all keys in a true/false array based on whether or not they are pressed
	}
	
	public void tick() {
	//whenever tick() method is called, we are updating the up, down, left, right variables
	//based on whether  or not the w,s,a,d keys are being pressed and set them to a boolean 
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		
		aUp = keys[KeyEvent.VK_UP];
		aDown = keys[KeyEvent.VK_DOWN];
		aLeft = keys[KeyEvent.VK_LEFT];
		aRight = keys[KeyEvent.VK_RIGHT];
		
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true; //gets key code for key that was pressed
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false; //key that was pressed is no longer pressed
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
