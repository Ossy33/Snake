package dev.ossy.snake;

public class Main {
	public static void main(String[] args) {
		Game g = new Game("Snake", 20 * 20, 20 * 20);
		g.start();
	}
}
