package gameObjects;

import java.awt.Image;

public class Enemy extends GameObject {
	private final static int ENEMY_WIDTH = 1;
	private final static int ENEMY_HEIGHT = 1;
	private final static Image ENEMY_SPRITE = null;

	public Enemy(int xPos, int yPos) {
		super(ENEMY_WIDTH, ENEMY_HEIGHT, ENEMY_SPRITE, xPos, yPos);
	}
}
