package dev.codenmore.tilegame.states;

import java.awt.Graphics;


import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.worlds.World;

public class GameState extends State {
//have to put methods of State in GameState class
	
	private World world;
	
	public GameState(Handler handler) {
		super(handler);
		//super method calls the constructor of whatever class you extended
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		
	}
	
	@Override
	public void tick() { //happens 60 times a second
		world.tick();

	}
	
	@Override 
	public void render(Graphics g) {
		world.render(g); //do this before player since you don't want player drawn on before world tiles

	}
	
}
