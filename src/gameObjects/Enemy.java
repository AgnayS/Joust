package gameObjects;

import java.awt.Image;

public abstract class Enemy extends DynamicGameObject {
	private final static int ENEMY_WIDTH = 20;
	private final static int ENEMY_HEIGHT = 20;

	private int pointValue;
	
	public Enemy(int xPos, int yPos, Image sprite, int pointValue) {
		super(ENEMY_WIDTH, ENEMY_HEIGHT, sprite, xPos, yPos);
		this.pointValue = pointValue;
	}
}
