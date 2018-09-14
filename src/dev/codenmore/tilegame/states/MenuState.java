package dev.codenmore.tilegame.states;

import java.awt.Graphics;


import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.ui.ClickListener;
import dev.codenmore.tilegame.ui.UIImageButton;
import dev.codenmore.tilegame.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(0, 0, 1024, 768, Assets.btn_start, new ClickListener() {
				//Creates instance of ClickListener class on the fly
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null); //this way all the buttons in the menu state no longer get any UI input 
				State.setState(handler.getGame().gameState);
				
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
		
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
		
	}
	
		
}


