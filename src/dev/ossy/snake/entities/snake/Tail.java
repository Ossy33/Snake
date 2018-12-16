package dev.ossy.snake.entities.snake;

import java.awt.*;
import java.util.ArrayList;

public class Tail {

	private ArrayList<BodyPiece> bodyPieces;
	private int x, y;
	private int width;

	/**Create a tail with 2 snake pieces*/
	protected Tail(int x, int y, int width){
		this.x = x;
		this.y = y;
		bodyPieces = new ArrayList<BodyPiece>();
		bodyPieces.add(new BodyPiece(x, y + width, width));
		bodyPieces.add(new BodyPiece(x, y, width));
	}


	public BodyPiece getPeice(int i){
		return bodyPieces.get(i);
	}

	public int getLength(){
		return bodyPieces.size();
	}

	public void render(Graphics g){

		for (BodyPiece i: bodyPieces) {
			i.render(g);
		}
	}

	public void move(int x, int y){
		this.x = x;
		this.y = y;
		movePieces();
	}

	public void addBodyPiece(int x, int y){
		bodyPieces.add(new BodyPiece(x, y, width));
	}


	private void movePieces(){
		BodyPiece b = bodyPieces.get(0);
		bodyPieces.remove(0);
		b.move(x, y);
		bodyPieces.add(b);

	}
}
