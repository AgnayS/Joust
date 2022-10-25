package gameObjects;

import java.awt.Image;

public abstract class GameObject {

	protected final int width, height;
	protected final Image sprite;

	protected double xPos, yPos, xVel, yVel;

	public GameObject(int width, int height, Image sprite, double xPos, double yPos) {
		this.width = width;
		this.height = height;
		this.xPos = xPos;
		this.yPos = yPos;
		this.sprite = sprite;
	}

	public boolean collision(GameObject other) {
		return (this.xPos + this.width >= other.getxPos() || other.getxPos() + other.getWidth() >= this.xPos)
				&& (this.yPos + this.height >= other.getyPos() || other.getyPos() + other.getHeight() >= this.yPos);
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

	public double getxVel() {
		return xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public Image getSprite() {
		return sprite;
	}
}
