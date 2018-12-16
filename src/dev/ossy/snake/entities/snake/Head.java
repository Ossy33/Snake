package dev.ossy.snake.entities.snake;

import java.awt.*;

public class Head {

	private Color color;
	private int x, y;
	private int width;

	protected Head(int x, int y, int width){
		color = Color.BLUE;
		this.width = width;
		this.y = y;
		this.x = x;
	}
	public void render(Graphics g){
		g.setColor(color);
		g.fillRect(x, y, width, width);
	}


	public void move(int x, int y){
		this.x = x;
		this.y = y;
	}


	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}


}
