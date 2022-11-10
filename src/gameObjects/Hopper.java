package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
/**
 * A subclass of enemy, Hopper is designed to track the Hero's y position and match or get above it
 * @author jonescm
 *
 */
public class Hopper extends Enemy{

	private final static Image MOVING_LEFT_ENEMY_SPRITE = null, MOVING_RIGHT_ENEMY_SPRITE = null, JUMPING_LEFT_ENEMY_SPRITE = null, JUMPING_RIGHT_ENEMY_SPRITE = null, FALLING_LEFT_ENEMY_SPRITE = null, FALLING_RIGHT_ENEMY_SPRITE = null;
	
	private final static int  HOPPER_SPEED = 15; //default grunt speed for initial implementation
	private final static int  HOPPER_VERTICAL_SPEED = 10;
	
	public Hopper(int xPos, int yPos) {
		super(xPos, yPos, MOVING_LEFT_ENEMY_SPRITE, HOPPER_SPEED, HOPPER_VERTICAL_SPEED);
		
		
	}
	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.BLUE);
		g2.fillOval((int)xPos,(int)yPos, width, height);
		g2.setColor(Color.BLACK);
	}
	
	@Override
	public void track(Hero hero) {
		
		if(hero.getxPos() > this.getxPos()) {
			this.setxVel(HOPPER_SPEED);
		}
		if(hero.getxPos() < this.getxPos()){
			this.setxVel(-HOPPER_SPEED); //make sure it cannot go backwards in the x direction
		}
		if(hero.getyPos()-10< this.getyPos()) {
			this.setyVel(-1.2*(HOPPER_VERTICAL_SPEED)); 
		}
		if(hero.getyPos()-10> this.getyPos()) {
			this.setyVel(1.2*Math.abs(HOPPER_VERTICAL_SPEED));
		}
		
	}	
}
