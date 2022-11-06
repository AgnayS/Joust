package gameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
//initial creation of Hopper as a subclass of enemy
public class Hopper extends Enemy{

	private final static int DEFAULT_POINT_VALUE = 10;
	private final static Image MOVING_LEFT_ENEMY_SPRITE = null, MOVING_RIGHT_ENEMY_SPRITE = null, JUMPING_LEFT_ENEMY_SPRITE = null, JUMPING_RIGHT_ENEMY_SPRITE = null, FALLING_LEFT_ENEMY_SPRITE = null, FALLING_RIGHT_ENEMY_SPRITE = null;
	
	private final static int  HOPPER_SPEED = 15; //default grunt speed for initial implementation
	private final static int  HOPPER_VERTICAL_SPEED = 0;
	
	public Hopper(int xPos, int yPos) {
		super(xPos, yPos, MOVING_LEFT_ENEMY_SPRITE, HOPPER_SPEED, HOPPER_VERTICAL_SPEED);
		
		
	}
	@Override
	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.BLUE);
		g2.fillOval((int)xPos,(int)yPos, width, height);
		g2.setColor(Color.BLACK);
	}
	
	
}
