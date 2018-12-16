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


	private int direction;
	private int nextDirection;
	private Handler handler;

	public Snake(int x, int y, int resolution, Handler handler){
		this.resolution = resolution;
		this.x = x;
		this.y = y;
		head = new Head(x, y, this.resolution);
		tail = new Tail(x ,y + resolution, this.resolution);
		direction = NORTH;
		this.handler = handler;
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
		if (nextDirection != direction && nextDirection != 0){
			direction = nextDirection;
		}

		tail.move(x, y);

		switch (direction){
			case NORTH:
				y -= resolution;
				break;
			case SOUTH:
				y += resolution;
				break;
			case WEST:
				this.x -= resolution;
				break;
			case EAST:
				this.x += resolution;
				break;
		}

		head.move(x ,y);
	}

	public void render(Graphics g){
		head.render(g);
		tail.render(g);
	}

	public int getDirection(){
		return direction;
	}

	public void setDirection(int direction){
		this.direction = direction;
	}

}
