package dev.codenmore.tilegame.entities.creatures;


import java.awt.Graphics;
import java.awt.image.BufferedImage;


import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Animation;
import dev.codenmore.tilegame.gfx.Assets;


public class Player extends Creature{
	
	//Animations
	private Animation animDown, animLeft, animRight, animUp, animStill;
	private int direction = 0; //initializing at 1 because character starts facing down, which is index of 0 in array 
	
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		bounds.x = 15;
		bounds.y = 22;
		bounds.width = 32;
		bounds.height = 80;
		
		//Animations
		animDown = new Animation(300, Assets.player_down); //half a second
		animLeft = new Animation(300, Assets.player_left);
		animRight = new Animation(300, Assets.player_right);
		animUp = new Animation(300, Assets.player_up);
		animStill = new Animation(300, Assets.player_still);
	}

	@Override
	public void tick() { //60 times a second
		
		//Animations
		animDown.tick();
		animLeft.tick();
		animRight.tick();
		animUp.tick();
		animStill.tick();

		//Movement
		getInput(); //sets the xMove and yMove variables to what they should be
					//^(speed or -speed)cx
		move();
		handler.getGameCamera().centeronEntity(this); //this means this player
	}
	
	private void getInput() { //happens 60 times a second
		//manages anything that input (keyboard stuff) should do
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up) //if player is moving up
			yMove = -speed; 
		if(handler.getKeyManager().down) 
			yMove = speed; 
		if(handler.getKeyManager().left) 
			xMove = -speed; 
		if(handler.getKeyManager().right) 
			xMove = speed; 
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if (xMove != 0 || yMove != 0) {
			if (xMove < 0) {
				direction = 1;
			    return animLeft.getCurrentFrame();
			} else if (xMove > 0) {
				direction = 2;
			    return animRight.getCurrentFrame();
			} else if (yMove < 0) {
			    direction = 3;
			    return animUp.getCurrentFrame();
			} else {
			    direction = 0;
			    return animDown.getCurrentFrame();
			}
			} else return Assets.player_still[direction];
	}
}

