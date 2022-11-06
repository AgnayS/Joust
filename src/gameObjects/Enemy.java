package gameObjects;

import java.awt.Image;
//creation of Enemy as an abstract class
public abstract class Enemy extends DynamicGameObject {
	private final static int ENEMY_WIDTH = 20;
	private final static int ENEMY_HEIGHT = 20;
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
			this.setxVel(-this.getxVel());
		}
		if(hero.getyPos() < this.getyPos() - 1){ //Hero Win! Adjusting for object bouncing when changing enemy y position			
			this.markToRemove(); //Egg is being spawned inside of update game
		}
		if(hero.getyPos() > this.getyPos() - 1) {//Hero Loses!
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
	public void track(Hero hero) {}
}
