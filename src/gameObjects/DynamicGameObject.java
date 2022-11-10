package gameObjects;

import java.awt.Image;
import java.util.ArrayList;

public abstract class DynamicGameObject extends GameObject {

	private final static double GRAVITATIONAL_ACCELERATION = 1;
	private final static double TERMINAL_VELOCITY = 40;
	private final static int MAX_X = 960-48;
	
	protected double xVel, yVel;

	public DynamicGameObject(int width, int height, Image sprite, double xPos, double yPos) {
		super(width, height, sprite, xPos, yPos);
	}

	public int[] getCollisionDirection(GameObject other) {
		double distanceIntoLeft = this.xPos + this.width - other.getxPos();
		double distanceIntoRight = other.getxPos() + other.getWidth() - this.xPos;
		double distanceIntoTop = this.yPos + this.height - other.getyPos();
		double distanceIntoBottom = other.getyPos() + other.getHeight() - this.yPos;
		double distanceIntoClosestSide = Math.min(Math.min(distanceIntoTop, distanceIntoBottom), Math.min(distanceIntoLeft, distanceIntoRight));	
		if(distanceIntoClosestSide <= 0) {
			return null;
		} else if(distanceIntoClosestSide == distanceIntoLeft) {
			return new int[] {-1,0};
		} else if(distanceIntoClosestSide == distanceIntoRight) {
			return new int[] {1,0};
		} else if(distanceIntoClosestSide == distanceIntoTop) {
			return new int[] {0,-1};
		} else {
			return new int[] {0,1};
		}
	}
	
	public boolean isCollidingWith(GameObject other) {
		return getCollisionDirection(other) != null;
	}

	public void update(ArrayList<Hero> heroes, ArrayList<PlatformPiece> platformPieces) {
		
		if(xPos > MAX_X+this.width*2) {
			xPos = -this.width*.9;
		}
		if(xPos < -this.width*.9) {
			xPos = MAX_X+this.width*2;
		}
		xPos += xVel;
		yPos += yVel;
		yVel += GRAVITATIONAL_ACCELERATION;
		if(yVel > TERMINAL_VELOCITY) {
			yVel = TERMINAL_VELOCITY;
		}
		for (Hero hero : heroes) {
			if (this.isCollidingWith(hero)) {
				this.handleHeroInteraction(hero);
			}
		}
		for (PlatformPiece platformPiece : platformPieces) {
			if (this.isCollidingWith(platformPiece)) {
				platformPiece.handleCollision(this);
			}
		}
	}

	public void handleHeroInteraction(Hero hero){}

	public double getxVel() {
		return xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}
	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	protected void adjustPosition(int[] positionIncrement) {
		xPos += positionIncrement[0];
		yPos += positionIncrement[1];
	}

	public void track(Hero hero) {
		
	}

}
