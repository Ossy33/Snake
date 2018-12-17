package dev.ossy.snake.entities.snake;

import dev.ossy.snake.Handler;
import dev.ossy.snake.input.KeyManager;

import java.awt.*;

public class Snake {

	public static final int NORTH = 1, SOUTH = 2, WEST = 3, EAST = 4;

	private Head head;
	private Tail tail;
	private int resolution;
	private int x, y;

	private int height, width;

	private int direction;
	private int nextDirection;
	private Handler handler;

	private boolean increaseSize;

	public Snake(int x, int y, int resolution, Handler handler){
		this.resolution = resolution;
		this.x = x;
		this.y = y;
		head = new Head(x, y, this.resolution);
		tail = new Tail(x ,y + resolution, this.resolution);
		direction = NORTH;
		this.handler = handler;

		this.height = handler.getGame().getHeight();
		this.width = handler.getGame().getWidth();
	}

	public void tick(){
		handler.getKeyManager().tick();

		if (handler.getKeyManager().down && direction != NORTH){
			nextDirection = SOUTH;
		}else if (handler.getKeyManager().left && direction != EAST){
			nextDirection = WEST;
		}else if (handler.getKeyManager().right && direction != WEST){
			nextDirection = EAST;
		}else if (handler.getKeyManager().up && direction != SOUTH){
			nextDirection = NORTH;
		}

	}

	public void move(){
		if (!increaseSize){
			tail.move(x, y);
		}else {
			tail.addBodyPiece(x, y);
			increaseSize = false;
		}

		if (nextDirection != direction && nextDirection != 0){
			direction = nextDirection;
		}


		switch (direction){
			case NORTH:
				y = (y - resolution + height) % height;
				break;
			case SOUTH:
				y = (y + resolution + height) % height;
				break;
			case WEST:
				x = (x - resolution + width) % width;
				break;
			case EAST:
				x = (x + resolution + width) % width;
				break;
		}

		head.move(x ,y);
	}

	public void render(Graphics g){
		head.render(g);
		tail.render(g);
	}

	public boolean hasCrashed(){
		for (BodyPiece i : tail.getBodyPieces()){
			if (i.getY() == head.getY() && i.getX() == head.getX()){
				return true;
			}
		}
		return false;
	}

	public int getDirection(){
		return direction;
	}

	public void setDirection(int direction){
		this.direction = direction;
	}

	public int getX(){
		return x;
	}

	public int getY() {
		return y;
	}

	public void increaseLength() {
		increaseSize = true;
	}
}
