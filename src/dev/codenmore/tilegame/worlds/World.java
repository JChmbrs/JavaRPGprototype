package dev.codenmore.tilegame.worlds;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.EntityManager;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.statics.Tree;
import dev.codenmore.tilegame.entities.statics.Rocks.RockAddy;
import dev.codenmore.tilegame.entities.statics.Rocks.RockBlurite;
import dev.codenmore.tilegame.entities.statics.Rocks.RockClay;
import dev.codenmore.tilegame.entities.statics.Rocks.RockCoal;
import dev.codenmore.tilegame.entities.statics.Rocks.RockGem;
import dev.codenmore.tilegame.entities.statics.Rocks.RockMith;
import dev.codenmore.tilegame.entities.statics.Rocks.RockRune;
import dev.codenmore.tilegame.entities.statics.Rocks.RockSilver;
import dev.codenmore.tilegame.tile.Tile;
import dev.codenmore.tilegame.utils.Utils;

public class World {

	private Handler handler;
	private int width, height; //width and height of world1.txt file, currently 20 by 20
	private  int spawnX, spawnY;
	private int[][] worldtiles;  //int because tiles have int "id". First[] is rows or x. Second[] is columns or y.
	//Entities
	private EntityManager entityManager;
			
	public World(Handler handler, String path) {  //loading a world from a file, 'path' is a location in our computer
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 65, 65));
		
		//rocks
		entityManager.addEntity(new RockRune(handler, 130, 1040));
		entityManager.addEntity(new RockAddy(handler, 256, 1040));
		entityManager.addEntity(new RockRune(handler, 325, 1040));
		entityManager.addEntity(new RockAddy(handler, 455, 1040));
		entityManager.addEntity(new RockRune(handler, 256, 975));
		
		
		
		//sidewalk trees
		entityManager.addEntity(new Tree(handler, 130, 260)); //Y starts -64 for trees
		
		entityManager.addEntity(new Tree(handler, 130, 390));
		
		entityManager.addEntity(new Tree(handler, 130, 520));
	
		entityManager.addEntity(new Tree(handler, 130, 650));
		
		entityManager.addEntity(new Tree(handler, 130, 780));
		
		entityManager.addEntity(new Tree(handler, 0, 260));
		
		entityManager.addEntity(new Tree(handler, 0, 390));
		
		entityManager.addEntity(new Tree(handler, 0, 520));
		
		entityManager.addEntity(new Tree(handler, 0, 650));
		
		entityManager.addEntity(new Tree(handler, 0, 780));
		
		//random trees
		entityManager.addEntity(new Tree(handler, 390, 520)); 
		
				
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick() {
		//updates positions of tiles
		entityManager.tick();
		
	}
	
	public void render(Graphics g) { //xOffset is how much the camera has moved in pixels. Divide that by TILEWIDTH to get that number in terms of tiles. 
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH); //contains the tile that the user can currently see thats on the far left of the screen that is visible 
		//returns whatever number is greater (max function here)
		//xStart will either equal 0 or 
		int xEnd = (int) Math.min(width,  (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		//renders every tile to the screen
		for(int y = yStart; y < yEnd; y++) {  //both of these loop through every tile
			for(int x = xStart; x < xEnd; x++) {
				getTile(x,y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset())); //gets the tile and renders it
				//multiplying here makes it so the tiles are not drawn onto the same space
				//but instead drawn onto one after the other after the other 
				//(int) turns float value of game.getGameCamera().getxOffset into int
				//subtracting game.getGameCamera().getxOffset (offset) by the position in which we are rendering these tiles on the screen 
			}
		}
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) { //returns Tile object
		//finds the 'id' in the tiles array based on the put in x and y variables
		//and it will return that tile
		if (x < 0 || y < 0 || x >= width || y >= height) //width and height of the map
			return Tile.grassTile; //prevents errors, game thinks player is standing on grasstile if goes out of bounds of map
		
		
		Tile t = Tile.tiles[worldtiles[x][y]]; //creates tile object t that is a tile[] in the Tile class
									      	   //[tiles[x][y]] is the tile array in this class
		if(t == null) //if a position in the 256 array in the Tile class is not set
			return Tile.grassTile;
		return t;
	}
	
	private void loadWorld(String path) {
		//loads in the world
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+"); //takes our file and splits up every number to a string and into a string array, that  way we can access int's individually 
		width = Utils.parseInt(tokens[0]); //converts strings to int's 
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		worldtiles = new int[width][height];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				worldtiles[x][y] = Utils.parseInt(tokens[(x + (y * width)) + 4]); //converting tokens array into multi-dimensional array 
			}   //each of the worldtiles elements are going to be equal to each tile 'id' in the tokens array, so from element 4 onwards 
			    //within the tokens array has the 'id' of the tile we want to map onto the screen
		}
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
