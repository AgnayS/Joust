package gameObjects;

import java.awt.Image;

public abstract class Enemy extends DynamicGameObject {

	private final static int ENEMY_WIDTH = 20;
	private final static int ENEMY_HEIGHT = 20;

	private int pointValue;
	
	public Enemy(int xPos, int yPos) {
		super(ENEMY_WIDTH, ENEMY_HEIGHT, MOVING_RIGHT_ENEMY_SPRITE, xPos, yPos);
		this.pointValue = DEFAULT_POINT_VALUE;
	}
	
	@Override
	public void handleHeroInteraction(Hero hero) {
		
	}
}
