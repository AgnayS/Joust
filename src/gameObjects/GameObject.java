package gameObjects;

import java.awt.Graphics2D;
import java.awt.Image;

public abstract class GameObject {

	protected final int width, height;
	protected final Image sprite;

	protected double xPos, yPos;
	protected boolean markedForRemoval;

	public GameObject(int width, int height, Image sprite, double xPos, double yPos) {
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		this.sprite = sprite;
	}

	public void drawOn(Graphics2D g2) {
		g2.drawImage(sprite, (int) xPos, (int) yPos, width, height, 0, 0, sprite.getWidth(null), sprite.getHeight(null), null);
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
		return sprite;
	}
}
