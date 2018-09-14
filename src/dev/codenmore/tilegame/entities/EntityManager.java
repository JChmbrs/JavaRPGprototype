package dev.codenmore.tilegame.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.creatures.Player;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		//checks which Y coordinate is lowest and put that in the entities arraylist first, that way we render lowest Y's first
		@Override
		public int compare(Entity a, Entity b) {
			//goes through entity array and compares them to one another 
			//return -1 meaning a should be rendered before b
			//return +1 meaning b should be rendered before a
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) //bottom Y coordinates of entity
				return -1;
			return 1; //else 
		}
	};
	
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler; 
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player); //adding player to entities ArrayList
		
	}
	
	public void tick() {
		//ticks all entities and updates their positions 
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i); //Entity e = entities[i]; doing this
			e.tick();
		}
		entities.sort(renderSorter);
	}
	
	public void render(Graphics g) {
		for(Entity e : entities) { //creates an entity called e and gets it in the arraylist for us
			e.render(g);
		}
	}
	
	public void addEntity(Entity e) {
		//takes an entity and adds it to the entities arraylist 
		entities.add(e);
	}
	
	//GETTERS AND SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}
