package dev.ossy.snake.entities.snake;

import java.awt.*;

public class BodyPiece {

	private int x, y;
	private Color color;
	private int width;

	protected BodyPiece(int x,int y,int width){
		color = Color.ORANGE;
		this.width = width;
		this.x = x;
		this.y = y;
	}

	public void render(Graphics g){
		g.setColor(color);
		g.fillRect(x, y, width, width);
	}

	public void move(int x, int y){
		this.x = x;
		this.y = y;
	}

	//Getters and setters



	public void setColor(Color color){
		this.color = color;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
