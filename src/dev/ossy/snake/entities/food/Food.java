package dev.ossy.snake.entities.food;

import java.awt.*;
import java.util.Random;

public class Food {

	private Color color;

	private int width, height;
	private int resolution;

	private int x, y;
	private int maxX, maxY;

	private Random rand = new Random();

	public Food(int width, int height, int resolution){
		this.width = width;
		this.height = height;
		this.resolution = resolution;

		color = Color.BLACK;

		maxX = width / resolution;
		maxY = height / resolution;
		newLocation();
	}

	public void newLocation(){
		x = rand.nextInt(maxX);
		y = rand.nextInt(maxY);
	}

	public void render(Graphics g){
		g.setColor(color);
		g.fillRect(x, y, resolution, resolution);
	}

	//Getters and setters

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
}
