package dev.ossy.snake;

import dev.ossy.snake.entities.food.Food;
import dev.ossy.snake.entities.snake.Snake;
import dev.ossy.snake.display.Display;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

	public String title;

	private int width, height;

	private Display display;
	private Thread thread;
	private boolean running = false;

	private BufferStrategy bs;
	private Graphics g;

	private Snake snake;
	private Food food;

	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		snake = new Snake( 200, 400, 10);
		food = new Food(width, height, 10);
	}

	private void tick(){
		snake.tick();
	}

	private void init() {
		display = new Display(title, width, height);
		//Allows us to use the keyboard.



		display.getFrame().addKeyListener(snake.getKeyManager());
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
		snake.render(g);
		food.render(g);


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
					snake.move();
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
