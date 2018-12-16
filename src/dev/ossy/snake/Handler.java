package dev.ossy.snake;

import dev.ossy.snake.input.KeyManager;

public class Handler {

	private Game game;
	private KeyManager keyManager;

	public Handler(){

	}


	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setKeyManager(KeyManager keyManager){
		this.keyManager = keyManager;
	}

	public KeyManager getKeyManager(){
		return keyManager;
	}
}
