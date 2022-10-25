package gameObjects;

import java.awt.Image;

public abstract class GameObject {

	protected int width, height;
	
	protected double xPos, yPos, xVel, yVel;
	Image sprite;

	public boolean collision() {
		return true;
	}

}
