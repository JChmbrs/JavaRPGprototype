package dev.codenmore.tilegame.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[256];  //grassTile is 0 position of this array
	public static Tile grassTile = new GrassTile(0); //0 is id that GrassTile takes in
	public static Tile treeTile = new TreeTile(1);   
	public static Tile stoneTile = new StoneTile(2);
	
	
	//CLASS
	
	public static final int TILEWIDTH = 65, TILEHEIGHT = 65;
	
	protected BufferedImage texture;
	protected final int id; //every different tile has different id
	
	
	public Tile(BufferedImage texture, int id) { //Constructor 
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this; //"this" is equal to the tile that we are creating
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false; //if false, then you can walk through this tile
	}
	
	public int getId() {
		return id;
	}
	
}
