package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;

public class Hero extends DynamicGameObject {
	private final static int HERO_WIDTH = 20;
	private final static int HERO_HEIGHT = 20;
	private final static int DAMAGE_COOLDOWN = 1;
	private final static Image MOVING_LEFT_HERO_SPRITE = null, MOVING_RIGHT_HERO_SPRITE = null, JUMPING_LEFT_HERO_SPRITE = null, JUMPING_RIGHT_HERO_SPRITE = null, FALLING_LEFT_HERO_SPRITE = null, FALLING_RIGHT_HERO_SPRITE = null;
	public final static double HERO_SPEED = 8;
	
	private int lives;
	private int score;
	private int damageCooldown;
	
	

	public Hero(int xPos, int yPos) {
		super(HERO_WIDTH, HERO_HEIGHT, MOVING_RIGHT_HERO_SPRITE, xPos, yPos);
		this.lives = 3;
	}
	
	@Override
	public void drawOn(Graphics2D g2) {
		g2.fillOval((int)xPos,(int)yPos, HERO_WIDTH, HERO_HEIGHT);
	}
	
	public void moveRight() {
		xVel = HERO_SPEED;
	}
	
	public void moveLeft() {
		xVel = -HERO_SPEED;
	}
	
	public void moveUp() {
		yVel = -HERO_SPEED;
	}
	
	public void stopLeftRight() {
		xVel = 0;
	}

	public void stopUpDown() {
		yVel = 0;
	}
}
