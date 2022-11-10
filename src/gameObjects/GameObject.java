package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;

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

	public void drawOn(Graphics2D g2) {
		g2.drawImage(spriteSheet, (int) xPos, (int) yPos, (int) xPos + width, (int) yPos + height, (int)(width/PIXELS_PER_SPRITE_PIXEL)*spriteNumber, 0, (int)(height/PIXELS_PER_SPRITE_PIXEL)*(spriteNumber+1), 48, null);
	}
	
	public void markToRemove() {
		markedForRemoval = true;
	}
	
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
