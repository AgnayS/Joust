package gameObjects;

import java.awt.Image;

import mainApp.MainApp;
//creation of Enemy as an abstract class
public abstract class Enemy extends DynamicGameObject {
	private final static int ENEMY_WIDTH = 32;
	private final static int ENEMY_HEIGHT = 32;
	private int ENEMY_SPEED = 0;
	private int ENEMY_VERTICAL_SPEED=0;

	
	public Enemy(int xPos, int yPos, Image sprite, int enemySpeed, int enemyVerticalSpeed) {
		super(ENEMY_WIDTH, ENEMY_HEIGHT, sprite, xPos, yPos);
		ENEMY_SPEED =  enemySpeed;
		enemyVerticalSpeed = enemyVerticalSpeed;
	}
	@Override
	public void handleHeroInteraction(Hero hero) {

		if(hero.getyPos() == this.getyPos()) { //No winner!IN ORDER FOR BOUNCING THIS NEEDS TO BE THE FIRST IF
			xVel = hero.getxVel()-xVel;
			hero.setxVel(-xVel);
			this.bounceCooldown = 3;
			hero.setBounceCooldown(3);
		}
		if(hero.getyPos() < -3 + this.getyPos()){ //Hero Win! Adjusting for object bouncing when changing enemy y position			
			this.markToRemove(); //Egg is being spawned inside of update game
		}
		if(hero.getyPos() > 3 + this.getyPos()) {//Hero Loses!
			hero.die(System.currentTimeMillis());
		}
	}
	public void moveRight() {
		xVel = ENEMY_SPEED;
	}
	
	public void moveLeft() {
		xVel = -ENEMY_SPEED;
	}
	
	public void moveUp() {
		yVel = -ENEMY_VERTICAL_SPEED;
	}
	public void moveDown()
	{
		yVel = ENEMY_VERTICAL_SPEED;
	}
	
	@Override
	public void track(Hero hero) {
		if(yPos + height > (MainApp.LEVEL_HEIGHT-1)*PlatformPiece.DEFAULT_HEIGHT+10) {
			yVel = -10;
		}	
	}
}
