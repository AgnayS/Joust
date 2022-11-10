package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
//initial implementation of Grunt as a subclass of enemy
public class Grunt extends Enemy{
	private final static Image MOVING_LEFT_ENEMY_SPRITE = null, MOVING_RIGHT_ENEMY_SPRITE = null, JUMPING_LEFT_ENEMY_SPRITE = null, JUMPING_RIGHT_ENEMY_SPRITE = null, FALLING_LEFT_ENEMY_SPRITE = null, FALLING_RIGHT_ENEMY_SPRITE = null;
	
	private final static int GRUNT_SPEED = 10; //default grunt speed for initial implementation
	private final static int  GRUNT_VERTICAL_SPEED = 0;
	
	public Grunt(int xPos, int yPos) {
		super(xPos, yPos, MOVING_LEFT_ENEMY_SPRITE, GRUNT_SPEED, GRUNT_VERTICAL_SPEED);
	}
	
	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.RED);
		g2.fillRoundRect((int)xPos,(int)yPos, width, height,10,10);
		g2.setColor(Color.BLACK);
	}
	

	public void track(Hero hero) {
		if(hero.getxPos() > this.getxPos()) {
			this.setxVel(GRUNT_SPEED);
		} else if(hero.getxPos() < this.getxPos()){
			this.setxVel(-GRUNT_SPEED);
		}
	}
	
}
