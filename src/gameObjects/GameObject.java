package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;
/**
 * GameObject is the super class that encompasses all objects draw on screen. All gameobjects have an x and y position, width/height
 * GameObject also handles the booleans for whether or not an object should be removed.
 * @author jonescm
 *
 */
public abstract class GameObject {

	protected final int width, height;
	private final static double PIXELS_PER_SPRITE_PIXEL = 1.3333;
	
	protected Image spriteSheet;
	protected double xPos, yPos;
	protected boolean markedForRemoval;
	protected int spriteNumber;

	
	
	public GameObject(int width, int height, Image spriteSheet, double xPos, double yPos) {
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		this.spriteSheet = spriteSheet;
	}
	//creates the particular object on screen based on its sprite size and provided position
	public void drawOn(Graphics2D g2) {
		g2.drawImage(spriteSheet, (int) xPos, (int) yPos, (int) xPos + width, (int) yPos + height, (int)(width/PIXELS_PER_SPRITE_PIXEL)*spriteNumber, 0, (int)(width/PIXELS_PER_SPRITE_PIXEL)*(spriteNumber+1), (int)(height/PIXELS_PER_SPRITE_PIXEL), null);
	}
	
	public void markToRemove() {
		markedForRemoval = true;
	}
	//shouldBeRemoved is how the game essentially tells an object to remove itself
	public boolean shouldBeRemoved() {
		return markedForRemoval;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public double getxPos() {
		return xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public Image getSprite() {
		return spriteSheet;
	}
}
