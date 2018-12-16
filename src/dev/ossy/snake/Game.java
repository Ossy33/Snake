package dev.ossy.snake;

import dev.ossy.snake.entities.food.Food;
import dev.ossy.snake.entities.snake.Snake;
import dev.ossy.snake.display.Display;
import dev.ossy.snake.states.GameState;
import dev.ossy.snake.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

	public String title;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private int width, height;

	private Display display;
	private Thread thread;
	private boolean running = false;

	private BufferStrategy bs;
	private Graphics g;


	private GameState state;
	private Handler handler;
	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		handler = new Handler();
		state = new GameState(width, height, handler);
	}

	private void tick(){
		state.tick();
	}

	private void init() {
		display = new Display(title, width, height);
		//Allows us to use the keyboard.



		display.getFrame().addKeyListener(handler.getKeyManager());
	}

	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		state.render(g);


		bs.show();
		g.dispose();
	}


	public synchronized void start() {

		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void run() {
		init();
		//The amount of time we want to call tick() and render() method every second.
		int fps = 60;
		//The time per frame in ns.
		double timePerTick = 1000000000 / (double) fps;
		//The amount of time we have to call the tick() and render() method again.
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		//How many times the tick() and render() methods were called.
		int ticks = 0;

		while (running) {
			//Current time of our computer in nanoseconds.
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			//if true we have to tick() and render().
			if (delta >= 1) {
				//This loop updates 60 times a second
				tick();
				render();

				if (ticks % 20 == 0) {
					state.getSnake().move();
				}

				ticks++;
				delta--;
			}

			//Checks if the timer has exceeded 1 second
			if (timer >= 1000000000) {
				System.out.println("Ticks: " + ticks);

				ticks = 0;
				timer = 0;
			}

		}
		stop();
	}


}
