package dev.codenmore.tilegame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame; 

public class Display {

	private JFrame frame;
	//Jframe object (this is the window)
	private Canvas canvas;
	//allows graphics to be drawn in window (draw it in the canvas,
	//then put it in the frame)
	
	
	
	private String title;
	private int width, height; 
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height; 
		
		createDisplay();
	}

	private void createDisplay() { 
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		//^wont close out of game even if you close out of window
		frame.setResizable(false);
		//cant resize the window
		frame.setLocationRelativeTo(null);
		//sets window location at the center and not at the side
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		//size we want the canvas to be
		canvas.setMaximumSize(new Dimension(width, height));
		//stays at this maximum size
		canvas.setMinimumSize(new Dimension(width, height));
		//stays at this minimum size
		canvas.setFocusable(false);
		
		
		frame.add(canvas);
		//add the canvas to the frame
		frame.pack();
		//resizes the frame a little so we can see the canvas fully
		
	}
	
	public Canvas getCanvas() {
		//use Get methods so we can use these methods outside of this class, so other classes
		//can use them as well
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
