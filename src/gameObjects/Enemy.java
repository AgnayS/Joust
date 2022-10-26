package gameObjects;

import java.awt.Image;

public class Enemy extends DynamicGameObject {
	private final static int DEFAULT_POINT_VALUE = 10;
	private final static int ENEMY_WIDTH = 1;
	private final static int ENEMY_HEIGHT = 1;
	private final static Image MOVING_LEFT_ENEMY_SPRITE = null, MOVING_RIGHT_ENEMY_SPRITE = null, JUMPING_LEFT_ENEMY_SPRITE = null, JUMPING_RIGHT_ENEMY_SPRITE = null, FALLING_LEFT_ENEMY_SPRITE = null, FALLING_RIGHT_ENEMY_SPRITE = null;

	private int pointValue;
	
	public Enemy(int xPos, int yPos) {
		super(ENEMY_WIDTH, ENEMY_HEIGHT, MOVING_RIGHT_ENEMY_SPRITE, xPos, yPos);
		this.pointValue = DEFAULT_POINT_VALUE;
	}
	
	@Override
	public void handleHeroInteraction(Hero hero) {
		
	}
}
