package dev.ossy.snake.entities.snake;

import dev.ossy.snake.input.KeyManager;

import java.awt.*;

public class Snake {

	public static final int NORTH = 1, SOUTH = 2, WEST = 3, EAST = 4;

	private Head head;
	private Tail tail;
	private int width;
	private int x, y;


	private int direction;

	private KeyManager keyManager;

	public Snake(int x, int y, int width){
		this.width = width;
		this.x = x;
		this.y = y;
		head = new Head(x, y, this.width);
		tail = new Tail(x ,y + width, this.width);
		direction = NORTH;

		keyManager = new KeyManager();
	}

	public void tick(){
		keyManager.tick();

		if (keyManager.down){
			direction = SOUTH;
		}else if (keyManager.left){
			direction = WEST;
		}else if (keyManager.right){
			direction = EAST;
		}else if (keyManager.up){
			direction = NORTH;
		}
	}

	public void move(){

		tail.move(x, y);

		switch (direction){
			case NORTH:
				y -= width;
				break;
			case SOUTH:
				y += width;
				break;
			case WEST:
				this.x -= width;
				break;
			case EAST:
				this.x += width;
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

	public KeyManager getKeyManager(){
		return keyManager;
	}

}
