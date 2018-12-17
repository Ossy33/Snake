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

	private int score;

	private int resolution = 10;

	public GameState(int width, int height, Handler handler) {
		super(handler);
		score = 0;
		keyManager = new KeyManager();

		snake = new Snake( 100, 400, 10, handler);
		food = new Food(width, height,10);
	}

	public void init(){
		food.newLocation();
		handler.setKeyManager(keyManager);

	}

	@Override
	public void tick() {
		keyManager.tick();
		snake.tick();
		if (snake.hasCrashed()){
			System.out.println("game over!");
			handler.getGame().stop();
		}
		if (snake.getX() == food.getX() && snake.getY() == food.getY()){
			food.newLocation();
			score++;
			snake.increaseLength();
			System.out.println("score:" + score);
		}

	}

	@Override
	public void render(Graphics g) {
		snake.render(g);
		food.render(g);
	}

	public Snake getSnake(){
		return snake;
	}

	public Food getFood() {
		return food;
	}
}
