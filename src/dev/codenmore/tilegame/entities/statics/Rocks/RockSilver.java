package dev.codenmore.tilegame.entities.statics.Rocks;

import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.statics.StaticEntity;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.tile.Tile;
	
public class RockSilver extends StaticEntity {
		
	public RockSilver(Handler handler, float x, float y) {
		super(handler, x, y, 54, 52);
			
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 66;
		bounds.height = 16;
			
	}

	@Override
	public void tick() {
			
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rocks[6], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
	}
		
}