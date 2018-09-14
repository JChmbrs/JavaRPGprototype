package dev.codenmore.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.GameCamera;
import dev.codenmore.tilegame.input.KeyManager;
import dev.codenmore.tilegame.input.MouseManager;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.MenuState;
import dev.codenmore.tilegame.states.State;

//main class, hold all base code, will start/run/close everything

public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title; 
	
	private boolean running = false;
	//variable that goes in the run() method, while game is running
	private Thread thread;
	//thread object
	
	private BufferStrategy bs;
	//tells the computer how to draw things to the screen
	//buffer is a 'hidden' computer screen within your computer
	//prevents screen flickering
	private Graphics g;
	//allows us to draw things to the canvas, like a paint brush 
	
	//States
	public State gameState;
	public State menuState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	

	//BufferedImage object named testImages

	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		
	}
	
	private void init() { 
		//initializes the graphics of the game, gets stuff ready for our game
		display = new Display(title, width, height);
		//loads the image to the testImage object
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager); //do frame and canvas so which ever one is currently on (frame or canvas), mouse events will happen
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		//getting the JFrame of our display and allows us to access the keyboard
		Assets.init(); //calls the init method and loads in all of our assets
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0); //GameCamera needs Game variable in it so use 'this' because in Game class
		
		gameState = new GameState(handler); //setting our state object gameState as a GameState object
		menuState = new MenuState(handler);
		State.setState(menuState); //sets the current state of the game
		
	}
	
	private void tick() {
		//updates everything for the game
		keyManager.tick(); //happens 60 times a second
		//calls the KeyManager classes tick() method
		if (State.getState() != null);
		//have a state that currently exists
			State.getState().tick();
			//calls the tick method on our current state
	}
	
	private void render() {
		//draws everything for the game
		bs = display.getCanvas().getBufferStrategy();
		//gets the canvas of our current window, sets whatever the buffer strategy is for our canvas
		if(bs == null) {
			//if our canvas doesn't have a buffer
			display.getCanvas().createBufferStrategy(3);
			//creates a buffer, 3 of them (faster because it can calculate a buffer then move the next while it calculates the next)
			return; 
		}
		g = bs.getDrawGraphics();
		//graphics object creates the paint brush
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw here!

		if (State.getState() != null);
		//have a state that currently exists
			State.getState().render(g);
			//draws the current state
		
		//puts our image testImage onto the screen

		//End Drawing!
		bs.show();
		//works all the buffer magic behind 
		g.dispose();
		//makes sure the graphics object gets done with properly
	}
	
	public void run() {
		//runs our thread (implements Runnable)
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;  //about a 0.01 secs?
		//gets us the maximum amount of time that we are allowed to have to run the tick
		//and render methods to achieve the 60 fps 
		double delta = 0;
		//delta is the amount of time we have until we have to call the tick() and render()
		//methods again
		long now;
		long lastTime = System.nanoTime();
		//nanoTime returns the amount of time in nano secs that our computer is running at
		long timer = 0;
		int ticks = 0; 
		
		
		while(running) {
			now = System.nanoTime(); //gives us current time in nano secs of our computer
			
			delta += (now - lastTime) / timePerTick; //runs thousands of times a second 
			//(now - lastTime) gets us the amount of time thats passed since we last called
			//this line of code. Gives us if 16mil nano seconds have passed.
			
			timer += (now - lastTime); //gives us the amount of time since this code last ran	
			lastTime = now;
			
			if(delta >= 1) {  //every 16,666,666.7 nano sec this block of code runs
				//if this condition is met we have to tick() and render() to achieve 
				//60 fps
				tick();
				render();
				ticks++;
				delta--;
				//subtracts delta by 1
			}
			
			if(timer >= 1000000000) {
				//if our timer has been running for 1 sec in nano secs, how many ticks 
				//did we do in that last 1 sec that we just ran
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
				//resets the ticks and timer variables 
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}


	public synchronized void start() { //this code must start before run() can enter its while statement 
		//starts the thread
		if(running)
			return;
			//if our game is already running and start() gets called somewhere it wont run below code
		running = true;
		//tells game to start running
		thread = new Thread(this);
		//runs the game class on a thread
		thread.start();
		//calls the run method
	}
	
	public synchronized void stop() {
		//stops the thread
		if(!running)
			return;
			//if game is not running than it wont run below code
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
	
