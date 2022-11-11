package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.awt.Color;
/**
 * A subclass of enemy, Drone will track the hero and fly just above the hero to continuously kill the hero
 * @author jonescm
 *
 */
public class Drone extends Enemy{
	private final static Image MOVING_LEFT_ENEMY_SPRITE = null, MOVING_RIGHT_ENEMY_SPRITE = null, JUMPING_LEFT_ENEMY_SPRITE = null, JUMPING_RIGHT_ENEMY_SPRITE = null, FALLING_LEFT_ENEMY_SPRITE = null, FALLING_RIGHT_ENEMY_SPRITE = null;
	
	private final static int DRONE_SPEED = 16;
	
	public Drone(int xPos, int yPos) {
		super(xPos, yPos, MOVING_LEFT_ENEMY_SPRITE, DRONE_SPEED, DRONE_SPEED);
	}
	
	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.CYAN);
		g2.fillOval((int)xPos,(int)yPos, width, height);
		g2.setColor(Color.BLACK);
	}
	
	@Override
	public void track(Hero hero) {
		xVel = DRONE_SPEED*(hero.getxPos()-xPos)/Math.hypot(hero.getxPos()-xPos, hero.getyPos()-20-yPos);
		yVel = DRONE_SPEED*(hero.getyPos()-20-yPos)/Math.hypot(hero.getxPos()-xPos, hero.getyPos()-20-yPos);
	}
	
	@Override
	public ArrayList<DynamicGameObject> getRemnants(){
		ArrayList<DynamicGameObject> output = super.getRemnants();
		output.add(new Heart(xPos,yPos));
		return output;
	}
	
	
}
