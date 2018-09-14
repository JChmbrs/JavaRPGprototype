package dev.codenmore.tilegame.tile;

import dev.codenmore.tilegame.gfx.Assets;

public class TreeTile extends Tile {

	public TreeTile(int id) {
		super(Assets.tree, id); //calls the title classes constructor
	}

	@Override
	public boolean isSolid() {
		//if true then you can't walk through this tile
		return true;
	}
	
}
