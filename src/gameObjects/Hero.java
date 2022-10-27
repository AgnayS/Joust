package gameObjects;

import java.awt.Image;

public class Hero extends DynamicGameObject {
	private final static int HERO_WIDTH = 1;
	private final static int HERO_HEIGHT = 1;
	private final static int DAMAGE_COOLDOWN = 1;
	private final static Image MOVING_LEFT_HERO_SPRITE = null, MOVING_RIGHT_HERO_SPRITE = null, JUMPING_LEFT_HERO_SPRITE = null, JUMPING_RIGHT_HERO_SPRITE = null, FALLING_LEFT_HERO_SPRITE = null, FALLING_RIGHT_HERO_SPRITE = null;

	private int lives;
	private int score;
	private int damageCooldown;
	
	

	public Hero(int xPos, int yPos) {
		super(HERO_WIDTH, HERO_HEIGHT, MOVING_RIGHT_HERO_SPRITE, xPos, yPos);
		this.lives = 3;
	}
}
