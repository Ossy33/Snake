package dev.ossy.snake.states;

import dev.ossy.snake.Handler;
import dev.ossy.snake.entities.food.Food;
import dev.ossy.snake.entities.snake.Snake;
import dev.ossy.snake.input.KeyManager;

import java.awt.*;

public class GameState extends State {

	private KeyManager keyManager;
	private Snake snake;
	private Food food;

	private int resolution = 10;

	public GameState(int width, int height, Handler handler) {
		super(handler);
		keyManager = new KeyManager();
		handler.setKeyManager(keyManager);
		snake = new Snake( 100, 400, 10, handler);
		food = new Food(width, height,10);
	}

	@Override
	public void tick() {
		keyManager.tick();
		snake.tick();
	}

	@Override
	public void render(Graphics g) {
		snake.render(g);
	}

	public Snake getSnake(){
		return snake;
	}

	public Food getFood() {
		return food;
	}
}
