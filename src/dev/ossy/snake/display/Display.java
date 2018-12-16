package dev.ossy.snake.display;

import javax.swing.*;
import java.awt.*;

public class Display {

	private JFrame frame;
	private Canvas canvas;

	private String title;
	private int width, height;

	public Display(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay();
	}

	private void createDisplay(){
		//sets the title of the window
		frame = new JFrame(title);
		//Sets the size of the window
		frame.setSize(width,height);
		//If you close the window the program will close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Changes so you can't change the windowsize
		frame.setResizable(false);
		//Sets so the window is centered when the window pops up
		frame.setLocationRelativeTo(null);
		//Sets so you can see the window
		frame.setVisible(true);

		canvas = new Canvas();
		//Just look at the name and you will understand what they do.
		canvas.setPreferredSize(new Dimension(width,height));
		canvas.setMaximumSize(new Dimension(width,height));
		canvas.setMinimumSize(new Dimension(width,height));

		canvas.setFocusable(false);

		frame.add(canvas);
		frame.pack();

	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JFrame getFrame(){
		return frame;
	}
}
