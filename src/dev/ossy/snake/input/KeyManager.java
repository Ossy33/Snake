package dev.ossy.snake.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	public boolean up, down, left, right;

	private boolean[] keys;

	public KeyManager(){
		keys = new boolean[256];
	}


	public void tick(){
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		System.out.println("Pressed: " + e.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
