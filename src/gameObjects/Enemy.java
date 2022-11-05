package gameObjects;

import java.awt.Image;
//creation of Enemy as an abstract class
public abstract class Enemy extends DynamicGameObject {
	private final static int ENEMY_WIDTH = 20;
	private final static int ENEMY_HEIGHT = 20;

	private int pointValue;
	
	public Enemy(int xPos, int yPos, Image sprite, int pointValue) {
		super(ENEMY_WIDTH, ENEMY_HEIGHT, sprite, xPos, yPos);
		this.pointValue = pointValue;
	}
	@Override
	public void handleHeroInteraction(Hero hero) {

		if(hero.getyPos() == this.getyPos()) { //No winner!IN ORDER FOR BOUNCING THIS NEEDS TO BE THE FIRST IF
			this.setxVel(-this.getxVel());
		}
		if(hero.getyPos() < this.getyPos() - 1){ //Hero Win! Adjusting for object bouncing when changing enemy y position			
			hero.updateScore(100); //test point value of 100
			this.markToRemove();
			
		}
		if(hero.getyPos() > this.getyPos() - 1) {//Hero Loses!
			hero.die(System.currentTimeMillis());
		}
	}
}
